package com.einvoice.extension;

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

public final class Signature implements XMLElement {

    private final SignedInfo signedInfo;

    private final String signatureValue;

    private final KeyInfo keyInfo;

    private final SignedObject object;

    public Signature(SignedInfo signedInfo,
                     String signatureValue,
                     KeyInfo keyInfo,
                     SignedObject object) {
        this.signedInfo = signedInfo;
        this.signatureValue = signatureValue;
        this.keyInfo = keyInfo;
        this.object = object;
    }

    public SignedInfo getSignedInfo() {
        return signedInfo;
    }

    public String getSignatureValue() {
        return signatureValue;
    }

    public KeyInfo getKeyInfo() {
        return keyInfo;
    }

    public SignedObject getObject() {
        return object;
    }

    // @formatter:off
    @Override
    public String toXML() {
        return "<ds:Signature xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" Id=\"signature\">" +
                    signedInfo.toXML() +
                    "<ds:SignatureValue>" + signatureValue + "</ds:SignatureValue>" +
                    keyInfo.toXML() +
                    object.toXML() +
               "</ds:Signature>";
    }
}
