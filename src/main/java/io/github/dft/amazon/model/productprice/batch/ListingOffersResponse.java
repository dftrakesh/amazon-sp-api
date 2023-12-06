package io.github.dft.amazon.model.productprice.batch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.dft.amazon.model.productprice.listinganditemoffer.GetOffersResponse;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListingOffersResponse {
    private BatchStatus status;
    private GetOffersResponse body;
}