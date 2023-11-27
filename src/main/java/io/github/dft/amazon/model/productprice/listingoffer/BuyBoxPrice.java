package io.github.dft.amazon.model.productprice.listingoffer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class BuyBoxPrice {

    @JsonProperty("condition")
    private String condition;

    private LandedPrice landedPrice;
    private ListingPrice listingPrice;
    private Shipping shipping;
}