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

public final class DigestMethod implements XMLElement {

    private final String algorithm;

    private final String digestMethod;

    public DigestMethod(String algorithm, String digestMethod) {
        this.algorithm = algorithm;
        this.digestMethod = digestMethod;
    }

    public static DigestMethod of(String algorithm) {
        return new DigestMethod(algorithm, null);
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public String getDigestMethod() {
        return digestMethod;
    }

    // @formatter:off
    public String signature() {
        StringBuilder xml = new StringBuilder(255)
            .append("<ds:DigestMethod Algorithm=\"")
            .append(algorithm)
            .append("\" xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\"");
        if (digestMethod == null) {
            xml.append(" />");
        } else {
            xml.append('>')
               .append(digestMethod)
               .append("</ds:DigestMethod>");
        }
        return xml.toString();
    }

    // @formatter:off
    @Override
    public String toXML() {
        StringBuilder xml = new StringBuilder(255)
            .append("<ds:DigestMethod Algorithm=\"")
            .append(algorithm)
            .append('"');
        if (digestMethod == null) {
            xml.append("/>");
        } else {
            xml.append('>')
               .append(digestMethod)
               .append("</ds:DigestMethod>");
        }
        return xml.toString();
    }
}
