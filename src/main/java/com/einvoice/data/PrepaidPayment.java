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
import com.einvoice.util.Dates;

import java.util.Date;

public final class PrepaidPayment implements XMLElement {

    private final ID id;

    private final Amount paidAmount;

    private final String paidDate;

    private final String paidTime;

    public PrepaidPayment(ID id,
                          Amount paidAmount,
                          String paidDate,
                          String paidTime) {
        this.id = id;
        this.paidAmount = paidAmount;
        this.paidDate = paidDate;
        this.paidTime = paidTime;
    }

    public PrepaidPayment(ID id,
                          Amount paidAmount,
                          Date paidDateTime) {
        this(id,
            paidAmount,
            Dates.format(paidDateTime, "yyyy-MM-dd"),
            Dates.format(paidDateTime, "HH:mm:ss"));
    }

    public ID getId() {
        return id;
    }

    public Amount getPaidAmount() {
        return paidAmount;
    }

    public String getPaidDate() {
        return paidDate;
    }

    public String getPaidTime() {
        return paidTime;
    }

    // @formatter:off
    @Override
    public String toXML() {
        return "<cac:PrepaidPayment>" +
                    id.toXML() +
                    paidAmount.xml("cbc:PaidAmount") +
                    "<cbc:PaidDate>" + XMLElement.escape(paidDate) + "</cbc:PaidDate>" +
                    "<cbc:PaidTime>" + XMLElement.escape(paidTime) + "</cbc:PaidTime>" +
                "</cac:PrepaidPayment>";
    }
}



