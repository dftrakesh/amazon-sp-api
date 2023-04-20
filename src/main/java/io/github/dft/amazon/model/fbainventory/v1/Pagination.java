package io.github.dft.amazon.model.fbainventory.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pagination {
    private String nextToken;
}