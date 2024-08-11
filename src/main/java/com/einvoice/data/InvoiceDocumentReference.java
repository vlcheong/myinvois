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

public final class InvoiceDocumentReference extends DocumentReference {

    private final String uuid;

    public InvoiceDocumentReference(ID id, String uuid) {
        super(id);
        this.uuid = uuid;
    }

    public InvoiceDocumentReference(String id, String uuid) {
        this(new ID(id), uuid);
    }

    public static InvoiceDocumentReference of(String id, String uuid) {
        return new InvoiceDocumentReference(id, uuid);
    }

    public String getUUID() {
        return uuid;
    }

    // @formatter:off
    @Override
    public String toXML() {
        StringBuilder xml = new StringBuilder(255)
            .append("<cac:InvoiceDocumentReference>").append(super.toXML());
        if (uuid != null) {
            xml.append("<cbc:UUID>").append(XMLElement.escape(uuid)).append("</cbc:UUID>");
        }
        return xml.append("</cac:InvoiceDocumentReference>").toString();
    }
}
