package io.github.dft.amazon.model.settelmentreport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemPriceAdjustments {

    @JacksonXmlProperty(localName = "Component")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Component> component;
}