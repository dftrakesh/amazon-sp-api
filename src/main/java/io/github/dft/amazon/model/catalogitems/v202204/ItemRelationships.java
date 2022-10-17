package io.github.dft.amazon.model.catalogitems.v202204;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemRelationships {

    @JsonProperty("marketplaceId")
    private String marketplaceId;

    @JsonProperty("relationships")
    private List<ItemRelationship> relationships;
}