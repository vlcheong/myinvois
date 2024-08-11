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

package com.einvoice.data;

import com.einvoice.base.XMLElement;

public final class IndustryClassificationCode implements XMLElement {

    private final String name;

    private final String code;

    public IndustryClassificationCode(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public static IndustryClassificationCode of(String name, String code) {
        return new IndustryClassificationCode(name, code);
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public static String toXML(IndustryClassificationCode industryClassificationCode) {
        if (industryClassificationCode == null) {
            return "";
        }
        return industryClassificationCode.toXML();
    }

    // @formatter:off
    @Override
    public String toXML() {
        StringBuilder xml = new StringBuilder(255)
            .append("<cbc:IndustryClassificationCode");
        if (name != null) {
            xml.append(" name=\"").append(XMLElement.escape(name, true)).append('"');
        }
        xml.append('>');
        if (code != null) {
            xml.append(XMLElement.escape(code));
        }
        return xml.append("</cbc:IndustryClassificationCode>").toString();
    }
}
