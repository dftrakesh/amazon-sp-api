package io.github.dft.amazon.model.offers.v20210801;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.github.dft.amazon.model.attributes.v20210801.ItemValue;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Offers {

    private String marketplaceId;
    private String offerType;
    private Price price;
    private List<ItemValue> productDescription;
}