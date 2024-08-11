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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class PostalAddress implements XMLElement {

    private final String cityName;

    private final String postalZone;

    private final String countrySubentityCode;

    private final List<String> addressLines;

    private final Country country;

    public PostalAddress(String cityName,
                         String postalZone,
                         String countrySubentityCode,
                         List<String> addressLines,
                         Country country) {
        this.cityName = cityName;
        this.postalZone = postalZone;
        this.countrySubentityCode = countrySubentityCode;
        this.addressLines = addressLines;
        this.country = country;
    }

    public String getCityName() {
        return cityName;
    }

    public String getPostalZone() {
        return postalZone;
    }

    public String getCountrySubentityCode() {
        return countrySubentityCode;
    }

    public List<String> getAddressLines() {
        return addressLines;
    }

    public Country getCountry() {
        return country;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private String cityName;

        private String postalZone;

        private String countrySubentityCode;

        private final List<String> addressLines;

        private Country country;

        private Builder() {
            this.addressLines = new ArrayList<>();
        }

        public Builder cityName(String cityName) {
            this.cityName = cityName;
            return this;
        }

        public Builder postalZone(String postalZone) {
            this.postalZone = postalZone;
            return this;
        }

        public Builder countrySubentityCode(String countrySubentityCode) {
            this.countrySubentityCode = countrySubentityCode;
            return this;
        }

        public Builder addressLines(String... lines) {
            int size = lines == null ? 0 : lines.length;
            if (size > 0) {
                this.addressLines.addAll(Arrays.asList(lines).subList(0, size));
            }
            return this;
        }

        public Builder country(String identificationCode, String listId, String listAgencyId) {
            this.country = new Country(identificationCode, listId, listAgencyId);
            return this;
        }

        public Builder country(Country country) {
            this.country = country;
            return this;
        }

        public PostalAddress build() {
            return new PostalAddress(cityName,
                postalZone,
                countrySubentityCode,
                addressLines,
                country);
        }
    }

    // @formatter:off
    public static String toXML(PostalAddress address) {
        if (address == null) {
            return "<cac:PostalAddress>" +
                        "<cbc:CityName/>" +
                        "<cbc:PostalZone/>" +
                        "<cbc:CountrySubentityCode/>" +
                        "<cac:AddressLine><cbc:Line/></cac:AddressLine>" +
                        Country.toXML(null) +
                    "</cac:PostalAddress>";
        }
        return address.toXML();
    }

    // @formatter:off
    @Override
    public String toXML() {
        StringBuilder xml = new StringBuilder(255);
        xml.append("<cac:PostalAddress>");
        if (cityName == null || cityName.isBlank()) {
            xml.append("<cbc:CityName></cbc:CityName>");
        } else {
            xml.append("<cbc:CityName>")
               .append(XMLElement.escape(cityName))
               .append("</cbc:CityName>");
        }
        if (postalZone == null || postalZone.isBlank()) {
            xml.append("<cbc:PostalZone></cbc:PostalZone>");
        } else {
            xml.append("<cbc:PostalZone>")
               .append(XMLElement.escape(postalZone))
               .append("</cbc:PostalZone>");
        }

        if (countrySubentityCode == null || countrySubentityCode.isBlank()) {
            xml.append("<cbc:CountrySubentityCode></cbc:CountrySubentityCode>");
        } else {
            xml.append("<cbc:CountrySubentityCode>")
               .append(XMLElement.escape(countrySubentityCode))
               .append("</cbc:CountrySubentityCode>");
        }
        int size = addressLines == null ? 0 : addressLines.size();
        if (size == 0) {
            xml.append("<cac:AddressLine><cbc:Line></cbc:Line></cac:AddressLine>");
        } else {
            for (int i = 0; i < size; i++) {
                xml.append("<cac:AddressLine><cbc:Line>")
                   .append(XMLElement.escape(addressLines.get(i)))
                   .append("</cbc:Line></cac:AddressLine>");
            }
        }
        return xml.append(Country.toXML(country)).append("</cac:PostalAddress>").toString();
    }
}
