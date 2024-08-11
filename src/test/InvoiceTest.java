package com.einvoice.data;

import com.einvoice.util.Dates;
import org.junit.jupiter.api.Test;

import java.time.Month;

public class InvoiceTest {

    @Test
    public void create() {
        InvoiceBuilder builder = Invoice.builder()
            .id("INV12345")
            .issueDateTime(Dates.toDateTime(2024, Month.JUNE, 26, 15, 30, 0))
            .invoiceTypeCode("1.1", "01")
            .documentCurrencyCode("MYR")
            .invoicePeriod(Dates.toDate(2017, Month.NOVEMBER, 26),
                Dates.toDate(2017, Month.NOVEMBER, 30),
                "Monthly")
            .billingReferences(
                InvoiceDocumentReference.of("INV54321", "F9D425P6DS7D8IU"),
                AdditionalDocumentReference.of("L1")
            )
            .additionalDocumentReferences(
                AdditionalDocumentReference.of("L1", "CustomsImportForm"),
                AdditionalDocumentReference.of("FTA", "FreeTradeAgreement", "Sample Description"),
                AdditionalDocumentReference.of("L1", "K2"),
                AdditionalDocumentReference.of("L1")
            )
            .accountingSupplierParty(accountingSupplierParty())
            .accountingCustomerParty(accountingCustomerParty())
            .paymentMeans(PaymentMeans.of("01", PayeeFinancialAccount.of("L1")))
            .paymentTerms(PaymentTerms.of("Cash"))
            .prepaidPayment("L1",
                Amount.MYR(1D),
                Dates.toDateTime(2000, Month.JANUARY, 1, 12, 0, 0)
            )
            .allowanceCharges(
                new AllowanceCharge(false, "Sample Description", Amount.MYR(100D)),
                new AllowanceCharge(true, "Sample Description", Amount.MYR(100D))
            )
            .taxTotal(taxTotal())
            .legalMonetaryTotal(legalMonetaryTotal())
            .invoiceLines(invoiceLine());
        Invoice invoice = builder.build();
        System.out.println(invoice.toXML());
        /*String signed = Signer.getSigner().sign(
            invoice,
            KeyStoreConfig.from(new File("cert.p12"), "BCNM34O2"));
        System.out.println(signed);*/
    }

    private AccountingSupplierParty accountingSupplierParty() {
        AccountId accountId = AccountId.of("CertEX");
        IndustryClassificationCode industryClassificationCode = IndustryClassificationCode.of(
            "Wholesale of computer hardware, software and peripherals", "46510");
        PartyIdentification[] partyIdentifications = PartyIdentification.of(
            PartyIdentification.of("C29823658030", "TIN"),
            PartyIdentification.of("202201020832", "BRN")
        );
        PostalAddress postalAddress = PostalAddress.builder()
            .cityName("Cyberjaya")
            .postalZone("63000")
            .countrySubentityCode("14")
            .addressLines("Persiaran Rimba Permai", "Cyber 8", "63000 Cyberjaya")
            .country("MYS", "ISO3166-1", "6")
            .build();
        PartyLegalEntity partyLegalEntity = PartyLegalEntity.of("AMS Setia Jaya Sdn. Bhd.");
        Contact contact = Contact.of("+969876543210", "xyz@test.com");
        return new AccountingSupplierParty(
            accountId,
            industryClassificationCode,
            partyIdentifications,
            postalAddress,
            partyLegalEntity,
            contact
        );
    }

    private AccountingCustomerParty accountingCustomerParty() {
        PartyIdentification[] partyIdentifications = PartyIdentification.of(
            PartyIdentification.of("C29967772090", "TIN"),
            PartyIdentification.of("202201043174", "BRN")
        );
        PostalAddress postalAddress = PostalAddress.builder()
            .cityName("Kuala Lumpur")
            .postalZone("50200")
            .countrySubentityCode("14")
            .addressLines("Lot 5 08", "5th Floor", "Wisma Cosway Jalan Raja Chulang")
            .country("MYS", "ISO3166-1", "6")
            .build();
        PartyLegalEntity partyLegalEntity = PartyLegalEntity.of("Chuan Sin Sdn. Bhd.");
        Contact contact = Contact.of("+969876543210", "xyz@test.com");
        return new AccountingCustomerParty(
            partyIdentifications,
            postalAddress,
            partyLegalEntity,
            contact
        );
    }

    private TaxTotal taxTotal() {
        Amount taxAmount = Amount.MYR(60D);
        TaxSubtotal taxSubtotal = new TaxSubtotal(
            Amount.MYR(60D),
            Amount.MYR(1000D),
            new TaxCategory(
                "01",
                new TaxScheme("OTH", "UN/ECE 5153", "6")
            )
        );
        return TaxTotal.of(taxAmount, taxSubtotal);
    }

    private LegalMonetaryTotal legalMonetaryTotal() {
        return LegalMonetaryTotal.builder()
            .lineExtensionAmount(Amount.MYR(1436.5D))
            .taxExclusiveAmount(Amount.MYR(1436.5D))
            .taxInclusiveAmount(Amount.MYR(1436.5D))
            .allowanceTotalAmount(Amount.MYR(1436.5D))
            .chargeTotalAmount(Amount.MYR(1436.5D))
            .payableRoundingAmount(Amount.MYR(.3D))
            .payableAmount(Amount.MYR(1436.5D))
            .build();
    }

    private InvoiceLine invoiceLine() {
        TaxSubtotal taxSubtotal = new TaxSubtotal(
            Amount.MYR(1000D),
            Amount.MYR(60D),
            new TaxCategory("01", 6D, new TaxScheme("OTH", "UN/ECE 5153", "6"))
        );
        Item item = new Item(
            "Laptop Peripherals",
            "MYS",
            CommodityClassification.of("PTC", "038"),
            CommodityClassification.of("CLASS", "023"),
            CommodityClassification.of("CLASS", "011")
        );
        return new InvoiceLine(
            ID.of("1234"),
            InvoicedQuantity.of("C62", 1L),
            Amount.MYR(1436.5D),
            AllowanceCharge.of(
                new AllowanceCharge(false, "Sample Description", .15D, new Amount(100D, "MYR")),
                new AllowanceCharge(true, "Sample Description", .1D, new Amount(100D, "MYR"))
            ),
            TaxTotal.of(Amount.MYR(60D), taxSubtotal),
            item,
            Amount.MYR(17D),
            Amount.MYR(100D)
        );
    }
}