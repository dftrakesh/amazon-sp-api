package io.github.dft.amazon.model.reports.v202106;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@Data
public class CreateReportSpecification {

    private ReportOptions reportOptions;
    private String reportType;
    private ZonedDateTime dataStartTime;
    private ZonedDateTime dataEndTime;
    private List<String> marketplaceIds;
}