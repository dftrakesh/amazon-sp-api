package io.github.dft.amazon.model.catalogitems.v202204;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Dimension {

    @JsonProperty("unit")
    private String unit;

    @JsonProperty("value")
    private Double value;
}