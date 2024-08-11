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

public final class PartyLegalEntity implements XMLElement {

    private final String registrationName;

    public PartyLegalEntity(String registrationName) {
        this.registrationName = registrationName;
    }

    public static PartyLegalEntity of(String registrationName) {
        return new PartyLegalEntity(registrationName);
    }

    public String getRegistrationName() {
        return registrationName;
    }

    // @formatter:off
    public static String toXML(PartyLegalEntity partyLegalEntity) {
        if (partyLegalEntity == null ||
            partyLegalEntity.registrationName == null ||
            partyLegalEntity.registrationName.isBlank()) {
            return "<cac:PartyLegalEntity>" +
                        "<cbc:RegistrationName></cbc:RegistrationName>" +
                   "</cac:PartyLegalEntity>";
        }
        return partyLegalEntity.toXML();
    }

    // @formatter:off
    @Override
    public String toXML() {
        return "<cac:PartyLegalEntity>" +
                    "<cbc:RegistrationName>" + XMLElement.escape(registrationName) + "</cbc:RegistrationName>" +
               "</cac:PartyLegalEntity>";
    }
}
