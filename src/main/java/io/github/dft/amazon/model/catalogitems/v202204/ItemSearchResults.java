package io.github.dft.amazon.model.catalogitems.v202204;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.dft.amazon.model.fbainventory.v1.Pagination;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemSearchResults {

    private Integer numberOfResults;
    private List<Item> items;
    private Pagination pagination;
}