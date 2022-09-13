package io.github.dft.amazon.model.orders.v0;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BuyerTaxInfo {

    private String companyLegalName;
    private String taxingRegion;
    private List<TaxClassifications> taxClassificationsList;
}