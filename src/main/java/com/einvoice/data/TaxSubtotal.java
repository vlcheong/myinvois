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

public final class TaxSubtotal implements XMLElement {

    private final Amount taxableAmount;

    private final Amount taxAmount;

    private final TaxCategory taxCategory;

    public TaxSubtotal(Amount taxableAmount, Amount taxAmount, TaxCategory taxCategory) {
        this.taxableAmount = taxableAmount;
        this.taxAmount = taxAmount;
        this.taxCategory = taxCategory;
    }

    public Amount getTaxableAmount() {
        return taxableAmount;
    }

    public Amount getTaxAmount() {
        return taxAmount;
    }

    public TaxCategory getTaxCategory() {
        return taxCategory;
    }

    // @formatter:off
    @Override
    public String toXML() {
        return "<cac:TaxSubtotal>" +
                    taxableAmount.xml("cbc:TaxableAmount") +
                    taxAmount.xml("cbc:TaxAmount") +
                    taxCategory.toXML() +
               "</cac:TaxSubtotal>";
    }
}
