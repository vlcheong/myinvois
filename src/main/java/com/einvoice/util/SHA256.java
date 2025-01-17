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

package com.einvoice.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class SHA256 {

    private static final String ALGORITHM = "SHA-256";

    private SHA256() {
    }

    public static byte[] hash(byte[] bytes) {
        try {
            MessageDigest md = MessageDigest.getInstance(ALGORITHM);
            return md.digest(bytes);
        } catch (NoSuchAlgorithmException e) {
            throw new SecurityException(e);
        }
    }

    public static byte[] hash(String str) {
        if (str == null) return new byte[]{};
        return hash(str.getBytes(StandardCharsets.UTF_8));
    }
}
