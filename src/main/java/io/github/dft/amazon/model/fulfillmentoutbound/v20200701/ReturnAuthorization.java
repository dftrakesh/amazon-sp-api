package io.github.dft.amazon.model.fulfillmentoutbound.v20200701;

import io.github.dft.amazon.model.orders.v0.Address;
import lombok.Data;

@Data
public class ReturnAuthorization {
    private String returnAuthorizationId;
    private String fulfillmentCenterId;
    private Address returnToAddress;
    private String amazonRmaId;
    private String rmaPageURL;
}