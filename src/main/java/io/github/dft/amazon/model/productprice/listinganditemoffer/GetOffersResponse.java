package io.github.dft.amazon.model.productprice.listinganditemoffer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.dft.amazon.model.error.v1.Error;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetOffersResponse {
    
    private Payload payload;
    private List<Error> errors;
}