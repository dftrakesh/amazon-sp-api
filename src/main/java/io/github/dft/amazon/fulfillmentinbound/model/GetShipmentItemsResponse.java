package io.github.dft.amazon.fulfillmentinbound.model;

import io.github.dft.amazon.model.reports.v202106.Error;
import lombok.Data;

import java.util.List;

@Data
public class GetShipmentItemsResponse {

    private GetShipmentItemsResult payload;
    private List<Error> errors;
}
