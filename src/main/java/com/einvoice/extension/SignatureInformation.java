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
import com.einvoice.data.ID;

public final class SignatureInformation implements XMLElement {

    private final ID id;

    private final String referencedSignatureId;

    private final Signature signature;

    public SignatureInformation(ID id,
                                String referencedSignatureId,
                                Signature signature) {
        this.id = id;
        this.referencedSignatureId = referencedSignatureId;
        this.signature = signature;
    }

    public SignatureInformation(String id,
                                String referencedSignatureId,
                                Signature signature) {
        this(new ID(id), referencedSignatureId, signature);
    }

    public ID getId() {
        return id;
    }

    public String getReferencedSignatureId() {
        return referencedSignatureId;
    }

    public Signature getSignature() {
        return signature;
    }

    // @formatter:off
    @Override
    public String toXML() {
        return "<sac:SignatureInformation>" +
                    id.toXML() +
                    "<sbc:ReferencedSignatureID>" + referencedSignatureId + "</sbc:ReferencedSignatureID>" +
                    signature.toXML() +
                "</sac:SignatureInformation>";
    }
}
