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
import com.einvoice.util.Dates;

import java.util.Date;

public final class InvoicePeriod implements XMLElement {

    private final String startDate;

    private final String endDate;

    private final String description;

    public InvoicePeriod(String startDate, String endDate, String description) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    public InvoicePeriod(Date startDate, Date endDate, String description) {
        this(Dates.format(startDate, "yyyy-MM-dd"),
            Dates.format(endDate, "HH:mm:ss"),
            description);
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getDescription() {
        return description;
    }

    public static String toXML(InvoicePeriod invoicePeriod) {
        return invoicePeriod == null ? "<cac:InvoicePeriod></cac:InvoicePeriod>" : invoicePeriod.toXML();
    }

    // @formatter:off
    @Override
    public String toXML() {
        return "<cac:InvoicePeriod>" +
                    "<cbc:StartDate>" + XMLElement.escape(startDate) + "</cbc:StartDate>" +
                    "<cbc:EndDate>" + XMLElement.escape(endDate) + "</cbc:EndDate>" +
                    "<cbc:Description>" + XMLElement.escape(description) + "</cbc:Description>" +
                "</cac:InvoicePeriod>";
    }
}
