package io.github.dft.amazon.model.catalogitems.v202204;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.dft.amazon.model.reports.v202106.Error;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

    @JsonProperty("asin")
    private String asin;

    @JsonProperty("attributes")
    private Attributes attributes;

    @JsonProperty("dimensions")
    private List<ItemDimensionsByMarketplace> dimensions;

    @JsonProperty("images")
    private List<ItemImagesByMarketplace> images;

    @JsonProperty("productTypes")
    private List<ItemProductTypeByMarketplace> productTypes;

    @JsonProperty("summaries")
    private List<ItemSummaryByMarketplace> summaries;

    private List<Error> errors;
}