package io.github.dft.amazon.model.tokens.v202103;

import lombok.Data;

@Data
public class CreateRestrictedDataTokenResponse {
    private String restrictedDataToken;
    private Integer expiresIn;
}
