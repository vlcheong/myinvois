# myinvois
MyInvois XML Digital Signature

# Disclaimer
This software is distributed under the Apache License, Version 2.0, without any warranties or guarantees. Users are permitted to use, modify, and distribute the software in accordance with the license terms. However, by using this software, users acknowledge that they accept full responsibility for any risks or consequences associated with its use.

# Requirement
1. Minimum Java 17

# Example
1. Create Invoice object using InvoiceBuilder, refer to test for more details.
   ```
   Invoice invoice = Invoice.builder().build();
   ```
3. Create KeyStoreConfig
   ```
   KeyStoreConfig keyStoreConfig = KeyStoreConfig.from(new File("cert.p12"), "password");
   ```
4. Sign the invoice
   ```
   String xml = Signer.getSigner().sign(invoice, keyStoreConfig);
   ```
