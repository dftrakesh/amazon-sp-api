package io.github.dft.amazon.model.orders.v0;

import lombok.Data;

@Data
public class BuyerInfo {

    private String buyerEmail;
    private String buyerName;
    private String buyerCounty;
    private BuyerTaxInfo BuyerTaxInfo;
    private String PurchaseOrderNumber;
}