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

import java.util.Date;

public final class InvoiceBuilder {

    ID id;

    IssueDate issueDate;

    IssueTime issueTime;

    InvoiceTypeCode invoiceTypeCode;

    DocumentCurrencyCode documentCurrencyCode;

    TaxCurrencyCode taxCurrencyCode;

    InvoicePeriod invoicePeriod;

    BillingReference[] billingReferences;

    AdditionalDocumentReference[] additionalDocumentReferences;

    AccountingSupplierParty accountingSupplierParty;

    AccountingCustomerParty accountingCustomerParty;

    PaymentMeans paymentMeans;

    PaymentTerms paymentTerms;

    PrepaidPayment prepaidPayment;

    AllowanceCharge[] allowanceCharges;

    Delivery delivery;

    TaxExchangeRate taxExchangeRate;

    TaxTotal taxTotal;

    LegalMonetaryTotal legalMonetaryTotal;

    InvoiceLine[] invoiceLines;

    InvoiceBuilder() {
    }

    public InvoiceBuilder id(ID id) {
        this.id = id;
        return this;
    }

    public InvoiceBuilder id(String id) {
        this.id = new ID(id);
        return this;
    }

    public InvoiceBuilder issueDate(IssueDate issueDate) {
        this.issueDate = issueDate;
        return this;
    }

    public InvoiceBuilder issueDate(Date date) {
        this.issueDate = new IssueDate(date);
        return this;
    }

    public InvoiceBuilder issueTime(IssueTime issueTime) {
        this.issueTime = issueTime;
        return this;
    }

    public InvoiceBuilder issueTime(Date date) {
        this.issueTime = new IssueTime(date);
        return this;
    }

    public InvoiceBuilder issueDateTime(Date datetime) {
        this.issueDate = new IssueDate(datetime);
        this.issueTime = new IssueTime(datetime);
        return this;
    }

    public InvoiceBuilder invoiceTypeCode(InvoiceTypeCode invoiceTypeCode) {
        this.invoiceTypeCode = invoiceTypeCode;
        return this;
    }

    public InvoiceBuilder invoiceTypeCode(String listVersionId, String code) {
        this.invoiceTypeCode = new InvoiceTypeCode(listVersionId, code);
        return this;
    }

    public InvoiceBuilder documentCurrencyCode(DocumentCurrencyCode documentCurrencyCode) {
        this.documentCurrencyCode = documentCurrencyCode;
        return this;
    }

    public InvoiceBuilder documentCurrencyCode(String currencyCode) {
        this.documentCurrencyCode = new DocumentCurrencyCode(currencyCode);
        return this;
    }

    public InvoiceBuilder taxCurrencyCode(String currencyCode) {
        this.taxCurrencyCode = new TaxCurrencyCode(currencyCode);
        return this;
    }

    public InvoiceBuilder invoicePeriod(InvoicePeriod invoicePeriod) {
        this.invoicePeriod = invoicePeriod;
        return this;
    }

    public InvoiceBuilder invoicePeriod(Date startDate, Date endDate, String description) {
        this.invoicePeriod = new InvoicePeriod(startDate, endDate, description);
        return this;
    }

    public InvoiceBuilder billingReferences(DocumentReference... refs) {
        int size = refs == null ? 0 : refs.length;
        if (size > 0) {
            this.billingReferences = new BillingReference[size];
            for (int i = 0; i < size; i++) {
                this.billingReferences[i] = new BillingReference(refs[i]);
            }
        } else {
            this.billingReferences = new BillingReference[0];
        }
        return this;
    }

    public InvoiceBuilder additionalDocumentReferences(AdditionalDocumentReference... refs) {
        int size = refs == null ? 0 : refs.length;
        if (size > 0) {
            this.additionalDocumentReferences = new AdditionalDocumentReference[size];
            for (int i = 0; i < size; i++) {
                this.additionalDocumentReferences[i] = refs[i];
            }
        } else {
            this.additionalDocumentReferences = new AdditionalDocumentReference[0];
        }
        return this;
    }

    public InvoiceBuilder accountingSupplierParty(AccountingSupplierParty party) {
        this.accountingSupplierParty = party;
        return this;
    }

    public InvoiceBuilder accountingCustomerParty(AccountingCustomerParty party) {
        this.accountingCustomerParty = party;
        return this;
    }

    public InvoiceBuilder paymentTerms(PaymentTerms paymentTerms) {
        this.paymentTerms = paymentTerms;
        return this;
    }

    public InvoiceBuilder prepaidPayment(PrepaidPayment prepaidPayment) {
        this.prepaidPayment = prepaidPayment;
        return this;
    }

    public InvoiceBuilder prepaidPayment(String id,
                                         Amount paidAmount,
                                         Date paidDateTime) {
        this.prepaidPayment = new PrepaidPayment(new ID(id), paidAmount, paidDateTime);
        return this;
    }

    public InvoiceBuilder paymentMeans(PaymentMeans paymentMeans) {
        this.paymentMeans = paymentMeans;
        return this;
    }

    public InvoiceBuilder allowanceCharges(AllowanceCharge... allowanceCharges) {
        int size = allowanceCharges == null ? 0 : allowanceCharges.length;
        if (size > 0) {
            this.allowanceCharges = new AllowanceCharge[size];
            for (int i = 0; i < size; i++) {
                this.allowanceCharges[i] = allowanceCharges[i];
            }
        } else {
            this.allowanceCharges = new AllowanceCharge[0];
        }
        return this;
    }

    public InvoiceBuilder delivery(Delivery delivery) {
        this.delivery = delivery;
        return this;
    }

    public InvoiceBuilder taxExchangeRate(String sourceCurrencyCode,
                                          String targetCurrencyCode,
                                          double calculationRate) {
        this.taxExchangeRate = TaxExchangeRate.of(sourceCurrencyCode, targetCurrencyCode, calculationRate);
        return this;
    }

    public InvoiceBuilder taxExchangeRate(String sourceCurrencyCode,
                                          String targetCurrencyCode,
                                          long calculationRate) {
        this.taxExchangeRate = TaxExchangeRate.of(sourceCurrencyCode, targetCurrencyCode, calculationRate);
        return this;
    }

    public InvoiceBuilder taxTotal(TaxTotal taxTotal) {
        this.taxTotal = taxTotal;
        return this;
    }

    public InvoiceBuilder legalMonetaryTotal(LegalMonetaryTotal legalMonetaryTotal) {
        this.legalMonetaryTotal = legalMonetaryTotal;
        return this;
    }

    public InvoiceBuilder invoiceLines(InvoiceLine... lines) {
        int size = lines == null ? 0 : lines.length;
        if (size > 0) {
            this.invoiceLines = new InvoiceLine[size];
            for (int i = 0; i < size; i++) {
                this.invoiceLines[i] = lines[i];
            }
        } else {
            this.invoiceLines = new InvoiceLine[0];
        }
        return this;
    }

    public Invoice build() {
        return new Invoice(this);
    }
}
