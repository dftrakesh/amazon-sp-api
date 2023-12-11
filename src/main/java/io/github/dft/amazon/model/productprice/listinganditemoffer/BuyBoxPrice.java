package io.github.dft.amazon.model.productprice.listinganditemoffer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class BuyBoxPrice {

    @JsonProperty("condition")
    private String condition;

    private Price landedPrice;
    private Price listingPrice;
    private Price shipping;
}