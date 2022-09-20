package io.github.dft.amazon.model.sellersapi.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarketplaceParticipation {

    private Marketplace marketplace;
    private Participation participation;
}
