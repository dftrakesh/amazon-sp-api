package io.github.dft.amazon.model.feeds.v20210630;

import io.github.dft.amazon.model.reports.v202106.Error;
import lombok.Data;

import java.util.List;

@Data
public class CreateFeedResponse {
    private String feedId;
    private List<Error> errors;
}
