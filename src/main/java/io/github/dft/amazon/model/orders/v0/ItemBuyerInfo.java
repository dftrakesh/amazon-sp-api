package io.github.dft.amazon.model.orders.v0;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemBuyerInfo {

    @JsonProperty("BuyerCustomizedInfo")
    private BuyerCustomizedInfoDetail buyerCustomizedInfo;

    @JsonProperty("GiftWrapPrice")
    private Money giftWrapPrice;

    @JsonProperty("GiftWrapTax")
    private Money giftWrapTax;

    @JsonProperty("GiftMessageText")
    private String giftMessageText;

    @JsonProperty("GiftWrapLevel")
    private String giftWrapLevel;
}