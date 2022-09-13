package io.github.dft.amazon.model.tokens.v202103;

import lombok.Data;
import java.util.List;

@Data
public class RestrictedResource {
    private String path;
    private List<String> dataElements;
    private String method;
}
