package io.github.dft.amazon.model.productfees;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceToEstimateFees {

    @JsonProperty("ListingPrice")
    private MoneyType listingPrice;

    @JsonProperty("Shipping")
    private MoneyType shipping;

    @JsonProperty("Points")
    private Points points;
}
