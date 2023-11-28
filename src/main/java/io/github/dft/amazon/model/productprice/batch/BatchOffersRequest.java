package io.github.dft.amazon.model.productprice.batch;

import lombok.Data;

import java.util.List;

@Data
public class BatchOffersRequest {
    List<BatchOfferRequest> requests;
}