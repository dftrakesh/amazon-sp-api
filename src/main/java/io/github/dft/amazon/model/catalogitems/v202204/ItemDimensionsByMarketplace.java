package io.github.dft.amazon.model.catalogitems.v202204;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemDimensionsByMarketplace {

    @JsonProperty("marketplaceId")
    private String marketplaceId;

    @JsonProperty("item")
    private Dimensions item;

    @JsonProperty("package")
    private Dimensions packages;
}