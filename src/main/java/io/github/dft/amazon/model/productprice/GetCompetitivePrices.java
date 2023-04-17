package io.github.dft.amazon.model.productprice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetCompetitivePrices {

    private boolean belongsToRequester;

    private String condition;

    @JsonProperty("subcondition")
    private String subCondition;

    @JsonProperty("CompetitivePriceId")
    private String CompetitivePriceId;

    @JsonProperty("Price")
    private GetCompetitivePrice price;

}