package io.github.dft.amazon.fulfillmentinbound.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class InboundShipmentItem {

    @JsonProperty("ShipmentId")
    private String shipmentId;

    @JsonProperty("SellerSKU")
    private String sellerSKU;

    @JsonProperty("FulfillmentNetworkSKU")
    private String fulfillmentNetworkSKU;

    @JsonProperty("QuantityShipped")
    private Integer quantityShipped;

    @JsonProperty("QuantityReceived")
    private Integer quantityReceived;

    @JsonProperty("QuantityInCase")
    private Integer quantityInCase;

}
