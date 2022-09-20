package io.github.dft.amazon.model.tokens.v202103;

import lombok.Data;

import java.util.List;

@Data
public class CreateRestrictedDataTokenResponse {

    private String restrictedDataToken;
    private Integer expiresIn;
    private List<Error> errors;
}