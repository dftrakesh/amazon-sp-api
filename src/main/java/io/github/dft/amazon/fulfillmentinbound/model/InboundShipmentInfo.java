package io.github.dft.amazon.fulfillmentinbound.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class InboundShipmentInfo {

    private String shipmentId;
    private String shipmentName;
    private String destinationFulfillmentCenterId;
    private String shipmentStatus;
    private Address shipFromAddress;
}
