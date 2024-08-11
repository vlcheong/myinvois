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

public final class SignedInfo implements XMLElement {

    private final CanonicalizationMethod canonicalizationMethod;

    private final SignatureMethod signatureMethod;

    private final Reference[] references;

    public SignedInfo(CanonicalizationMethod canonicalizationMethod,
                      SignatureMethod signatureMethod,
                      Reference... references) {
        this.canonicalizationMethod = canonicalizationMethod;
        this.signatureMethod = signatureMethod;
        int size = references == null ? 0 : references.length;
        if (size == 0) {
            this.references = null;
        } else {
            this.references = references;
        }
    }

    public CanonicalizationMethod getCanonicalizationMethod() {
        return canonicalizationMethod;
    }

    public SignatureMethod getSignatureMethod() {
        return signatureMethod;
    }

    public Reference[] getReferences() {
        return references;
    }

    @Override
    public String toXML() {
        StringBuilder xml = new StringBuilder(255)
            .append("<ds:SignedInfo>");
        if (canonicalizationMethod != null) {
            xml.append(canonicalizationMethod.toXML());
        }
        if (signatureMethod != null) {
            xml.append(signatureMethod.toXML());
        }
        int size = references == null ? 0 : references.length;
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                xml.append(references[i].toXML());
            }
        }
        return xml.append("</ds:SignedInfo>").toString();
    }
}
