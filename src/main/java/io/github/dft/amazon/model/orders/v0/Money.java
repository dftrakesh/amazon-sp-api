package io.github.dft.amazon.model.orders.v0;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Money {

    @JsonProperty("CurrencyCode")
    private String currencyCode;

    @JsonProperty("Amount")
    private String amount;
}