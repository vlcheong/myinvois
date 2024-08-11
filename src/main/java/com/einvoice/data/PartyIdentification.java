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

public final class PartyIdentification implements XMLElement {

    private static final PartyIdentification[] EMPTY = new PartyIdentification[]{};

    private final String id;

    private final String schemeId;

    public PartyIdentification(String id, String schemeId) {
        this.id = id;
        this.schemeId = schemeId;
    }

    public static PartyIdentification of(String id, String schemeId) {
        return new PartyIdentification(id, schemeId);
    }

    public String getId() {
        return id;
    }

    public String getSchemeId() {
        return schemeId;
    }

    public static PartyIdentification[] of(PartyIdentification... partyIdentifications) {
        int size = partyIdentifications == null ? 0 : partyIdentifications.length;
        if (size == 0) {
            return EMPTY;
        }
        return partyIdentifications;
    }

    // @formatter:off
    @Override
    public String toXML() {
        return "<cac:PartyIdentification>" +
                    "<cbc:ID schemeID=\"" + XMLElement.escape(schemeId, true) + "\">" + XMLElement.escape(id) + "</cbc:ID>" +
                "</cac:PartyIdentification>";
    }
}
