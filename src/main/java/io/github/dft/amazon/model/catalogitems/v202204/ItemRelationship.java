package io.github.dft.amazon.model.catalogitems.v202204;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemRelationship {

    @JsonProperty("childAsins")
    private List<String> childAsins;

    @JsonProperty("parentAsins")
    private List<String> parentAsins;

    @JsonProperty("variationTheme")
    private ItemVariationTheme variationTheme;

    @JsonProperty("type")
    private String type;
 }