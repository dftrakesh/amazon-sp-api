package io.github.dft.amazon.model.orders.v0;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BuyerRequestedCancel {

    @JsonProperty("IsBuyerRequestedCancel")
    private Boolean isBuyerRequestedCancel;

    @JsonProperty("BuyerCancelReason")
    private String buyerCancelReason;
}