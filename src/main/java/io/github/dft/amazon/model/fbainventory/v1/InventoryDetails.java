package io.github.dft.amazon.model.fbainventory.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class InventoryDetails {
    private Integer fulfillableQuantity;
    private Integer inboundWorkingQuantity;
    private Integer inboundShippedQuantity;
    private Integer inboundReceivingQuantity;
    private ReservedQuantity reservedQuantity;
    private ResearchingQuantity researchingQuantity;
    private UnfulfillableQuantity unfulfillableQuantity;
}