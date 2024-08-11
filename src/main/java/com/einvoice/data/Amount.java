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

import java.math.BigDecimal;

public final class Amount {

    private final BigDecimal amount;

    private final String currencyId;

    public Amount(BigDecimal amount, String currencyId) {
        this.amount = amount;
        this.currencyId = currencyId;
    }

    public Amount(double amount, String currencyId) {
        this(BigDecimal.valueOf(amount), currencyId);
    }

    public Amount(long amount, String currencyId) {
        this(BigDecimal.valueOf(amount), currencyId);
    }

    public static Amount MYR(double amount) {
        return new Amount(BigDecimal.valueOf(amount), "MYR");
    }

    public static Amount MYR(long amount) {
        return new Amount(BigDecimal.valueOf(amount), "MYR");
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public String xml(String tag) {
        return "<" + tag + " currencyID=\"" + currencyId + "\">" + amount.toPlainString() + "</" + tag + ">";
    }
}
