package io.github.dft.amazon.model.fulfillmentoutbound.v20200701;

import lombok.Data;

@Data
public class ReturnItem {
    private String sellerReturnItemId;
    private String sellerFulfillmentOrderItemId;
    private String amazonShipmentId;
    private String sellerReturnReasonCode;
    private String returnComment;
    private String amazonReturnReasonCode;
    private String status;
    private String statusChangedDate;
    private String returnAuthorizationId;
    private String returnReceivedCondition;
    private String fulfillmentCenterId;
}