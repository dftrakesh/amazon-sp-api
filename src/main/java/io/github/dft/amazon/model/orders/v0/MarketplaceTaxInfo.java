package io.github.dft.amazon.model.orders.v0;

import lombok.Data;
import java.util.List;

@Data
public class MarketplaceTaxInfo {

    private List<TaxClassifications> TaxClassifications;
}