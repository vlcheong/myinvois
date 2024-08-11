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

import java.util.Arrays;
import java.util.stream.Collectors;

public final class AccountingSupplierParty implements XMLElement {

    private final AccountId accountId;

    private final IndustryClassificationCode industryClassificationCode;

    private final PartyIdentification[] partyIdentifications;

    private final PostalAddress postalAddress;

    private final PartyLegalEntity partyLegalEntity;

    private final Contact contact;

    public AccountingSupplierParty(AccountId accountId,
                                   IndustryClassificationCode industryClassificationCode,
                                   PartyIdentification[] partyIdentifications,
                                   PostalAddress postalAddress,
                                   PartyLegalEntity partyLegalEntity,
                                   Contact contact) {
        this.accountId = accountId;
        this.industryClassificationCode = industryClassificationCode;
        this.partyIdentifications = partyIdentifications;
        this.postalAddress = postalAddress;
        this.partyLegalEntity = partyLegalEntity;
        this.contact = contact;
    }

    public AccountId getAccountId() {
        return accountId;
    }

    public IndustryClassificationCode getIndustryClassificationCode() {
        return industryClassificationCode;
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

    // @formatter:off
    @Override
    public String toXML() {
        return "<cac:AccountingSupplierParty>" +
                    AccountId.toXML(accountId) +
                    "<cac:Party>" +
                        IndustryClassificationCode.toXML(industryClassificationCode) +
                        (partyIdentifications == null
                            ? ""
                            : Arrays.stream(partyIdentifications).map(PartyIdentification::toXML).collect(Collectors.joining())) +
                        postalAddress.toXML() +
                        partyLegalEntity.toXML() +
                        contact.toXML() +
                    "</cac:Party>" +
               "</cac:AccountingSupplierParty>";
    }
}
