package io.github.dft.amazon.fulfillmentinbound.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.dft.amazon.model.reports.v202106.Error;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetShipmentsResponse {

    private GetShipmentsResult payload;
    private List<Error> errors;
}
