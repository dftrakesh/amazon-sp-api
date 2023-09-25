package io.github.dft.amazon.model.error;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error {

    private String details;

    @JsonProperty("Type")
    private String type;

    @JsonProperty("Code")
    private String code;

    @JsonProperty("Message")
    private String message;


}