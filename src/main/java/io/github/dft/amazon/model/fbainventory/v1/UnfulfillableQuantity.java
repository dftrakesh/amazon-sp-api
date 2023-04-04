package io.github.dft.amazon.model.fbainventory.v1;

import lombok.Data;

@Data
public class UnfulfillableQuantity {
    private Integer totalUnfulfillableQuantity;
    private Integer customerDamagedQuantity;
    private Integer warehouseDamagedQuantity;
    private Integer distributorDamagedQuantity;
    private Integer carrierDamagedQuantity;
    private Integer defectiveQuantity;
    private Integer expiredQuantity;
}