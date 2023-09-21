package io.github.dft.amazon.model.notifications;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.github.dft.amazon.common.DateDeserializer;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationWrapper {

    private String id;
    private String version;

    @JsonProperty("detail-type")
    private String detailType;

    private String source;
    private String account;
    private String region;
    private Detail detail;

    @JsonDeserialize(using = DateDeserializer.class)
    private LocalDateTime time;
}