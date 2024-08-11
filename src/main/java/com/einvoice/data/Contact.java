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

public final class Contact implements XMLElement {

    private final String telephone;

    private final String electronicMail;

    public Contact(String telephone, String electronicMail) {
        this.telephone = telephone;
        this.electronicMail = electronicMail;
    }

    public static Contact of(String telephone, String electronicMail) {
        return new Contact(telephone, electronicMail);
    }

    public String getTelephone() {
        return telephone;
    }

    public String getElectronicMail() {
        return electronicMail;
    }

    @Override
    public String toXML() {
        if (telephone != null || electronicMail != null) {
            StringBuilder xml = new StringBuilder(255)
                .append("<cac:Contact>");
            if (telephone == null || telephone.isBlank()) {
                xml.append("<cbc:Telephone></cbc:Telephone>");
            } else {
                xml.append("<cbc:Telephone>").append(XMLElement.escape(telephone)).append("</cbc:Telephone>");
            }
            if (electronicMail == null || electronicMail.isBlank()) {
                xml.append("<cbc:ElectronicMail></cbc:ElectronicMail>");
            } else {
                xml.append("<cbc:ElectronicMail>").append(XMLElement.escape(electronicMail)).append("</cbc:ElectronicMail>");
            }
            return xml.append("</cac:Contact>").toString();
        }
        return "";
    }
}
