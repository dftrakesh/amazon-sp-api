package io.github.dft.amazon.model.productprice.batch;

import lombok.Data;

@Data
public class BatchStatus {
    private String statusCode;
    private String reasonPhrase;
}