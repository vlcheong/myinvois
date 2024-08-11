package com.einvoice.extension;

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

import java.util.Arrays;
import java.util.stream.Collectors;

public final class UBLExtensions implements XMLElement {

    private final UBLExtension[] extensions;

    public UBLExtensions(UBLExtension... extensions) {
        int size = extensions == null ? 0 : extensions.length;
        if (size == 0) {
            this.extensions = new UBLExtension[]{};
        } else {
            this.extensions = extensions;
        }
    }

    public UBLExtension[] getExtensions() {
        return extensions;
    }

    // @formatter:off
    @Override
    public String toXML() {
        if (extensions != null) {
            return "<ext:UBLExtensions xmlns:ext=\"urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2\">" +
                        Arrays.stream(extensions).map(UBLExtension::toXML).collect(Collectors.joining()) +
                    "</ext:UBLExtensions>";
        }
        return "";
    }
}
