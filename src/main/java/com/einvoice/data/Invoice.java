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

import java.util.Arrays;
import java.util.stream.Collectors;

public final class Invoice implements XMLElement {

    private final ID id;

    private final IssueDate issueDate;

    private final IssueTime issueTime;

    private final InvoiceTypeCode invoiceTypeCode;

    private final DocumentCurrencyCode documentCurrencyCode;

    private final TaxCurrencyCode taxCurrencyCode;

    private final InvoicePeriod invoicePeriod;

    private final BillingReference[] billingReferences;

    private final AdditionalDocumentReference[] additionalDocumentReferences;

    private final AccountingSupplierParty accountingSupplierParty;

    private final AccountingCustomerParty accountingCustomerParty;

    private final PaymentMeans paymentMeans;

    private final PaymentTerms paymentTerms;

    private final PrepaidPayment prepaidPayment;

    private final AllowanceCharge[] allowanceCharges;

    private final Delivery delivery;

    private final TaxExchangeRate taxExchangeRate;

    private final TaxTotal taxTotal;

    private final LegalMonetaryTotal legalMonetaryTotal;

    private final InvoiceLine[] invoiceLines;

    Invoice(InvoiceBuilder builder) {
        this.id = builder.id;
        this.issueDate = builder.issueDate;
        this.issueTime = builder.issueTime;
        this.invoiceTypeCode = builder.invoiceTypeCode;
        this.documentCurrencyCode = builder.documentCurrencyCode;
        this.taxCurrencyCode = builder.taxCurrencyCode;
        this.invoicePeriod = builder.invoicePeriod;
        this.billingReferences = builder.billingReferences;
        this.additionalDocumentReferences = builder.additionalDocumentReferences;
        this.accountingSupplierParty = builder.accountingSupplierParty;
        this.accountingCustomerParty = builder.accountingCustomerParty;
        this.paymentMeans = builder.paymentMeans;
        this.paymentTerms = builder.paymentTerms;
        this.prepaidPayment = builder.prepaidPayment;
        this.allowanceCharges = builder.allowanceCharges;
        this.delivery = builder.delivery;
        this.taxExchangeRate = builder.taxExchangeRate;
        this.taxTotal = builder.taxTotal;
        this.legalMonetaryTotal = builder.legalMonetaryTotal;
        this.invoiceLines = builder.invoiceLines;
    }

    public static InvoiceBuilder builder() {
        return new InvoiceBuilder();
    }

    public ID getId() {
        return id;
    }

    public IssueDate getIssueDate() {
        return issueDate;
    }

    public IssueTime getIssueTime() {
        return issueTime;
    }

    public InvoiceTypeCode getInvoiceTypeCode() {
        return invoiceTypeCode;
    }

    public DocumentCurrencyCode getDocumentCurrencyCode() {
        return documentCurrencyCode;
    }

    public TaxCurrencyCode getTaxCurrencyCode() {
        return taxCurrencyCode;
    }

    public InvoicePeriod getInvoicePeriod() {
        return invoicePeriod;
    }

    public BillingReference[] getBillingReferences() {
        return billingReferences;
    }

    public AdditionalDocumentReference[] getAdditionalDocumentReferences() {
        return additionalDocumentReferences;
    }

    public AccountingSupplierParty getAccountingSupplierParty() {
        return accountingSupplierParty;
    }

    public AccountingCustomerParty getAccountingCustomerParty() {
        return accountingCustomerParty;
    }

    public PaymentMeans getPaymentMeans() {
        return paymentMeans;
    }

    public PaymentTerms getPaymentTerms() {
        return paymentTerms;
    }

    public PrepaidPayment getPrepaidPayment() {
        return prepaidPayment;
    }

    public AllowanceCharge[] getAllowanceCharges() {
        return allowanceCharges;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public TaxExchangeRate getTaxExchangeRate() {
        return taxExchangeRate;
    }

    public TaxTotal getTaxTotal() {
        return taxTotal;
    }

    public LegalMonetaryTotal getLegalMonetaryTotal() {
        return legalMonetaryTotal;
    }

    public InvoiceLine[] getInvoiceLines() {
        return invoiceLines;
    }

    // @formatter:off
    @Override
    public String toXML() {
        StringBuilder xml = new StringBuilder(4096)
            .append("<Invoice xmlns=\"urn:oasis:names:specification:ubl:schema:xsd:Invoice-2\" xmlns:cac=\"urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2\" xmlns:cbc=\"urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2\">")
            .append(ID.toXML(id))
            .append(issueDate.toXML())
            .append(issueTime.toXML())
            .append(invoiceTypeCode.toXML())
            .append(documentCurrencyCode.toXML())
            .append(TaxCurrencyCode.toXML(taxCurrencyCode))
            .append(InvoicePeriod.toXML(invoicePeriod));
        if (billingReferences != null) {
            xml.append(Arrays.stream(billingReferences).map(BillingReference::toXML).collect(Collectors.joining()));
        }
        if (additionalDocumentReferences != null) {
            xml.append(Arrays.stream(additionalDocumentReferences).map(AdditionalDocumentReference::toXML).collect(Collectors.joining()));
        }
        if (accountingSupplierParty != null) {
            xml.append(accountingSupplierParty.toXML());
        }
        if (accountingCustomerParty != null) {
            xml.append(accountingCustomerParty.toXML());
        }
        if (paymentMeans != null) {
            xml.append(paymentMeans.toXML());
        }
        if (paymentTerms != null) {
            xml.append(paymentTerms.toXML());
        }
        if (prepaidPayment != null) {
            xml.append(prepaidPayment.toXML());
        }
        if (allowanceCharges != null) {
            xml.append(Arrays.stream(allowanceCharges).map(AllowanceCharge::toXML).collect(Collectors.joining()));
        }
        if (delivery != null) {
            xml.append(delivery.toXML());
        }
        if (taxExchangeRate != null) {
            xml.append(taxExchangeRate.toXML());
        }
        if (taxTotal != null) {
            xml.append(taxTotal.toXML());
        }
        if (legalMonetaryTotal != null) {
            xml.append(legalMonetaryTotal.toXML());
        }
        if (invoiceLines != null) {
            xml.append(Arrays.stream(invoiceLines).map(InvoiceLine::toXML).collect(Collectors.joining()));
        }
        return xml.append("</Invoice>").toString();
    }
}
