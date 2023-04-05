package io.github.dft.amazon.model.tokens.v202103;

import lombok.Data;

import java.util.List;

@Data
public class CreateRestrictedDataTokenRequest {
    private String targetApplication;
    private List<RestrictedResource> restrictedResources;
}
