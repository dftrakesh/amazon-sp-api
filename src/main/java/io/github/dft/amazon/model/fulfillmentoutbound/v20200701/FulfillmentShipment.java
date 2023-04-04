package io.github.dft.amazon.model.fulfillmentoutbound.v20200701;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FulfillmentShipment {
    private String amazonShipmentId;
    private String fulfillmentCenterId;
    private String fulfillmentShipmentStatus;
    private String shippingDate;
    private String estimatedArrivalDate;
    private List<String> shippingNotes;
    private List<FulfillmentShipmentItem> fulfillmentShipmentItem;
    private List<FulfillmentShipmentPackage> fulfillmentShipmentPackage;
}