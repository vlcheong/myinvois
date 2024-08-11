package com.einvoice.sign;

/* Copyright 2024 VLCHEONG
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

import com.einvoice.util.SHA256;

import javax.security.auth.x500.X500Principal;
import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Base64;

public class KeyStoreConfig {

    private final PrivateKey privateKey;

    private final X509Certificate certificate;

    public KeyStoreConfig(PrivateKey privateKey, X509Certificate certificate) {
        this.privateKey = privateKey;
        this.certificate = certificate;
    }

    String signatureValue(byte[] b) throws NoSuchAlgorithmException,
        InvalidKeyException,
        SignatureException {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(b);
        return Base64.getEncoder().encodeToString(signature.sign());
    }

    private String certDigest;

    String getCertDigest() throws CertificateEncodingException {
        if (this.certDigest == null) {
            byte[] b = certificate.getEncoded();
            this.certDigest = Base64.getEncoder().encodeToString(SHA256.hash(b)).trim();
        }
        return this.certDigest;
    }

    private String issuerName;

    String getIssuerName() {
        if (this.issuerName == null) {
            this.issuerName = certificate.getIssuerX500Principal().getName(X500Principal.RFC1779).trim();
        }
        return this.issuerName;
    }

    private String serialNumber;

    String getSerialNumber() {
        if (this.serialNumber == null) {
            BigInteger number = certificate.getSerialNumber();
            this.serialNumber = number.toString(10).trim();
        }
        return this.serialNumber;
    }

    private String certData;

    String getCertData() throws CertificateEncodingException {
        if (this.certData == null) {
            byte[] b = certificate.getEncoded();
            this.certData = Base64.getEncoder().encodeToString(b).trim();
        }
        return this.certData;
    }

    public static KeyStoreConfig from(File keyStoreFile, String password) {
        String type;
        String name = keyStoreFile.getName().toLowerCase();
        if (name.endsWith(".jks")) {
            type = "JKS";
        } else if (name.endsWith(".p12") || name.endsWith(".pfx")) {
            type = "PKCS12";
        } else {
            throw new IllegalArgumentException("Unsupported keystore format: " + name);
        }
        try (InputStream input = new FileInputStream(keyStoreFile)) {
            KeyStore keyStore = KeyStore.getInstance(type);
            keyStore.load(input, password.toCharArray());
            String alias = keyStore.aliases().nextElement();
            Key key = keyStore.getKey(alias, password.toCharArray());
            if (!(key instanceof PrivateKey)) {
                throw new IllegalArgumentException("Private key with alias " + alias + " not found");
            }
            Certificate certificate = keyStore.getCertificate(alias);
            if (certificate == null) {
                throw new IllegalArgumentException("Certificate with alias " + alias + " not found");
            }
            return new KeyStoreConfig((PrivateKey) key, (X509Certificate) certificate);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } catch (NoSuchAlgorithmException |
                 CertificateException |
                 KeyStoreException |
                 UnrecoverableKeyException e) {
            throw new SecurityException(e);
        }
    }
}
