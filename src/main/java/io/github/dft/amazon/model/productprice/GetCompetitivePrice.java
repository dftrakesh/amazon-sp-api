package io.github.dft.amazon.model.productprice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.dft.amazon.model.productfees.MoneyType;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetCompetitivePrice {

    @JsonProperty("LandedPrice")
    private MoneyType landedPrice;

    @JsonProperty("ListingPrice")
    private MoneyType listingPrice;

    @JsonProperty("Shipping")
    private MoneyType shipping;
}