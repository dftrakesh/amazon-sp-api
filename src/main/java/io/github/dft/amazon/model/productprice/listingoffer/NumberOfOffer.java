package io.github.dft.amazon.model.productprice.listingoffer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NumberOfOffer {
    private String condition;
    private String fulfillmentChannel;

    @JsonProperty("OfferCount")
    private Integer offerCount;
}