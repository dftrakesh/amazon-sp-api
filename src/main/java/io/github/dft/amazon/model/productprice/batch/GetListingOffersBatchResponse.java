package io.github.dft.amazon.model.productprice.batch;

import io.github.dft.amazon.model.error.v1.Error;
import lombok.Data;

import java.util.List;

@Data
public class GetListingOffersBatchResponse {
    private List<ListingOffersResponse> responses;
    private List<Error> errors;
}