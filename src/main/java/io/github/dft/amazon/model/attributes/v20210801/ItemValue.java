package io.github.dft.amazon.model.attributes.v20210801;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ItemValue {

    private String value;
    private String marketplaceId;
    private Integer quantity;
    private String languageTag;
    private String fulfillmentChannelCode;
}