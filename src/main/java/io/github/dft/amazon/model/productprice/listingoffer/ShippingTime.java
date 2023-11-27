package io.github.dft.amazon.model.productprice.listingoffer;

import lombok.Data;

@Data
public class ShippingTime {

    private Integer maximumHours;
    private Integer minimumHours;
    private String availabilityType;
}