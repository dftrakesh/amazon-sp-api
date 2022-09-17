package io.github.dft.amazon.model.productfees;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MoneyType {

    @JsonProperty("CurrencyCode")
    private String currencyCode;

    @JsonProperty("Amount")
    private Float amount;
}
