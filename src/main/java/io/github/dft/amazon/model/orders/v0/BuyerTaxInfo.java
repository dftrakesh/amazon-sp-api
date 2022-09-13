package io.github.dft.amazon.model.orders.v0;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BuyerTaxInfo {

    @JsonProperty("CompanyLegalName")
    private String companyLegalName;

    @JsonProperty("TaxingRegion")
    private String taxingRegion;

    @JsonProperty("TaxClassifications")
    private List<TaxClassifications> taxClassificationsList;
}