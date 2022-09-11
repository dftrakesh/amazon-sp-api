package io.github.dft.amazon.model.sellersapi.v1;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
public class GetMarketplaceParticipationsResponse {

    List<MarketplaceParticipation> payload;
}

