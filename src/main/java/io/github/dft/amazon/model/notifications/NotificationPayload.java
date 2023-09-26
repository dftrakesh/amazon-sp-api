package io.github.dft.amazon.model.notifications;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.github.dft.amazon.common.LocalDateTimeDeserializer;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class NotificationPayload {

    private String SellerId;
    private String Asin;
    private String MarketplaceId;
    private String FulfillmentChannelCode;
    private String Sku;
    private Integer Quantity;
    private List<String> Status;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime CreatedDate;
}