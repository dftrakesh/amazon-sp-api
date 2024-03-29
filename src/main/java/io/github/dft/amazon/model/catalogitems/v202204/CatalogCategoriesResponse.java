package io.github.dft.amazon.model.catalogitems.v202204;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.dft.amazon.model.error.AmazonError;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CatalogCategoriesResponse {

    @JsonProperty("payload")
    private List<Categories> self;

    @JsonProperty("errors")
    private List<AmazonError> errors;
}