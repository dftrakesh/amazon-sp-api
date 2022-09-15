package io.github.dft.amazon.model.orders.v0;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderItemsList {

    @JsonProperty("OrderItems")
    private List<OrderItem> orderItems;

    @JsonProperty("NextToken")
    private String nextToken;

    @JsonProperty("AmazonOrderId")
    private String amazonOrderId;
}