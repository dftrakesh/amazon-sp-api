package io.github.dft.amazon.model.orders.v0;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BuyerTaxInformation {

    private String buyerLegalCompanyName;
    private String buyerBusinessAddress;
    private String buyerTaxRegistrationId;
    private String buyerTaxOffice;
}