package io.github.dft.amazon.model.productprice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetCompetitivePriceResult {

    @JsonProperty("SellerSKU")
    private String sellerSKU;

    @JsonProperty("status")
    private String status;

    @JsonProperty("Product")
    private GetCompetitivePriceProduct getCompetitivePriceProduct;
}