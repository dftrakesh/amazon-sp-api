package io.github.dft.amazon.model.feeds.v20210630;

import lombok.Data;

@Data
public class CreateFeedSpecification {
    private String feedType;
    private String marketplaceIds;
    private String inputFeedDocumentId;
    private FeedOptions feedOptions;
}
