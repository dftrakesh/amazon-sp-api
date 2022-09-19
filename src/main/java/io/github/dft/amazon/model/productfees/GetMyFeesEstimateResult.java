package io.github.dft.amazon.model.productfees;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetMyFeesEstimateResult {

    @JsonProperty("FeesEstimateResult")
    private FeesEstimateResult feesEstimateResult;

}
