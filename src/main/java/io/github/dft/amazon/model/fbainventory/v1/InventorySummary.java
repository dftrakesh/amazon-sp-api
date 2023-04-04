package io.github.dft.amazon.model.fbainventory.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class InventorySummary {
    private String asin;
    private String fnSku;
    private String sellerSku;
    private String condition;
    private InventoryDetails inventoryDetails;
    private String lastUpdatedTime;
    private String productName;
    private Integer totalQuantity;
}