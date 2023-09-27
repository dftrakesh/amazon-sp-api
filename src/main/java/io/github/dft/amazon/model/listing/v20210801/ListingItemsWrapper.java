package io.github.dft.amazon.model.listing.v20210801;

import io.github.dft.amazon.model.attributes.v20210801.Attributes;
import io.github.dft.amazon.model.error.Error;
import io.github.dft.amazon.model.offers.v20210801.Offers;
import io.github.dft.amazon.model.summaries.v20210801.Summaries;
import lombok.Data;

import java.util.List;

@Data
public class ListingItemsWrapper {

    private String sku;
    private List<Summaries> summaries;
    private Attributes attributes;
    private List<Offers> offers;
    private List<Error> errors;
}