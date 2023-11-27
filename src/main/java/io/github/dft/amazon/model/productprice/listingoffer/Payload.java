package io.github.dft.amazon.model.productprice.listingoffer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class Payload {

    @JsonProperty("SKU")
    private String sku;

    @JsonProperty("status")
    private String status;

    @JsonProperty("marketplaceId")
    private String marketplaceId;

    private Summary summary;
    private List<Offer> offers;
    private String itemCondition;
    private Identifier identifier;
}