package io.github.dft.amazon.model.orders.v0;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderRegulatedInfo {

    @JsonProperty("AmazonOrderId")
    private String amazonOrderId;

    @JsonProperty("RegulatedInformation")
    private RegulatedInformation regulatedInformation;

    @JsonProperty("RequiresDosageLabel")
    private Boolean requiresDosageLabel;

    @JsonProperty("RegulatedOrderVerificationStatus")
    private RegulatedOrderVerificationStatus regulatedOrderVerificationStatus;
}