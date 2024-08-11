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

import java.util.Base64;

public final class X509Certificate implements XMLElement {

    private final String cert;

    public X509Certificate(String cert) {
        this.cert = cert;
    }

    public static X509Certificate of(String cert) {
        return new X509Certificate(cert);
    }

    public static X509Certificate of(byte[] b) {
        return of(Base64.getEncoder().encodeToString(b));
    }

    public String getCert() {
        return cert;
    }

    @Override
    public String toXML() {
        return "<ds:X509Certificate>" + cert + "</ds:X509Certificate>";
    }
}
