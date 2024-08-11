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

public final class InvoiceTypeCode implements XMLElement {

    private final String listVersionId;

    private final String code;

    public InvoiceTypeCode(String listVersionId, String code) {
        this.listVersionId = listVersionId;
        this.code = code;
    }

    public InvoiceTypeCode(String code) {
        this("1.0", code);
    }

    public String getListVersionId() {
        return listVersionId;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toXML() {
        return "<cbc:InvoiceTypeCode listVersionID=\"" + XMLElement.escape(listVersionId, true) + "\">" + XMLElement.escape(code) + "</cbc:InvoiceTypeCode>";
    }
}
