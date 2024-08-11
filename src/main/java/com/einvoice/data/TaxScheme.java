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

public final class TaxScheme implements XMLElement {

    private final String id;

    private final String schemeId;

    private final String schemeAgencyId;

    public TaxScheme(String id, String schemeId, String schemeAgencyId) {
        this.id = id;
        this.schemeId = schemeId;
        this.schemeAgencyId = schemeAgencyId;
    }

    public String getId() {
        return id;
    }

    public String getSchemeId() {
        return schemeId;
    }

    public String getSchemeAgencyId() {
        return schemeAgencyId;
    }

    // @formatter:off
    @Override
    public String toXML() {
        return "<cac:TaxScheme>" +
                    "<cbc:ID schemeAgencyID=\"" + XMLElement.escape(schemeAgencyId, true) + "\" schemeID=\"" +  XMLElement.escape(schemeId, true) + "\">" + XMLElement.escape(id) + "</cbc:ID>" +
               "</cac:TaxScheme>";
    }
}
