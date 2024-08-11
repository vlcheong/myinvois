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

public final class PayeeFinancialAccount implements XMLElement {

    private final ID id;

    public PayeeFinancialAccount(ID id) {
        this.id = id;
    }

    public PayeeFinancialAccount(String id) {
        this(new ID(id));
    }

    public static PayeeFinancialAccount of(String id) {
        return new PayeeFinancialAccount(id);
    }

    public ID getId() {
        return id;
    }

    @Override
    public String toXML() {
        return "<cac:PayeeFinancialAccount>" + ID.toXML(id) + "</cac:PayeeFinancialAccount>";
    }
}