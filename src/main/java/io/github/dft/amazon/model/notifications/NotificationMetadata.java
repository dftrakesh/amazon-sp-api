package io.github.dft.amazon.model.notifications;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.github.dft.amazon.common.LocalDateTimeDeserializer;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class NotificationMetadata {

    private String notificationId;
    private String subscriptionId;
    private String applicationId;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime publishTime;
}
