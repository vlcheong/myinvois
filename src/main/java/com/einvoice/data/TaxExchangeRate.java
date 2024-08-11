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

import java.math.BigDecimal;

public final class TaxExchangeRate implements XMLElement {

    private final String sourceCurrencyCode;

    private final String targetCurrencyCode;

    private final BigDecimal calculationRate;

    public TaxExchangeRate(String sourceCurrencyCode,
                           String targetCurrencyCode,
                           BigDecimal calculationRate) {
        this.sourceCurrencyCode = sourceCurrencyCode;
        this.targetCurrencyCode = targetCurrencyCode;
        this.calculationRate = calculationRate;
    }

    public static TaxExchangeRate of(String sourceCurrencyCode,
                                     String targetCurrencyCode,
                                     double calculationRate) {
        return new TaxExchangeRate(sourceCurrencyCode, targetCurrencyCode, BigDecimal.valueOf(calculationRate));
    }

    public static TaxExchangeRate of(String sourceCurrencyCode,
                                     String targetCurrencyCode,
                                     long calculationRate) {
        return new TaxExchangeRate(sourceCurrencyCode, targetCurrencyCode, BigDecimal.valueOf(calculationRate));
    }

    public String getSourceCurrencyCode() {
        return sourceCurrencyCode;
    }

    public String getTargetCurrencyCode() {
        return targetCurrencyCode;
    }

    public BigDecimal getCalculationRate() {
        return calculationRate;
    }

    // @formatter:off
    @Override
    public String toXML() {
        return "<cac:TaxExchangeRate>" +
                    "<cbc:SourceCurrencyCode>" + XMLElement.escape(sourceCurrencyCode) + "</cbc:SourceCurrencyCode>" +
                    "<cbc:TargetCurrencyCode>" + XMLElement.escape(targetCurrencyCode) + "</cbc:TargetCurrencyCode>" +
                    "<cbc:CalculationRate>" + calculationRate + "</cbc:CalculationRate>" +
                "</cac:TaxExchangeRate>";
    }
}
