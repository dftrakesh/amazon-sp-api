package io.github.dft.amazon.model.productprice.listinganditemoffer;

import lombok.Data;

@Data
public class ShippingTime {

    private Integer maximumHours;
    private Integer minimumHours;
    private String availabilityType;
}