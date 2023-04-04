package io.github.dft.amazon.model.fulfillmentoutbound.v20200701;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.dft.amazon.model.orders.v0.Address;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FulfillmentOrder {
    private String sellerFulfillmentOrderId;
    private String marketplaceId;
    private String displayableOrderId;
    private String displayableOrderDate;
    private String displayableOrderComment;
    private String shippingSpeedCategory;
    private DeliveryWindow deliveryWindow;
    private Address destinationAddress;
    private String fulfillmentAction;
    private String fulfillmentPolicy;
    private CODSettings codSettings;
    private String receivedDate;
    private String fulfillmentOrderStatus;
    private String statusUpdatedDate;
    private List<String> notificationEmails;
    private List<FeatureSettings> featureConstraints;
}