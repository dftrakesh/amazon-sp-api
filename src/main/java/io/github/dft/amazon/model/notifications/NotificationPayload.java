package io.github.dft.amazon.model.notifications;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class NotificationPayload {

    private String SellerId;
    private String FulfillmentChannelCode;
    private String Sku;
    private Integer Quantity;
}