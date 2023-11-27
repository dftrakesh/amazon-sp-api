package io.github.dft.amazon.model.productprice.listingoffer;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class Identifier {

    private String marketplaceId;
    private String itemCondition;
    private String sellerSKU;
}