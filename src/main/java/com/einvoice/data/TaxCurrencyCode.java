package com.einvoice.data;

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

public final class TaxCurrencyCode implements XMLElement {

    private final String currencyCode;

    public TaxCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public static TaxCurrencyCode of(String currencyCode) {
        return new TaxCurrencyCode(currencyCode);
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public static String toXML(TaxCurrencyCode taxCurrencyCode) {
        return taxCurrencyCode == null ? "" : taxCurrencyCode.toXML();
    }

    @Override
    public String toXML() {
        return "<cbc:TaxCurrencyCode>" + XMLElement.escape(currencyCode) + "</cbc:TaxCurrencyCode>";
    }
}
