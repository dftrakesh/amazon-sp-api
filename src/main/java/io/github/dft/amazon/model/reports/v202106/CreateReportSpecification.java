package io.github.dft.amazon.model.reports.v202106;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateReportSpecification {

    private ReportOptions reportOptions;
    private String reportType;
    private String dataStartTime;
    private String dataEndTime;
    private List<String> marketplaceIds;
}