package io.github.dft.amazon.model.productprice.listingoffer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BuyBoxEligibleOffer {

    private String condition;
    private String fulfillmentChannel;
    
    @JsonProperty("OfferCount")
    private Integer offerCount;
}