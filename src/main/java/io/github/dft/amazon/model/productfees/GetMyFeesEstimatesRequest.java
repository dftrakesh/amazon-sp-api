package io.github.dft.amazon.model.productfees;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetMyFeesEstimatesRequest {

    @JsonProperty("FeesEstimateRequest")
    private FeesEstimateRequest feesEstimateRequest;

    @JsonProperty("IdType")
    private String idType;

    @JsonProperty("IdValue")
    private String idValue;
}
