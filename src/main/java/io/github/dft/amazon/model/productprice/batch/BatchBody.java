package io.github.dft.amazon.model.productprice.batch;

import io.github.dft.amazon.model.error.v1.Error;
import io.github.dft.amazon.model.productprice.listinganditemoffer.Payload;
import lombok.Data;

import java.util.List;

@Data
public class BatchBody {
    private Payload payload;
    private List<Error> errors;
}