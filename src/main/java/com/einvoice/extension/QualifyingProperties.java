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

public final class QualifyingProperties implements XMLElement {

    private final String target;

    private final SignedProperties signedProperties;

    public QualifyingProperties(String target,
                                SignedProperties signedProperties) {
        this.target = target;
        this.signedProperties = signedProperties;
    }

    public static QualifyingProperties of(String target, SignedProperties signedProperties) {
        return new QualifyingProperties(target, signedProperties);
    }

    public String getTarget() {
        return target;
    }

    public SignedProperties getSignedProperties() {
        return signedProperties;
    }

    // @formatter:off
    @Override
    public String toXML() {
        StringBuilder xml = new StringBuilder(255)
            .append("<xades:QualifyingProperties xmlns:xades=\"http://uri.etsi.org/01903/v1.3.2#\"");
        if (target != null) {
            xml.append(" Target=\"").append(target).append('"');
        }
        return xml.append('>')
                  .append(signedProperties.toXML())
                  .append("</xades:QualifyingProperties>")
                  .toString();
    }
}
