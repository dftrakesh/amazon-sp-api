package io.github.dft.amazon.fulfillmentinbound.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class Address {

    private String name;
    private String addressLine1;
    private String addressLine2;
    private String districtOrCounty;
    private String city;
    private String stateOrProvinceCode;
    private String countryCode;
    private String postalCode;
}
