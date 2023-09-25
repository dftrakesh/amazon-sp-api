package io.github.dft.amazon.model.productfees;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoneyType {

    @JsonProperty("CurrencyCode")
    private String currencyCode;

    @JsonProperty("Amount")
    private Float amount;
}
