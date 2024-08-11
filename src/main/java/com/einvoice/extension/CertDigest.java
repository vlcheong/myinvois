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

public final class CertDigest implements XMLElement {

    private final DigestMethod digestMethod;

    private final String digestValue;

    public CertDigest(DigestMethod digestMethod, String digestValue) {
        this.digestMethod = digestMethod;
        this.digestValue = digestValue;
    }

    public static CertDigest of(DigestMethod digestMethod, String digestValue) {
        return new CertDigest(digestMethod, digestValue);
    }

    public DigestMethod getDigestMethod() {
        return digestMethod;
    }

    public String getDigestValue() {
        return digestValue;
    }

    // @formatter:off
    public String signature() {
        return "<xades:CertDigest>" +
                    this.digestMethod.signature() +
                    "<ds:DigestValue xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\">" + digestValue + "</ds:DigestValue>" +
                "</xades:CertDigest>";
    }

    // @formatter:off
    @Override
    public String toXML() {
        return "<xades:CertDigest>" +
                    this.digestMethod.toXML() +
                    "<ds:DigestValue>" + digestValue + "</ds:DigestValue>" +
               "</xades:CertDigest>";
    }
}
