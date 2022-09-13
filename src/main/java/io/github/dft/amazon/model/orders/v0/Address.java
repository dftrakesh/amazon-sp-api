package io.github.dft.amazon.model.orders.v0;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {

    private String name ;
    private String addressLine1 ;
    private String addressLine2 ;
    private String addressLine3 ;
    private String city ;
    private String county ;
    private String district ;
    private String stateOrRegion ;
    private String municipality ;
    private String postalCode ;
    private String countryCode ;
    private String phone ;
    private AddressTypeEnum addressType ;

    private enum AddressTypeEnum {
        Residential, Commercial
    }
}