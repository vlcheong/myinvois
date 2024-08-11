package com.einvoice.extension;

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

import com.einvoice.base.XMLElement;

public final class SigningCertificate implements XMLElement {

    private final Cert cert;

    public SigningCertificate(Cert cert) {
        this.cert = cert;
    }

    public static SigningCertificate of(Cert cert) {
        return new SigningCertificate(cert);
    }

    public Cert getCert() {
        return cert;
    }

    // @formatter:off
    public String signature() {
        return "<xades:SigningCertificate>" +
                    cert.signature() +
               "</xades:SigningCertificate>";
    }

    // @formatter:off
    @Override
    public String toXML() {
        return "<xades:SigningCertificate>" +
                    cert.toXML() +
                "</xades:SigningCertificate>";
    }
}
