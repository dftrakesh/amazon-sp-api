package io.github.dft.amazon.model.feeds.v20210630;

import lombok.Data;

@Data
public class FeedDocument {
    private String feedDocumentId;
    private String url;
    private String compressionAlgorithm;
}
