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

package com.einvoice.extension;

import com.einvoice.base.XMLElement;

public final class SignedObject implements XMLElement {

    private final QualifyingProperties qualifyingProperties;

    public SignedObject(QualifyingProperties qualifyingProperties) {
        this.qualifyingProperties = qualifyingProperties;
    }

    public static SignedObject of(QualifyingProperties qualifyingProperties) {
        return new SignedObject(qualifyingProperties);
    }

    public QualifyingProperties getQualifyingProperties() {
        return qualifyingProperties;
    }

    // @formatter:off
    @Override
    public String toXML() {
        return "<ds:Object>" +
                    qualifyingProperties.toXML() +
               "</ds:Object>";
    }
}
