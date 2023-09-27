package io.github.dft.amazon.model.error.v1;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error {

    private String code;
    private String message;
    private String details;
}