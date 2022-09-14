package io.github.dft.amazon.model.orders.v0;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentExecutionDetailItem {

    @JsonProperty("Payment")
    private Money payment;

    @JsonProperty("PaymentMethod")
    private String paymentMethod;
}