package io.github.dft.amazon.model.orders.v0;

import lombok.Data;

@Data
public class BuyerTaxInformation {

    private String buyerLegalCompanyName;
    private String buyerBusinessAddress;
    private String buyerTaxRegistrationId;
    private String buyerTaxOffice;
}