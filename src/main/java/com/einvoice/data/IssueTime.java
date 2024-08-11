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
import com.einvoice.util.Dates;

import java.util.Date;

public final class IssueTime implements XMLElement {

    private final String issueTime;

    public IssueTime(String issueTime) {
        this.issueTime = issueTime;
    }

    public IssueTime(Date date) {
        this(Dates.format(date, "HH:mm:ss'Z'"));
    }

    public String getIssueTime() {
        return issueTime;
    }

    @Override
    public String toXML() {
        return "<cbc:IssueTime>" + XMLElement.escape(issueTime) + "</cbc:IssueTime>";
    }
}
