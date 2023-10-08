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

    @JsonProperty("identifiers")
    private List<ItemIdentifiers> identifiers;

    @JsonProperty("images")
    private List<ItemImagesByMarketplace> images;

    @JsonProperty("productTypes")
    private List<ItemProductTypeByMarketplace> productTypes;

    @JsonProperty("relationships")
    private List<ItemRelationships> relationships;

    @JsonProperty("salesRanks")
    private List<ItemSalesRanksByMarketplace> salesRanks;

    private List<Error> errors;
}