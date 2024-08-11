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

public final class Reference implements XMLElement {

    private final String id;

    private final String uri;

    private final String type;

    private final Transform[] transforms;

    private final DigestMethod digestMethod;

    private final DigestValue digestValue;

    public Reference(String id,
                     String uri,
                     String type,
                     Transform[] transforms,
                     DigestMethod digestMethod,
                     DigestValue digestValue) {
        this.id = id;
        this.uri = uri;
        this.type = type;
        this.transforms = transforms;
        this.digestMethod = digestMethod;
        this.digestValue = digestValue;
    }

    public String getId() {
        return id;
    }

    public String getUri() {
        return uri;
    }

    public String getType() {
        return type;
    }

    public Transform[] getTransforms() {
        return transforms;
    }

    public DigestMethod getDigestMethod() {
        return digestMethod;
    }

    public DigestValue getDigestValue() {
        return digestValue;
    }

    @Override
    public String toXML() {
        StringBuilder xml = new StringBuilder(255)
            .append("<ds:Reference");
        if (id != null) {
            xml.append(" Id=\"").append(id).append('"');
        }
        if (type != null) {
            xml.append(" Type=\"").append(type).append('"');
        }
        if (uri != null) {
            xml.append(" URI=\"").append(uri).append('"');
        }
        xml.append('>');
        int size = transforms == null ? 0 : transforms.length;
        if (size > 0) {
            xml.append("<ds:Transforms>");
            for (int i = 0; i < size; i++) {
                xml.append(transforms[i].toXML());
            }
            xml.append("</ds:Transforms>");
        }
        if (digestMethod != null) {
            xml.append(digestMethod.toXML());
        }
        if (digestValue != null) {
            xml.append(digestValue.toXML());
        }
        return xml.append("</ds:Reference>").toString();
    }
}
