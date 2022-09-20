package io.github.dft.amazon.model.reports.v202106;

import lombok.Data;

import java.util.List;

@Data
public class Report {

    private List<String> marketplaceIds;
    private String reportId;
    private String reportType;
    private String dataStartTime;
    private String dataEndTime;
    private String reportScheduleId;
    private String processingStatus;
    private String createdTime;
    private String processingStartTime;
    private String processingEndTime;
    private String reportDocumentId;
    private List<Error> errors;
}