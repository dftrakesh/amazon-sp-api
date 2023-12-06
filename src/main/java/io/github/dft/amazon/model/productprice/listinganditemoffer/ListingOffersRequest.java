package io.github.dft.amazon.model.productprice.listinganditemoffer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListingOffersRequest {
    private String uri;
    private String method;

    @JsonProperty("MarketplaceId")
    private String marketplaceId;

    @JsonProperty("ItemCondition")
    private String itemCondition;

    @JsonProperty("CustomerType")
    private String customerType;
}
