package io.github.dft.amazon.model.fulfillmentoutbound.v20200701;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.dft.amazon.model.orders.v0.Money;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FulfillmentOrderItem {
    private String sellerSku;
    private String sellerFulfillmentOrderItemId;
    private String quantity;
    private String giftMessage;
    private String displayableComment;
    private String fulfillmentNetworkSku;
    private String orderItemDisposition;
    private String cancelledQuantity;
    private String unfulfillableQuantity;
    private String estimatedShipDate;
    private String estimatedArrivalDate;
    private String perUnitPrice;
    private String perUnitTax;
    private Money perUnitDeclaredValue;
}