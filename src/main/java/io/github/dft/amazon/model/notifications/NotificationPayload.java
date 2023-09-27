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

    private String sellerId;
    private String asin;
    private String marketplaceId;
    private String fulfillmentChannelCode;
    private String sku;
    private Integer quantity;
    private List<String> status;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createdDate;
}