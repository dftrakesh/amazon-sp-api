package io.github.dft.amazon.model.settelmentreport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Charge {

    @JacksonXmlProperty(localName = "Type")
    private String type;

    @JacksonXmlProperty(localName = "Amount")
    private Amount amount;
}