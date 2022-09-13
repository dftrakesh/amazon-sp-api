package io.github.dft.amazon.model.orders.v0;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BuyerInfo {

    private String buyerEmail;
    private String buyerName;
    private String buyerCounty;
    private BuyerTaxInfo BuyerTaxInfo;
    private String PurchaseOrderNumber;
}