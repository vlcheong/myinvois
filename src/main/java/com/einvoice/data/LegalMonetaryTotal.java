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

public final class LegalMonetaryTotal implements XMLElement {

    private final Amount lineExtensionAmount;

    private final Amount taxExclusiveAmount;

    private final Amount taxInclusiveAmount;

    private final Amount allowanceTotalAmount;

    private final Amount chargeTotalAmount;

    private final Amount payableRoundingAmount;

    private final Amount payableAmount;

    public LegalMonetaryTotal(Amount lineExtensionAmount,
                              Amount taxExclusiveAmount,
                              Amount taxInclusiveAmount,
                              Amount allowanceTotalAmount,
                              Amount chargeTotalAmount,
                              Amount payableRoundingAmount,
                              Amount payableAmount) {
        this.lineExtensionAmount = lineExtensionAmount;
        this.taxExclusiveAmount = taxExclusiveAmount;
        this.taxInclusiveAmount = taxInclusiveAmount;
        this.allowanceTotalAmount = allowanceTotalAmount;
        this.chargeTotalAmount = chargeTotalAmount;
        this.payableRoundingAmount = payableRoundingAmount;
        this.payableAmount = payableAmount;
    }

    public Amount getLineExtensionAmount() {
        return lineExtensionAmount;
    }

    public Amount getTaxExclusiveAmount() {
        return taxExclusiveAmount;
    }

    public Amount getTaxInclusiveAmount() {
        return taxInclusiveAmount;
    }

    public Amount getAllowanceTotalAmount() {
        return allowanceTotalAmount;
    }

    public Amount getChargeTotalAmount() {
        return chargeTotalAmount;
    }

    public Amount getPayableRoundingAmount() {
        return payableRoundingAmount;
    }

    public Amount getPayableAmount() {
        return payableAmount;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private Amount lineExtensionAmount;

        private Amount taxExclusiveAmount;

        private Amount taxInclusiveAmount;

        private Amount allowanceTotalAmount;

        private Amount chargeTotalAmount;

        private Amount payableRoundingAmount;

        private Amount payableAmount;

        private Builder() {
        }

        public Builder lineExtensionAmount(Amount lineExtensionAmount) {
            this.lineExtensionAmount = lineExtensionAmount;
            return this;
        }

        public Builder taxExclusiveAmount(Amount taxExclusiveAmount) {
            this.taxExclusiveAmount = taxExclusiveAmount;
            return this;
        }

        public Builder taxInclusiveAmount(Amount taxInclusiveAmount) {
            this.taxInclusiveAmount = taxInclusiveAmount;
            return this;
        }

        public Builder allowanceTotalAmount(Amount allowanceTotalAmount) {
            this.allowanceTotalAmount = allowanceTotalAmount;
            return this;
        }

        public Builder chargeTotalAmount(Amount chargeTotalAmount) {
            this.chargeTotalAmount = chargeTotalAmount;
            return this;
        }

        public Builder payableRoundingAmount(Amount payableRoundingAmount) {
            this.payableRoundingAmount = payableRoundingAmount;
            return this;
        }

        public Builder payableAmount(Amount payableAmount) {
            this.payableAmount = payableAmount;
            return this;
        }

        public LegalMonetaryTotal build() {
            return new LegalMonetaryTotal(
                lineExtensionAmount,
                taxExclusiveAmount,
                taxInclusiveAmount,
                allowanceTotalAmount,
                chargeTotalAmount,
                payableRoundingAmount,
                payableAmount
            );
        }
    }

    // @formatter:off
    @Override
    public String toXML() {
        return "<cac:LegalMonetaryTotal>" +
                    lineExtensionAmount.xml("cbc:LineExtensionAmount") +
                    taxExclusiveAmount.xml("cbc:TaxExclusiveAmount") +
                    taxInclusiveAmount.xml("cbc:TaxInclusiveAmount") +
                    allowanceTotalAmount.xml("cbc:AllowanceTotalAmount") +
                    chargeTotalAmount.xml("cbc:ChargeTotalAmount") +
                    payableRoundingAmount.xml("cbc:PayableRoundingAmount") +
                    payableAmount.xml("cbc:PayableAmount") +
                "</cac:LegalMonetaryTotal>";
    }
}
