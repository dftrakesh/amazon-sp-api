package io.github.dft.amazon.model.catalogitems.v202204;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Categories {

    @JsonProperty("ProductCategoryId")
    private String productCategoryId;

    @JsonProperty("ProductCategoryName")
    private String productCategoryName;

    @JsonProperty("parent")
    private Categories parent;
}