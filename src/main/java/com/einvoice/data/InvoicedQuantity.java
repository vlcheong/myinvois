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

import java.math.BigDecimal;

public final class InvoicedQuantity implements XMLElement {

    private final String unitCode;

    private final BigDecimal quantity;

    public InvoicedQuantity(String unitCode, BigDecimal quantity) {
        this.unitCode = unitCode;
        this.quantity = quantity;
    }

    public InvoicedQuantity(String unitCode, double quantity) {
        this(unitCode, BigDecimal.valueOf(quantity));
    }

    public InvoicedQuantity(String unitCode, long quantity) {
        this(unitCode, BigDecimal.valueOf(quantity));
    }

    public static InvoicedQuantity of(String unitCode, double quantity) {
        return new InvoicedQuantity(unitCode, quantity);
    }

    public static InvoicedQuantity of(String unitCode, long quantity) {
        return new InvoicedQuantity(unitCode, quantity);
    }

    public String getUnitCode() {
        return unitCode;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    @Override
    public String toXML() {
        return "<cbc:InvoicedQuantity unitCode=\"" + XMLElement.escape(unitCode, true) + "\">" + quantity + "</cbc:InvoicedQuantity>";
    }
}
