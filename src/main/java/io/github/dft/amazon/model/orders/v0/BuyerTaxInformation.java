package io.github.dft.amazon.model.orders.v0;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BuyerTaxInformation {

    @JsonProperty("BuyerLegalCompanyName")
    private String buyerLegalCompanyName;

    @JsonProperty("BuyerBusinessAddress")
    private String buyerBusinessAddress;

    @JsonProperty("BuyerTaxRegistrationId")
    private String buyerTaxRegistrationId;

    @JsonProperty("BuyerTaxOffice")
    private String buyerTaxOffice;
}