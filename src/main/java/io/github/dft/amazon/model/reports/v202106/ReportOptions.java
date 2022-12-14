package io.github.dft.amazon.model.reports.v202106;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.HashMap;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportOptions {
    HashMap<String, String> reportOptions;
}