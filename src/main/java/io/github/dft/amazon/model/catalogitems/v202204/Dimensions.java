package io.github.dft.amazon.model.catalogitems.v202204;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Dimensions {

    @JsonProperty("height")
    private Dimension height;

    @JsonProperty("length")
    private Dimension length;

    @JsonProperty("weight")
    private Dimension weight;

    @JsonProperty("width")
    private Dimension width;
}