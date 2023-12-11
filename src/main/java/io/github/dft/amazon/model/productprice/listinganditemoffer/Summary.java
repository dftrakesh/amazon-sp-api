package io.github.dft.amazon.model.productprice.listinganditemoffer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class Summary {

    private List<LowestPrice> lowestPrices;
    private List<BuyBoxPrice> buyBoxPrices;
    private List<OfferCountDetails> numberOfOffers;
    private List<OfferCountDetails> buyBoxEligibleOffers;
    private Integer totalOfferCount;
}