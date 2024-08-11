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

public final class ID implements XMLElement {

    private final String id;

    public ID(String id) {
        this.id = id;
    }

    public static ID of(String id) {
        return new ID(id);
    }

    public String id() {
        return this.id;
    }

    public static String toXML(ID id) {
        if (id == null) {
            return "<cbc:ID></cbc:ID>";
        }
        if (id.id() == null || id.id().isBlank()) {
            return "<cbc:ID></cbc:ID>";
        }
        return id.toXML();
    }

    @Override
    public String toXML() {
        return "<cbc:ID>" + XMLElement.escape(id) + "</cbc:ID>";
    }
}
