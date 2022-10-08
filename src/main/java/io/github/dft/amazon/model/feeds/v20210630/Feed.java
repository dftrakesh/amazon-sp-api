package io.github.dft.amazon.model.feeds.v20210630;

import lombok.Data;

import java.util.List;

@Data
public class Feed {

    private String feedId;
    private String feedType;
    private List<String> marketplaceIds;
    private String createdTime;
    private String processingStatus;
    private String processingStartTime;
    private String processingEndTime;
    private String resultFeedDocumentId;

}
