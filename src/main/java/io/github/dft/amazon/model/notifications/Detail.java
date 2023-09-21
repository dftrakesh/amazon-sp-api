package io.github.dft.amazon.model.notifications;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.github.dft.amazon.common.LocalDateTimeDeserializer;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class Detail {

    private Float notificationVersion;
    private String notificationType;
    private Float payloadVersion;
    private NotificationPayload payload;
    private NotificationMetadata notificationMetadata;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime eventTime;
}