package io.github.dft.amazon.model.productfees;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FeesEstimate {

    @JsonProperty("TimeOfFeesEstimation")
    private String timeOfFeesEstimation;

    @JsonProperty("TotalFeesEstimate")
    private MoneyType totalFeesEstimate;

    @JsonProperty("FeeDetailList")
    private List<FeeDetailList> feeDetailList;
}
