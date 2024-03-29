package io.github.dft.amazon.model.fulfillmentoutbound.v20200701;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListAllFulfillmentOrdersResult {
    private List<FulfillmentOrder> fulfillmentOrders;
    private String nextToken;
}