package io.github.dft.amazon.model.catalogitems.v202204;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemDisplayGroupSalesRank {
    private String websiteDisplayGroup;
    private String title;
    private String link;
    private Integer rank;
}