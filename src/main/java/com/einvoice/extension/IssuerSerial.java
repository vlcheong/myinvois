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

public final class IssuerSerial implements XMLElement {

    private final String issuerName;

    private final String serialNumber;

    public IssuerSerial(String issuerName, String serialNumber) {
        this.issuerName = issuerName;
        this.serialNumber = serialNumber;
    }

    public static IssuerSerial of(String issuerName, String serialNumber) {
        return new IssuerSerial(issuerName, serialNumber);
    }

    public String getIssuerName() {
        return issuerName;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    // @formatter:off
    public String signature() {
        return "<xades:IssuerSerial>" +
                    "<ds:X509IssuerName xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\">" + issuerName + "</ds:X509IssuerName>" +
                    "<ds:X509SerialNumber xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\">" + serialNumber + "</ds:X509SerialNumber>" +
               "</xades:IssuerSerial>";
    }

    // @formatter:off
    @Override
    public String toXML() {
        return "<xades:IssuerSerial>" +
                    "<ds:X509IssuerName>" + issuerName + "</ds:X509IssuerName>" +
                    "<ds:X509SerialNumber>" + serialNumber + "</ds:X509SerialNumber>" +
               "</xades:IssuerSerial>";
    }
}
