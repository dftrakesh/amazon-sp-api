package io.github.dft.amazon.model.catalogitems.v202204;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemSummaryByMarketplace {

    @JsonProperty("marketplaceId")
    private String marketplaceId;

    @JsonProperty("brand")
    private String brand;

    @JsonProperty("browseClassification")
    private String browseClassification;

    @JsonProperty("color")
    private String color;

    @JsonProperty("itemClassification")
    private String itemClassification;

    @JsonProperty("itemName")
    private String itemName;

    @JsonProperty("manufacturer")
    private String manufacturer;

    @JsonProperty("modelNumber")
    private String modelNumber;

    @JsonProperty("packageQuantity")
    private String packageQuantity;

    @JsonProperty("partNumber")
    private String partNumber;

    @JsonProperty("size")
    private String size;

    @JsonProperty("style")
    private String style;

    @JsonProperty("websiteDisplayGroup")
    private String websiteDisplayGroup;

    @JsonProperty("websiteDisplayGroupName")
    private String websiteDisplayGroupName;
}