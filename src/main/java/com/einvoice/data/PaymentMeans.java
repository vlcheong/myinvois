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

public final class PaymentMeans implements XMLElement {

    private final String paymentMeansCode;

    private final PayeeFinancialAccount payeeFinancialAccount;

    public PaymentMeans(String paymentMeansCode, PayeeFinancialAccount payeeFinancialAccount) {
        this.paymentMeansCode = paymentMeansCode;
        this.payeeFinancialAccount = payeeFinancialAccount;
    }

    public static PaymentMeans of(String paymentMeansCode, PayeeFinancialAccount payeeFinancialAccount) {
        return new PaymentMeans(paymentMeansCode, payeeFinancialAccount);
    }

    public String getPaymentMeansCode() {
        return paymentMeansCode;
    }

    public PayeeFinancialAccount getPayeeFinancialAccount() {
        return payeeFinancialAccount;
    }

    // @formatter:off
    @Override
    public String toXML() {
        StringBuilder xml = new StringBuilder(255)
            .append("<cac:PaymentMeans>");
        if (paymentMeansCode != null) {
            xml.append("<cbc:PaymentMeansCode>").append(XMLElement.escape(paymentMeansCode)).append("</cbc:PaymentMeansCode>");
        }
        if (payeeFinancialAccount != null) {
            xml.append(payeeFinancialAccount.toXML());
        }
        return xml.append("</cac:PaymentMeans>").toString();
    }
}
