package io.github.dft.amazon.model.fulfillmentoutbound.v20200701;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FulfillmentShipmentItem {
    private String sellerSku;
    private String sellerFulfillmentOrderItemId;
    private String quantity;
    private Integer packageNumber;
    private String serialNumber;
}