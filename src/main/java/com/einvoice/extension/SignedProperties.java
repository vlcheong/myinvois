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

package com.einvoice.extension;

import com.einvoice.base.XMLElement;

public final class SignedProperties implements XMLElement {

    private final String id;

    private final SignedSignatureProperties signedSignatureProperties;

    public SignedProperties(String id,
                            SignedSignatureProperties signedSignatureProperties) {
        this.id = id;
        this.signedSignatureProperties = signedSignatureProperties;
    }

    public static SignedProperties of(String id, SignedSignatureProperties signedSignatureProperties) {
        return new SignedProperties(id, signedSignatureProperties);
    }

    public String getId() {
        return id;
    }

    public SignedSignatureProperties getSignedSignatureProperties() {
        return signedSignatureProperties;
    }

    // @formatter:off
    public String signature() {
        StringBuilder xml = new StringBuilder(255)
            .append("<xades:SignedProperties");
        if (id != null) {
            xml.append(" Id=\"").append(id).append('"');
        }
        return xml.append(" xmlns:xades=\"http://uri.etsi.org/01903/v1.3.2#\">")
                  .append(signedSignatureProperties.signature())
                  .append("</xades:SignedProperties>")
                  .toString();
    }

    // @formatter:off
    @Override
    public String toXML() {
        StringBuilder xml = new StringBuilder(255)
            .append("<xades:SignedProperties");
        if (id != null) {
            xml.append(" Id=\"").append(id).append('"');
        }
        return xml.append('>')
                  .append(signedSignatureProperties.toXML())
                  .append("</xades:SignedProperties>")
                  .toString();
    }
}
