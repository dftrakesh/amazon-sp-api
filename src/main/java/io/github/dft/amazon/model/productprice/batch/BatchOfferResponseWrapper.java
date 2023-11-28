package io.github.dft.amazon.model.productprice.batch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BatchOfferResponseWrapper {
    private BatchStatus status;
    private BatchBody body;
}