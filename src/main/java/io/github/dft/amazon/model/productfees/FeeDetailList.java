package io.github.dft.amazon.model.productfees;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FeeDetailList {

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

    @JsonProperty("IncludedFeeDetailList")
    private List<IncludedFeeDetail> includedFeeDetailList;
}