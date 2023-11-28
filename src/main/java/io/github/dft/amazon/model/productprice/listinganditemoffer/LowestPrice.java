package io.github.dft.amazon.model.productprice.listinganditemoffer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class LowestPrice {

    @JsonProperty("condition")
    private String condition;

    @JsonProperty("fulfillmentChannel")
    private String fulfillmentChannel;

    private Price shipping;
    private Price landedPrice;
    private Price listingPrice;
}