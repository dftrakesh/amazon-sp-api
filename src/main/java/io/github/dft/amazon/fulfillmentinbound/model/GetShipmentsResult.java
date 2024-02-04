package io.github.dft.amazon.fulfillmentinbound.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GetShipmentsResult {

    @JsonProperty("ShipmentData")
    private InboundShipmentList<InboundShipmentInfo> shipmentData;

    @JsonProperty("NextToken")
    private String nextToken;
}
