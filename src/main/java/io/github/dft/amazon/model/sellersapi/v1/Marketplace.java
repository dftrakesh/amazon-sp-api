package io.github.dft.amazon.model.sellersapi.v1;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class Marketplace {

    private String id;
    private String name;
    private String countryCode;
    private String defaultCurrencyCode;
    private String defaultLanguageCode;
    private String domainName;
}
