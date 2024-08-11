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

public final class TaxCategory implements XMLElement {

    private final ID id;

    private final BigDecimal percent;

    private final TaxScheme taxScheme;

    public TaxCategory(ID id, BigDecimal percent, TaxScheme taxScheme) {
        this.id = id;
        this.percent = percent;
        this.taxScheme = taxScheme;
    }

    public TaxCategory(String id, double percent, TaxScheme taxScheme) {
        this(new ID(id), BigDecimal.valueOf(percent), taxScheme);
    }

    public TaxCategory(String id, TaxScheme taxScheme) {
        this(new ID(id), null, taxScheme);
    }

    public ID getId() {
        return id;
    }

    public BigDecimal getPercent() {
        return percent;
    }

    public TaxScheme getTaxScheme() {
        return taxScheme;
    }

    // @formatter:off
    @Override
    public String toXML() {
        StringBuilder xml = new StringBuilder(255)
            .append("<cac:TaxCategory>")
            .append(ID.toXML(id))
            .append("<cbc:Percent>");
        if (percent == null) {
            xml.append("0.0");
        } else {
            xml.append(percent);
        }
        return xml.append("</cbc:Percent>")
                  .append(taxScheme.toXML())
                  .append("</cac:TaxCategory>")
                  .toString();
    }
}
