package io.github.dft.amazon.model.orders.v0;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetOrderItemsBuyerInfoResponse {

    private OrderItemsBuyerInfoList payload;

    private List<Error> errors;
}