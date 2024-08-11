package com.einvoice.data;

import com.einvoice.base.XMLElement;

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

public final class AdditionalDocumentReference extends DocumentReference {

    private final String documentType;

    private final String documentDescription;

    public AdditionalDocumentReference(ID id, String documentType, String documentDescription) {
        super(id);
        this.documentType = documentType;
        this.documentDescription = documentDescription;
    }

    public AdditionalDocumentReference(String id, String documentType, String documentDescription) {
        this(new ID(id), documentType, documentDescription);
    }

    public AdditionalDocumentReference(String id) {
        this(new ID(id), null, null);
    }

    public AdditionalDocumentReference(String id, String documentType) {
        this(new ID(id), documentType, null);
    }

    public static AdditionalDocumentReference of(String id) {
        return new AdditionalDocumentReference(id);
    }

    public static AdditionalDocumentReference of(String id, String documentType) {
        return new AdditionalDocumentReference(id, documentType);
    }

    public static AdditionalDocumentReference of(String id, String documentType, String documentDescription) {
        return new AdditionalDocumentReference(id, documentType, documentDescription);
    }

    public String getDocumentType() {
        return documentType;
    }

    public String getDocumentDescription() {
        return documentDescription;
    }

    // @formatter:off
    @Override
    public String toXML() {
        StringBuilder xml = new StringBuilder(255)
            .append("<cac:AdditionalDocumentReference>").append(super.toXML());
        if (documentType != null) {
            xml.append("<cbc:DocumentType>").append(XMLElement.escape(documentType)).append("</cbc:DocumentType>");
        }
        if (documentDescription != null) {
            xml.append("<cbc:DocumentDescription>").append(XMLElement.escape(documentDescription)).append("</cbc:DocumentDescription>");
        }
        return xml.append("</cac:AdditionalDocumentReference>").toString();
    }
}
