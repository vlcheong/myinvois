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

public final class Transform implements XMLElement {

    private final String algorithm;

    private final String xPath;

    public Transform(String algorithm, String xPath) {
        this.algorithm = algorithm;
        this.xPath = xPath;
    }

    public static Transform of(String algorithm, String xPath) {
        return new Transform(algorithm, xPath);
    }

    public static Transform of(String algorithm) {
        return of(algorithm, null);
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public String getXPath() {
        return xPath;
    }

    @Override
    public String toXML() {
        StringBuilder xml = new StringBuilder(255)
            .append("<ds:Transform Algorithm=\"").append(algorithm).append('"');
        if (xPath == null) {
            xml.append("/>");
        } else {
            xml.append("><ds:XPath>").append(xPath).append("</ds:XPath>").append("</ds:Transform>");
        }
        return xml.toString();
    }
}
