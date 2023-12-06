package io.github.dft.amazon.model.productprice.batch;

import io.github.dft.amazon.model.productprice.listinganditemoffer.ListingOffersRequest;
import lombok.Data;
import java.util.List;

@Data
public class BatchOffersRequest {
    List<ListingOffersRequest> requests;
}