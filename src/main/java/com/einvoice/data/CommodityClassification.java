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

public final class CommodityClassification implements XMLElement {

    private final String listId;

    private final String code;

    public CommodityClassification(String listId, String code) {
        this.listId = listId;
        this.code = code;
    }

    public static CommodityClassification of(String listId, String code) {
        return new CommodityClassification(listId, code);
    }

    public String getListId() {
        return listId;
    }

    public String getCode() {
        return code;
    }

    // @formatter:off
    @Override
    public String toXML() {
        return "<cac:CommodityClassification>" +
                    "<cbc:ItemClassificationCode listID=\"" + XMLElement.escape(listId, true) + "\">" + XMLElement.escape(code) + "</cbc:ItemClassificationCode>" +
               "</cac:CommodityClassification>";
    }
}
