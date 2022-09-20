package io.github.dft.amazon.model.productfees;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FeesEstimateResult {

    @JsonProperty("Status")
    private String status;

    @JsonProperty("FeesEstimate")
    private FeesEstimate feesEstimate;
}
