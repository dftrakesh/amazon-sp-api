package io.github.dft.amazon.model.fulfillmentoutbound.v20200701;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetFulfillmentOrderResult {
    private FulfillmentOrder fulfillmentOrder;
    private List<FulfillmentOrderItem> fulfillmentOrderItems;
    private List<FulfillmentShipment> fulfillmentShipments;
    private List<ReturnItem> returnItems;
    private List<ReturnAuthorization> returnAuthorizations;
}