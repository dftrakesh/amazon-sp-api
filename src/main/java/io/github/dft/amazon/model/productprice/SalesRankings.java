package io.github.dft.amazon.model.productprice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SalesRankings {

    @JsonProperty("ProductCategoryId")
    private String productCategoryId;

    @JsonProperty("Rank")
    private int rank;
}