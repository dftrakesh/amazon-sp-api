package io.github.dft.amazon.model.orders.v0;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BuyerInfo {

    @JsonProperty("BuyerEmail")
    private String buyerEmail;

    @JsonProperty("BuyerName")
    private String buyerName;

    @JsonProperty("BuyerCounty")
    private String buyerCounty;

    @JsonProperty("BuyerTaxInfo")
    private BuyerTaxInfo BuyerTaxInfo;

    @JsonProperty("PurchaseOrderNumber")
    private String PurchaseOrderNumber;
}