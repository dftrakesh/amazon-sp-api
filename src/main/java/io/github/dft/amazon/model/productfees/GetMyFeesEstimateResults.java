package io.github.dft.amazon.model.productfees;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.dft.amazon.model.reports.v202106.ErrorList;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetMyFeesEstimateResults {

    @JsonProperty("Status")
    private String status;

    @JsonProperty("FeesEstimateIdentifier")
    private FeesEstimateIdentifier feesEstimateIdentifier;

    @JsonProperty("FeesEstimate")
    private FeesEstimate feesEstimate;

    @JsonProperty("FeeDetailList")
    private List<FeeDetailList> feeDetailList;

    private ErrorList errors;
}