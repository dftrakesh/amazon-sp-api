package io.github.dft.amazon.model.error;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AmazonError {

    private String details;

    @JsonProperty("Type")
    private String type;

    @JsonProperty("Code")
    @JsonAlias("code")
    private String code;

    @JsonProperty("Message")
    @JsonAlias("message")
    private String message;


}