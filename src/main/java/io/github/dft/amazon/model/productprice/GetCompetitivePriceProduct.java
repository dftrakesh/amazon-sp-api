package io.github.dft.amazon.model.productprice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetCompetitivePriceProduct {

    @JsonProperty("CompetitivePricing")
    private GetCompetitivePricing getCompetitivePricing;

    @JsonProperty("Identifiers")
    private GetCompetitiveIdentifiers identifiers;

    @JsonProperty("SalesRankings")
    private List<SalesRankings> salesRankings;
}