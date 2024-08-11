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

import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public abstract class Dates {

    public static final TimeZone UTC = TimeZone.getTimeZone("UTC");

    private Dates() {
    }

    public static Date toDate(int year, Month month, int dayOfMonth) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month.ordinal());
        cal.set(Calendar.DATE, dayOfMonth);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    public static Date toTime(int hour, int minute, int second) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);
        return cal.getTime();
    }

    public static Date toDateTime(int year, Month month, int dayOfMonth, int hour, int minute, int second) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month.ordinal());
        cal.set(Calendar.DATE, dayOfMonth);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);
        return cal.getTime();
    }

    public static String format(Date date, String pattern) {
        return format(date, pattern, TimeZone.getDefault());
    }

    public static String format(Date date, String pattern, TimeZone tz) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        formatter.setTimeZone(tz);
        return formatter.format(date);
    }
}
