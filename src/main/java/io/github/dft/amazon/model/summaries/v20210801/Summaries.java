package io.github.dft.amazon.model.summaries.v20210801;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.github.dft.amazon.common.LocalDateTimeDeserializer;
import io.github.dft.amazon.model.catalogitems.v202204.ItemImage;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class Summaries {

    private String marketplaceId;
    private String asin;
    private String productType;
    private String conditionType;
    private List<String> status;
    private String itemName;
    private ItemImage mainImage;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createdDate;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime lastUpdatedDate;
}
