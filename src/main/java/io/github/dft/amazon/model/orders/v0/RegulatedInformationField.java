package io.github.dft.amazon.model.orders.v0;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegulatedInformationField {

    @JsonProperty("FieldId")
    private String fieldId;

    @JsonProperty("FieldLabel")
    private String fieldLabel;

    @JsonProperty("FieldType")
    private String fieldType;

    @JsonProperty("FieldValue")
    private String fieldValue;
}