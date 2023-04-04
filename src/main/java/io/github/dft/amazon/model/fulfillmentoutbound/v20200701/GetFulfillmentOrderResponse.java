package io.github.dft.amazon.model.fulfillmentoutbound.v20200701;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.dft.amazon.model.reports.v202106.Error;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetFulfillmentOrderResponse {
    private GetFulfillmentOrderResult payload;
    private List<Error> errors;
}