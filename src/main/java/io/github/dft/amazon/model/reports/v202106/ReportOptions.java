package io.github.dft.amazon.model.reports.v202106;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportOptions {

    private String reportScheduleId;
    private String reportType;
    private List<String> marketplaceIds;
    private ReportOptions reportOptions;
    private String period;
    private ZonedDateTime nextReportCreationTime;
}