package io.github.dft.amazon.model.productprice.listingoffer;

import io.github.dft.amazon.model.error.v1.Error;
import lombok.Data;

import java.util.List;

@Data
public class GetOffersResponse {
    
    private Payload payload;
    private List<Error> errors;
}