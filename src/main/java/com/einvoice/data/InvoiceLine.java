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

import java.util.List;

public final class InvoiceLine implements XMLElement {

    private final ID id;

    private final InvoicedQuantity invoicedQuantity;

    private final Amount lineExtensionAmount;

    private final AllowanceCharge[] allowanceCharges;

    private final TaxTotal taxTotal;

    private final Item item;

    private final Amount price;

    private final Amount itemPriceExtension;

    public InvoiceLine(ID id,
                       InvoicedQuantity invoicedQuantity,
                       Amount lineExtensionAmount,
                       AllowanceCharge[] allowanceCharges,
                       TaxTotal taxTotal,
                       Item item,
                       Amount price,
                       Amount itemPriceExtension) {
        this.id = id;
        this.invoicedQuantity = invoicedQuantity;
        this.lineExtensionAmount = lineExtensionAmount;
        this.allowanceCharges = allowanceCharges;
        this.taxTotal = taxTotal;
        this.item = item;
        this.price = price;
        this.itemPriceExtension = itemPriceExtension;
    }

    public ID getId() {
        return id;
    }

    public InvoicedQuantity getInvoicedQuantity() {
        return invoicedQuantity;
    }

    public Amount getLineExtensionAmount() {
        return lineExtensionAmount;
    }

    public AllowanceCharge[] getAllowanceCharges() {
        return allowanceCharges;
    }

    public TaxTotal getTaxTotal() {
        return taxTotal;
    }

    public Item getItem() {
        return item;
    }

    public Amount getPrice() {
        return price;
    }

    public Amount getItemPriceExtension() {
        return itemPriceExtension;
    }

    // @formatter:off
    @Override
    public String toXML() {
        StringBuilder xml = new StringBuilder(255);
        xml.append("<cac:InvoiceLine>")
           .append(ID.toXML(id))
           .append(invoicedQuantity.toXML())
           .append(lineExtensionAmount.xml("cbc:LineExtensionAmount"));
        int size = allowanceCharges == null ? 0 : allowanceCharges.length;
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                xml.append(allowanceCharges[i].toXML());
            }
        }
        xml.append(taxTotal.toXML())
           .append(item.toXML())
           .append("<cac:Price>").append(price.xml("cbc:PriceAmount")).append("</cac:Price>");
        if (itemPriceExtension != null) {
            xml.append("<cac:ItemPriceExtension>").append(itemPriceExtension.xml("cbc:Amount")).append("</cac:ItemPriceExtension>");
        }
        return xml.append("</cac:InvoiceLine>").toString();
    }
}
