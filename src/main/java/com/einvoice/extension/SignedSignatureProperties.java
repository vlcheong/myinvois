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
import com.einvoice.util.Dates;

import java.util.Date;

public final class SignedSignatureProperties implements XMLElement {

    private final String signingTime;

    private final SigningCertificate signingCertificate;

    public SignedSignatureProperties(String signingTime,
                                     SigningCertificate signingCertificate) {
        this.signingTime = signingTime;
        this.signingCertificate = signingCertificate;
    }

    public static SignedSignatureProperties of(String signingTime, SigningCertificate signingCertificate) {
        return new SignedSignatureProperties(signingTime, signingCertificate);
    }

    public static SignedSignatureProperties of(Date date, SigningCertificate signingCertificate) {
        return new SignedSignatureProperties(
            Dates.format(date, "yyyy-MM-dd'T'HH:mm:ss'Z'", Dates.UTC),
            signingCertificate);
    }

    public String getSigningTime() {
        return signingTime;
    }

    public SigningCertificate getSigningCertificate() {
        return signingCertificate;
    }

    // @formatter:off
    public String signature() {
        return "<xades:SignedSignatureProperties>" +
                    "<xades:SigningTime>" + signingTime + "</xades:SigningTime>" +
                    signingCertificate.signature() +
                "</xades:SignedSignatureProperties>";
    }

    // @formatter:off
    @Override
    public String toXML() {
        return "<xades:SignedSignatureProperties>" +
                    "<xades:SigningTime>" + signingTime + "</xades:SigningTime>" +
                    signingCertificate.toXML() +
                "</xades:SignedSignatureProperties>";
    }
}
