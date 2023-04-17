package io.github.dft.amazon.model.productprice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetCompetitivePricing {

    @JsonProperty("CompetitivePrices")
    private List<GetCompetitivePrices> getCompetitivePrices;
}