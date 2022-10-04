package io.github.dft.amazon.model.productfees;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IncludedFeeDetail {

    @JsonProperty("FeeType")
    private String feeType;

    @JsonProperty("FeeAmount")
    private MoneyType feeAmount;

    @JsonProperty("FinalFee")
    private MoneyType finalFee;

    @JsonProperty("FeePromotion")
    private MoneyType feePromotion;

    @JsonProperty("TaxAmount")
    private MoneyType taxAmount;
}