package io.github.dft.amazon.model.fbainventory.v1;

import lombok.Data;

@Data
public class ReservedQuantity {
    private Integer totalReservedQuantity;
    private Integer pendingCustomerOrderQuantity;
    private Integer pendingTransshipmentQuantity;
    private Integer fcProcessingQuantity;
}