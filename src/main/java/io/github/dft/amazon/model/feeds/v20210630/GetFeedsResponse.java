package io.github.dft.amazon.model.feeds.v20210630;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.dft.amazon.model.reports.v202106.Error;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetFeedsResponse {

    private List<Feed> feeds;
    private String nextToken;
    private List<Error> errors;
}
