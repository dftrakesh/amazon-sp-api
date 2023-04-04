package io.github.dft.amazon.model.fulfillmentoutbound.v20200701;

import lombok.Data;

@Data
public class DeliveryWindow {
    private String startDate;
    private String endDate;
}