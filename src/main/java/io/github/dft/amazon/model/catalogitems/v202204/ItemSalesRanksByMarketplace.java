package io.github.dft.amazon.model.catalogitems.v202204;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemSalesRanksByMarketplace {
    private String marketplaceId;
    private List<ItemClassificationSalesRank> classificationRanks;
    private List<ItemDisplayGroupSalesRank> displayGroupRanks;
}