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

public final class X509Data implements XMLElement {

    private final X509Certificate x509Certificate;

    public X509Data(X509Certificate x509Certificate) {
        this.x509Certificate = x509Certificate;
    }

    public static X509Data of(X509Certificate x509Certificate) {
        return new X509Data(x509Certificate);
    }

    public static X509Data of(String cert) {
        return of(X509Certificate.of(cert));
    }

    public X509Certificate getX509Certificate() {
        return x509Certificate;
    }

    @Override
    public String toXML() {
        return "<ds:X509Data>" + x509Certificate.toXML() + "</ds:X509Data>";
    }
}
