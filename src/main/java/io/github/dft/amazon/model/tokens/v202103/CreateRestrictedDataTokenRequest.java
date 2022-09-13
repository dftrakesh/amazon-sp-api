package io.github.dft.amazon.model.tokens.v202103;

import lombok.Data;

@Data
public class CreateRestrictedDataTokenRequest {
    private String targetApplication;
    private RestrictedResource restrictedResources;
}
