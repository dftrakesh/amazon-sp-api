package io.github.dft.amazon.model.productprice.batch;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import static io.github.dft.amazon.constantcode.ConstantCodes.OFFERS_ENDPOINT;
import static io.github.dft.amazon.constantcode.ConstantCodes.PRODUCTS_PRICING_LISTINGS_V0;

@Data
public class BatchOfferRequest {

    private String uri;
    private String method;
    
    @JsonProperty("MarketplaceId")
    private String marketplaceId;
    
    @JsonProperty("ItemCondition")
    private String itemCondition;
}