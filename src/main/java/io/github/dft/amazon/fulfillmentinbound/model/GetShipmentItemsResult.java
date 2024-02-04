package io.github.dft.amazon.fulfillmentinbound.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GetShipmentItemsResult {

    @JsonProperty("ItemData")
    private InboundShipmentItemList<InboundShipmentItem> itemData;

    @JsonProperty("NextToken")
    private String nextToken;

}
