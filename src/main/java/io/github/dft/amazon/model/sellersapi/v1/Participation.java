package io.github.dft.amazon.model.sellersapi.v1;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class Participation {

    private Boolean isParticipating;
    private Boolean hasSuspendedListings;

}
