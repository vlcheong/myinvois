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

public final class DeliveryParty implements XMLElement {

    private IndustryClassificationCode[] industryClassificationCodes;

    private PartyIdentification[] partyIdentifications;

    private PostalAddress postalAddress;

    private PartyLegalEntity partyLegalEntity;

    private Contact contact;

    public DeliveryParty() {
    }

    public DeliveryParty(IndustryClassificationCode[] industryClassificationCodes,
                         PartyIdentification[] partyIdentifications,
                         PostalAddress postalAddress,
                         PartyLegalEntity partyLegalEntity,
                         Contact contact) {
        this.industryClassificationCodes = industryClassificationCodes;
        this.partyIdentifications = partyIdentifications;
        this.postalAddress = postalAddress;
        this.partyLegalEntity = partyLegalEntity;
        this.contact = contact;
    }

    public DeliveryParty industryClassificationCode(IndustryClassificationCode... industryClassificationCodes) {
        this.industryClassificationCodes = industryClassificationCodes;
        return this;
    }

    public DeliveryParty partyIdentification(PartyIdentification... partyIdentifications) {
        this.partyIdentifications = partyIdentifications;
        return this;
    }

    public DeliveryParty postalAddress(PostalAddress postalAddress) {
        this.postalAddress = postalAddress;
        return this;
    }

    public DeliveryParty partyLegalEntity(PartyLegalEntity partyLegalEntity) {
        this.partyLegalEntity = partyLegalEntity;
        return this;
    }

    public DeliveryParty contact(Contact contact) {
        this.contact = contact;
        return this;
    }

    public IndustryClassificationCode[] getIndustryClassificationCodes() {
        return industryClassificationCodes;
    }

    public PartyIdentification[] getPartyIdentifications() {
        return partyIdentifications;
    }

    public PostalAddress getPostalAddress() {
        return postalAddress;
    }

    public PartyLegalEntity getPartyLegalEntity() {
        return partyLegalEntity;
    }

    public Contact getContact() {
        return contact;
    }

    @Override
    public String toXML() {
        StringBuilder xml = new StringBuilder(255);
        xml.append("<cac:DeliveryParty>");
        int size = industryClassificationCodes == null ? 0 : industryClassificationCodes.length;
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                xml.append(industryClassificationCodes[i].toXML());
            }
        }
        size = partyIdentifications == null ? 0 : partyIdentifications.length;
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                xml.append(partyIdentifications[i].toXML());
            }
        }
        xml.append(PostalAddress.toXML(postalAddress));
        if (partyLegalEntity != null) {
            xml.append(PartyLegalEntity.toXML(partyLegalEntity));
        }
        return xml.append("</cac:DeliveryParty>").toString();
    }
}
