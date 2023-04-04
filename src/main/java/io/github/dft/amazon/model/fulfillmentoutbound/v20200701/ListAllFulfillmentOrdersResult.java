package io.github.dft.amazon.model.fulfillmentoutbound.v20200701;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListAllFulfillmentOrdersResult {
    private FulfillmentOrder fulfillmentOrders;
    private String nextToken;
}