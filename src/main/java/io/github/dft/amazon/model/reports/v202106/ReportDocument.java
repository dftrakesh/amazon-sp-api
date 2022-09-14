package io.github.dft.amazon.model.reports.v202106;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportDocument {

    private String reportDocumentId;
    private String url;
    private String compressionAlgorithm;
    private List<Error> errors;
}