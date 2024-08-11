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

public final class Signature implements XMLElement {

    private final ID id;

    private final String signatureMethod;

    public Signature(ID id, String signatureMethod) {
        this.id = id;
        this.signatureMethod = signatureMethod;
    }

    public Signature(String id, String signatureMethod) {
        this(new ID(id), signatureMethod);
    }

    public static Signature of(String id, String signatureMethod) {
        return new Signature(id, signatureMethod);
    }

    public ID getId() {
        return id;
    }

    public String getSignatureMethod() {
        return signatureMethod;
    }

    // @formatter:off
    @Override
    public String toXML() {
        return "<cac:Signature>" +
                    id.toXML() +
                    "<cbc:SignatureMethod>" + XMLElement.escape(signatureMethod) + "</cbc:SignatureMethod>" +
               "</cac:Signature>";
    }
}
