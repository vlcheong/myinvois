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

public final class UBLDocumentSignatures implements XMLElement {

    private final SignatureInformation signatureInformation;

    public UBLDocumentSignatures(SignatureInformation signatureInformation) {
        this.signatureInformation = signatureInformation;
    }

    public SignatureInformation getSignatureInformation() {
        return signatureInformation;
    }

    // @formatter:off
    @Override
    public String toXML() {
        return "<sig:UBLDocumentSignatures xmlns:sig=\"urn:oasis:names:specification:ubl:schema:xsd:CommonSignatureComponents-2\" xmlns:sac=\"urn:oasis:names:specification:ubl:schema:xsd:SignatureAggregateComponents-2\" xmlns:sbc=\"urn:oasis:names:specification:ubl:schema:xsd:SignatureBasicComponents-2\">" +
                    signatureInformation.toXML() +
                "</sig:UBLDocumentSignatures>";
    }
}
