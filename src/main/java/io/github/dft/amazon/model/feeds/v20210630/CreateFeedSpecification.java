package io.github.dft.amazon.model.feeds.v20210630;

import lombok.Data;

import java.util.List;

@Data
public class CreateFeedSpecification {
    private String feedType;
    private List<String> marketplaceIds;
    private String inputFeedDocumentId;
}
