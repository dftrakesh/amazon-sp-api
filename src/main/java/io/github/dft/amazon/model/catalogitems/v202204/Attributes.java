package io.github.dft.amazon.model.catalogitems.v202204;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Attributes {

    private List<UnitCount> unitCount;
    private List<ItemValue> productDescription;
    private List<ItemValue> itemValue;
    private List<ItemPrice> listPrice;
    private List<ItemValue> itemName;
    private List<ItemValue> size;
    private List<ItemValue> style;
    private List<ItemValue> brand;
    private List<ItemValue> color;
    private List<ItemValue> productSiteLaunchDate;
}