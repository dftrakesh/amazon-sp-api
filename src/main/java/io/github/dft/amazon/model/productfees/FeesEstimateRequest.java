package io.github.dft.amazon.model.productfees;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FeesEstimateRequest {

    @JsonProperty("MarketplaceId")
    private String marketplaceId;

    @JsonProperty("IsAmazonFulfilled")
    private Boolean isAmazonFulfilled;

    @JsonProperty("PriceToEstimateFees")
    private PriceToEstimateFees priceToEstimateFees;

    @JsonProperty("Identifier")
    private String identifier;

    @JsonProperty("OptionalFulfillmentProgram")
    private OptionalFulfillmentProgram optionalFulfillmentProgram;

}
