package io.github.dft.amazon.model.reports.v202106;

import lombok.Data;
import java.util.List;

@Data
public class GetReportsResponse {
    private List<Report> reports;
    private String nextToken;
}
