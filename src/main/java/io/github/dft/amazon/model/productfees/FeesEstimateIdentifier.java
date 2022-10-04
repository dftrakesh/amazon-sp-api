package io.github.dft.amazon.model.productfees;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FeesEstimateIdentifier {

    @JsonProperty("MarketplaceId")
    private String marketplaceId;

    @JsonProperty("IdType")
    private String idType;

    @JsonProperty("SellerId")
    private String sellerId;

    @JsonProperty("SellerInputIdentifier")
    private String sellerInputIdentifier;

    @JsonProperty("IsAmazonFulfilled")
    private Boolean isAmazonFulfilled;

    @JsonProperty("IdValue")
    private String idValue;

    @JsonProperty("PriceToEstimateFees")
    private PriceToEstimateFees priceToEstimateFees;
}