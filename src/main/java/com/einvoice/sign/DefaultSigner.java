/*
 * Copyright 2024 VLCHEONG
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.einvoice.sign;

import com.einvoice.data.Invoice;
import com.einvoice.extension.*;
import com.einvoice.util.Dates;
import com.einvoice.util.SHA256;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;
import java.util.Base64;
import java.util.Calendar;

final class DefaultSigner implements Signer {

    private static final CanonicalizationMethod CANONICALIZATION_METHOD = new CanonicalizationMethod("https://www.w3.org/TR/xml-c14n11/#");

    private static final SignatureMethod SIGNATURE_METHOD = new SignatureMethod("http://www.w3.org/2001/04/xmldsig-more#rsa-sha256");

    private static final DigestMethod DIGEST_METHOD = DigestMethod.of("http://www.w3.org/2001/04/xmlenc#sha256");

    private static final Transform[] TRANSFORMS = new Transform[]{
        Transform.of("http://www.w3.org/TR/1999/REC-xpath-19991116", "not(//ancestor-or-self::ext:UBLExtensions)"),
        Transform.of("http://www.w3.org/TR/1999/REC-xpath-19991116", "not(//ancestor-or-self::cac:Signature)"),
        Transform.of("http://www.w3.org/2006/12/xml-c14n11")
    };

    private static final String SIGNATURE = com.einvoice.data.Signature.of(
        "urn:oasis:names:specification:ubl:signature:Invoice",
        "urn:oasis:names:specification:ubl:dsig:enveloped:xades"
    ).toXML();

    static final Signer INSTANCE = new DefaultSigner();

    private DefaultSigner() {
    }

    @Override
    public String sign(Invoice invoice, KeyStoreConfig config) {
        try {
            String xml = invoice.toXML();
            String standard = XML.canonicalize(XML.minify(xml), javax.xml.crypto.dsig.CanonicalizationMethod.INCLUSIVE_11);
            UBLExtensions extensions = extensions(standard.getBytes(StandardCharsets.UTF_8), config);
            StringBuilder sb = new StringBuilder(xml.length() * 2);
            sb.append(xml);
            append(extensions, sb);
            return sb.toString();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    private UBLExtensions extensions(byte[] raw, KeyStoreConfig config) throws CertificateEncodingException,
        NoSuchAlgorithmException,
        SignatureException,
        InvalidKeyException {

        byte[] docHash = SHA256.hash(raw);
        String docDigest = Base64.getEncoder().encodeToString(docHash);

        SignedObject signedObject = signedObject(config);
        SignedProperties signedProperties = signedObject.getQualifyingProperties().getSignedProperties();
        String signature = signedProperties.signature();
        String propsDigest = Base64.getEncoder().encodeToString(SHA256.hash(signature));

        UBLDocumentSignatures signatures = new UBLDocumentSignatures(
            new SignatureInformation(
                "urn:oasis:names:specification:ubl:signature:1",
                "urn:oasis:names:specification:ubl:signature:Invoice",
                new Signature(
                    new SignedInfo(
                        CANONICALIZATION_METHOD,
                        SIGNATURE_METHOD,
                        new Reference("id-doc-signed-data",
                            "",
                            null,
                            TRANSFORMS,
                            DIGEST_METHOD,
                            DigestValue.of(docDigest)
                        ),
                        new Reference(null,
                            "#id-xades-signed-props",
                            "http://www.w3.org/2000/09/xmldsig#SignatureProperties",
                            null,
                            DIGEST_METHOD,
                            DigestValue.of(propsDigest)
                        )
                    ),
                    config.signatureValue(raw),
                    keyInfo(config),
                    signedObject
                )
            )
        );
        UBLExtension extension = new UBLExtension(
            "urn:oasis:names:specification:ubl:dsig:enveloped:xades",
            new ExtensionContent(signatures)
        );
        return new UBLExtensions(extension);
    }

    private KeyInfo keyInfo(KeyStoreConfig config) throws CertificateEncodingException {
        X509Certificate cert = new X509Certificate(config.getCertData());
        return new KeyInfo(new X509Data(cert));
    }

    private SignedObject signedObject(KeyStoreConfig config) throws CertificateEncodingException {
        Calendar UTC = Calendar.getInstance(Dates.UTC);
        UTC.add(Calendar.MINUTE, 10);
        SignedProperties signedProperties = SignedProperties.of(
            "id-xades-signed-props",
            SignedSignatureProperties.of(
                UTC.getTime(),
                SigningCertificate.of(
                    new Cert(
                        CertDigest.of(
                            DIGEST_METHOD,
                            config.getCertDigest()
                        ),
                        IssuerSerial.of(config.getIssuerName(), config.getSerialNumber())
                    )
                )
            )
        );
        return new SignedObject(QualifyingProperties.of("signature", signedProperties));
    }

    private void append(UBLExtensions extensions, StringBuilder sb) {
        int offset = sb.indexOf("<cbc:ID>");
        sb.insert(offset, extensions.toXML());
        offset = sb.indexOf("<cac:AccountingSupplierParty>");
        sb.insert(offset, SIGNATURE);
    }
}
