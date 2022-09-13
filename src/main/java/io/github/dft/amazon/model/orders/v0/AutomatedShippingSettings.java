package io.github.dft.amazon.model.orders.v0;

import lombok.Data;

@Data
public class AutomatedShippingSettings {

    private Boolean hasAutomatedShippingSettings;
    private String automatedCarrier;
    private String automatedShipMethod;
}