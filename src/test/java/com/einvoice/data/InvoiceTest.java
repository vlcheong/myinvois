package com.einvoice.data;

import com.einvoice.util.Dates;
import org.junit.jupiter.api.Test;

import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

public class InvoiceTest {

    @Test
    public void testCreate() {
        Invoice invoice = create();
        System.out.println(invoice.toXML());
    }

    private Invoice create() {
        InvoiceBuilder builder = Invoice.builder()
            .id("INV12345")
            .issueDateTime(Dates.toDateTime(2024, Month.AUGUST, 10, 11, 35, 13))
            .invoiceTypeCode("1.1", "04")
            .documentCurrencyCode("MYR")
            .taxCurrencyCode("MYR")
            .invoicePeriod(Dates.toDate(2024, Month.AUGUST, 1),
                Dates.toDate(2024, Month.AUGUST, 31),
                "Monthly")
            .billingReferences(
                AdditionalDocumentReference.of("L1"),
                InvoiceDocumentReference.of("NA", "NA"),
                InvoiceDocumentReference.of("INV54321", "F9D425P6DS7D8IU")
            )
            .accountingSupplierParty(accountingSupplierParty())
            .accountingCustomerParty(accountingCustomerParty())
            .paymentMeans(PaymentMeans.of("01", PayeeFinancialAccount.of("L1")))
            .paymentTerms(PaymentTerms.of("Cash"))
            .prepaidPayment("L1",
                Amount.MYR(1D),
                Dates.toDateTime(2024, Month.AUGUST, 1, 12, 0, 0)
            )
            .delivery(new Delivery(new DeliveryParty()
                .postalAddress(PostalAddress.builder().build())
                .partyLegalEntity(PartyLegalEntity.of(""))
            ))
            .taxExchangeRate("MYR", "MYR", 1D)
            .taxTotal(taxTotal())
            .legalMonetaryTotal(legalMonetaryTotal())
            .invoiceLines(invoiceLines());
        return builder.build();
    }

    private AccountingSupplierParty accountingSupplierParty() {
        IndustryClassificationCode industryClassificationCode = IndustryClassificationCode.of(
            "Wholesale of computer hardware, software and peripherals",
            "46510");
        PartyIdentification[] partyIdentifications = PartyIdentification.of(
            PartyIdentification.of("C29823658030", "TIN"),
            PartyIdentification.of("202201020832", "BRN")
        );
        PostalAddress postalAddress = PostalAddress.builder()
            .cityName("Cyberjaya")
            .postalZone("63000")
            .countrySubentityCode("14")
            .addressLines(
                "Persiaran Rimba Permai",
                "Cyber 8,",
                "63000 Cyberjaya"
            )
            .country("MYS", "ISO3166-1", "6")
            .build();
        PartyLegalEntity partyLegalEntity = PartyLegalEntity.of("AMS Setia Jaya Sdn. Bhd.");
        Contact contact = Contact.of("+969876543210", "ali@ams.com");
        return new AccountingSupplierParty(
            null,
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
            PartyIdentification.of("202201043174", "BRN"),
            PartyIdentification.of("NA", "SST")
        );
        PostalAddress postalAddress = PostalAddress.builder()
            .cityName("Kuala Lumpur")
            .postalZone("50200")
            .countrySubentityCode("14")
            .addressLines("Lot 5 08", "5th Floor", "Wisma Cosway Jalan Raja Chulan")
            .country("MYS", "ISO3166-1", "6")
            .build();
        PartyLegalEntity partyLegalEntity = PartyLegalEntity.of("Chuan Sin Sdn. Bhd.");
        Contact contact = Contact.of("+969876543210", "chuan.sin@test.com");
        return new AccountingCustomerParty(
            partyIdentifications,
            postalAddress,
            partyLegalEntity,
            contact
        );
    }

    private TaxTotal taxTotal() {
        Amount taxAmount = Amount.MYR(0D);
        TaxSubtotal taxSubtotal = new TaxSubtotal(
            Amount.MYR(0D),
            Amount.MYR(0D),
            new TaxCategory(
                "06",
                new TaxScheme("OTH", "UN/ECE 5153", "6")
            )
        );
        return TaxTotal.of(taxAmount, taxSubtotal);
    }

    private LegalMonetaryTotal legalMonetaryTotal() {
        return LegalMonetaryTotal.builder()
            .lineExtensionAmount(Amount.MYR(1570.38D))
            .taxExclusiveAmount(Amount.MYR(1570.38D))
            .taxInclusiveAmount(Amount.MYR(1570.38D))
            .allowanceTotalAmount(Amount.MYR(0D))
            .chargeTotalAmount(Amount.MYR(0D))
            .payableRoundingAmount(Amount.MYR(.3D))
            .payableAmount(Amount.MYR(1570.38D))
            .build();
    }

    private InvoiceLine[] invoiceLines() {
        List<Map.Entry<String, Double>> items = Arrays.asList(
            entry("Laptop Peripherals", 454D),
            entry("Server Peripherals", 708.88D),
            entry("Computer Peripherals", 407.5D)
        );
        InvoiceLine[] invoiceLines = new InvoiceLine[items.size()];
        for (int i = 0; i < items.size(); i++) {
            Map.Entry<String, Double> item = items.get(i);
            invoiceLines[i] = new InvoiceLine(
                null,
                InvoicedQuantity.of("NA", 1D),
                Amount.MYR(item.getValue()),
                null,
                TaxTotal.of(
                    Amount.MYR(0D),
                    new TaxSubtotal(
                        Amount.MYR(0D),
                        Amount.MYR(0D),
                        new TaxCategory("06", new TaxScheme("OTH", "UN/ECE 5153", "6"))
                    )
                ),
                new Item(
                    item.getKey(),
                    null,
                    CommodityClassification.of("CLASS", "004")
                ),
                Amount.MYR(item.getValue()),
                Amount.MYR(0D)
            );
        }
        return invoiceLines;
    }
}
