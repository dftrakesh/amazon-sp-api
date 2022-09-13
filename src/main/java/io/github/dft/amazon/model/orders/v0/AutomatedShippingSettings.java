package io.github.dft.amazon.model.orders.v0;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AutomatedShippingSettings {

    private Boolean hasAutomatedShippingSettings;
    private String automatedCarrier;
    private String automatedShipMethod;
}