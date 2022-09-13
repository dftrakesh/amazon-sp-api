package io.github.dft.amazon.model.orders.v0;

import lombok.Data;
import java.util.List;

@Data
public class BuyerTaxInfo {

    private String companyLegalName;
    private String taxingRegion;
    private List<TaxClassifications> taxClassificationsList;
}