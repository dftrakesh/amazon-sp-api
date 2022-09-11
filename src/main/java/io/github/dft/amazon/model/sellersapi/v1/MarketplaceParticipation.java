package io.github.dft.amazon.model.sellersapi.v1;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
public class MarketplaceParticipation {

    private Marketplace marketplace;
    private Participation participation;
}
