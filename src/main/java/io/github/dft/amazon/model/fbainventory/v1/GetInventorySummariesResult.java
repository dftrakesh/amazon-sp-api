package io.github.dft.amazon.model.fbainventory.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetInventorySummariesResult {
    private Granularity granularity;
    private List<InventorySummary> inventorySummaries;
}