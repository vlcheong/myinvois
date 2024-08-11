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

public final class Item implements XMLElement {

    private final String description;

    private final String originCountryCode;

    private final CommodityClassification[] commodityClassifications;

    public Item(String description, String originCountryCode, CommodityClassification... commodityClassifications) {
        this.description = description;
        this.originCountryCode = originCountryCode;
        int size = commodityClassifications.length;
        if (size == 0)
            this.commodityClassifications = new CommodityClassification[]{};
        else {
            this.commodityClassifications = commodityClassifications;
        }
    }

    public String getDescription() {
        return description;
    }

    public String getOriginCountryCode() {
        return originCountryCode;
    }

    public CommodityClassification[] getCommodityClassifications() {
        return commodityClassifications;
    }

    // @formatter:off
    @Override
    public String toXML() {
        StringBuilder xml = new StringBuilder(255)
            .append("<cac:Item>")
            .append("<cbc:Description>").append(XMLElement.escape(description)).append("</cbc:Description>")
            .append("<cac:OriginCountry>")
            .append("<cbc:IdentificationCode>");
        if (originCountryCode != null && !originCountryCode.isBlank()) {
            xml.append(XMLElement.escape(originCountryCode));
        }
        xml.append("</cbc:IdentificationCode></cac:OriginCountry>");
        int size = commodityClassifications.length;
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                xml.append(commodityClassifications[i].toXML());
            }
        }
        return xml.append("</cac:Item>").toString();
    }
}
