package io.github.dft.amazon.model.productprice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarketplaceASIN {

    @JsonProperty("MarketplaceId")
    private String marketplaceId;

    @JsonProperty("ASIN")
    private String asin;
}