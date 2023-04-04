package io.github.dft.amazon.model.fbainventory.v1;

import lombok.Data;
import java.util.List;

@Data
public class ResearchingQuantity {
    private Integer totalResearchingQuantity;
    private List<ResearchingQuantityEntry> researchingQuantityBreakdown;
}