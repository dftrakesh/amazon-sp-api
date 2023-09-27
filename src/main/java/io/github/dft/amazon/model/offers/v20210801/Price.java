package io.github.dft.amazon.model.offers.v20210801;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Price {

    private String currency;
    private String marketplaceId;
    private Double amount;
}