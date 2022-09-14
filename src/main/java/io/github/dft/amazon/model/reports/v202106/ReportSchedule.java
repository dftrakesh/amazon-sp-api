package io.github.dft.amazon.model.reports.v202106;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportSchedule {
    private String reportScheduleId;
    private String reportType;
    private List<String> marketplaceIds;
    private ReportOptions reportOptions;
    private String period;
    private String nextReportCreationTime;
}
