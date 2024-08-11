package com.einvoice.data;

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

public final class Country implements XMLElement {

    private final String identificationCode;

    private final String listId;

    private final String listAgencyId;

    public Country(String identificationCode, String listId, String listAgencyId) {
        this.identificationCode = identificationCode;
        this.listId = listId;
        this.listAgencyId = listAgencyId;
    }

    public String getIdentificationCode() {
        return identificationCode;
    }

    public String getListId() {
        return listId;
    }

    public String getListAgencyId() {
        return listAgencyId;
    }

    public static String toXML(Country country) {
        if (country == null) {
            return "<cac:Country>" +
                        "<cbc:IdentificationCode listAgencyID=\"6\" listID=\"ISO3166-1\"/>" +
                   "</cac:Country>";
        }
        return country.toXML();
    }

    @Override
    public String toXML() {
        StringBuilder xml = new StringBuilder(255)
            .append("<cac:Country><cbc:IdentificationCode");
        if (listAgencyId != null) {
            xml.append(" listAgencyID=\"").append(XMLElement.escape(listAgencyId, true)).append('"');
        }
        if (listId != null) {
            xml.append(" listID=\"").append(XMLElement.escape(listId, true)).append('"');
        }
        xml.append('>');
        if (identificationCode != null) {
            xml.append(XMLElement.escape(identificationCode));
        }
        return xml.append("</cbc:IdentificationCode>").append("</cac:Country>").toString();
    }
}
