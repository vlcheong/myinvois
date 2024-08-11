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

package com.einvoice.base;

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

public interface XMLElement {

    String toXML();

    static String escape(String data) {
        return escape(data, false);
    }

    static String escape(String data, boolean isAttValue) {
        if (data == null) return "";
        String text = data.trim();
        int length = text.length();
        if (length == 0) return "";
        int capacity = (int) Math.ceil(length * 1.1D);
        StringBuilder sb = new StringBuilder(capacity);
        for (int i = 0; i < length; ) {
            int c = text.codePointAt(i);
            if (c < 0x80) {
                if (c < 0x20 && (c != '\t' && c != '\r' && c != '\n')) {
                    // Illegal XML character, even encoded. Skip or substitute
                    sb.append("&#xfffd;"); // Unicode replacement character
                } else {
                    switch (c) {
                        case '&':
                            sb.append("&amp;");
                            break;
                        case '>':
                            sb.append("&gt;");
                            break;
                        case '<':
                            sb.append("&lt;");
                            break;
                        case '\'':
                            if (isAttValue) {
                                sb.append("&apos;");
                            } else {
                                sb.append((char) c);
                            }
                            break;
                        case '\"':
                            if (isAttValue) {
                                sb.append("&quot;");
                            } else {
                                sb.append((char) c);
                            }
                            break;
                        default:
                            sb.append((char) c);
                    }
                }
            } else if ((c >= 0xd800 && c <= 0xdfff) || c == 0xfffe || c == 0xffff) {
                // Illegal XML character, even encoded. Skip or substitute
                sb.append("&#xfffd;"); // Unicode replacement character
            } else {
                sb.append("&#x")
                    .append(Integer.toHexString(c))
                    .append(';');
            }
            i += c <= 0xffff ? 1 : 2;
        }
        return sb.toString();
    }
}
