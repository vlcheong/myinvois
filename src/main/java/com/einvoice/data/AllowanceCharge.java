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

public final class AllowanceCharge implements XMLElement {

    private final boolean chargeIndicator;

    private final String allowanceChargeReason;

    private final BigDecimal multiplierFactorNumeric;

    private final Amount amount;

    public AllowanceCharge(boolean chargeIndicator,
                           String allowanceChargeReason,
                           Double multiplierFactorNumeric,
                           Amount amount) {
        this.chargeIndicator = chargeIndicator;
        this.allowanceChargeReason = allowanceChargeReason;
        this.multiplierFactorNumeric = multiplierFactorNumeric == null ? null : BigDecimal.valueOf(multiplierFactorNumeric.doubleValue());
        this.amount = amount;
    }

    public AllowanceCharge(boolean chargeIndicator,
                           String allowanceChargeReason,
                           Amount amount) {
        this(chargeIndicator, allowanceChargeReason, null, amount);
    }

    public static AllowanceCharge[] of(AllowanceCharge... allowanceCharges) {
        int size = allowanceCharges == null ? 0 : allowanceCharges.length;
        return size == 0 ? new AllowanceCharge[0] : allowanceCharges;
    }

    public boolean isChargeIndicator() {
        return chargeIndicator;
    }

    public String getAllowanceChargeReason() {
        return allowanceChargeReason;
    }

    public BigDecimal getMultiplierFactorNumeric() {
        return multiplierFactorNumeric;
    }

    public Amount getAmount() {
        return amount;
    }

    // @formatter:off
    @Override
    public String toXML() {
        StringBuilder xml = new StringBuilder(255)
            .append("<cac:AllowanceCharge>")
            .append("<cbc:ChargeIndicator>").append((chargeIndicator ? "true" : "false")).append("</cbc:ChargeIndicator>")
            .append("<cbc:AllowanceChargeReason>").append(XMLElement.escape(allowanceChargeReason)).append("</cbc:AllowanceChargeReason>");
        if (multiplierFactorNumeric != null) {
            xml.append("<cbc:MultiplierFactorNumeric>").append(multiplierFactorNumeric).append("</cbc:MultiplierFactorNumeric>");
        }
        return xml.append(amount.xml("cbc:Amount")).append("</cac:AllowanceCharge>").toString();
    }
}
