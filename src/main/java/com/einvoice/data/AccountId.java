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

public final class AccountId implements XMLElement {

    private final String id;

    private final String schemeAgencyName;

    public AccountId(String id, String schemeAgencyName) {
        this.id = id;
        this.schemeAgencyName = schemeAgencyName;
    }

    public AccountId(String schemeAgencyName) {
        this(null, schemeAgencyName);
    }

    public static AccountId of(String schemeAgencyName) {
        return new AccountId(schemeAgencyName);
    }

    public String getId() {
        return id;
    }

    public String getSchemeAgencyName() {
        return schemeAgencyName;
    }

    public static String toXML(AccountId accountId) {
        return accountId == null ? "" : accountId.toXML();
    }

    // @formatter:off
    @Override
    public String toXML() {
        StringBuilder xml = new StringBuilder(255)
            .append("<cbc:AdditionalAccountID");
        if (schemeAgencyName != null) {
            xml.append(" schemeAgencyName=\"").append(XMLElement.escape(schemeAgencyName, true)).append('"');
        }
        if (id == null) {
            xml.append("/>");
        } else {
            xml.append('>').append(XMLElement.escape(id)).append("</<cbc:AdditionalAccountID>");
        }
        return xml.toString();
    }
}
