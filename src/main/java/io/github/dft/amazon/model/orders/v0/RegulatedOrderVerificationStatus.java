package io.github.dft.amazon.model.orders.v0;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegulatedOrderVerificationStatus {

    @JsonProperty("Status")
    private String status;

    @JsonProperty("RequiresMerchantAction")
    private Boolean requiresMerchantAction;

    @JsonProperty("ValidRejectionReasons")
    private List<RejectionReason> validRejectionReasons;

    @JsonProperty("RejectionReason")
    private RejectionReason rejectionReason;

    @JsonProperty("ReviewDate")
    private String reviewDate;

    @JsonProperty("ExternalReviewerId")
    private String externalReviewerId;
}