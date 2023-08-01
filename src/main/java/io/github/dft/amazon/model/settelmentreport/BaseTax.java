package io.github.dft.amazon.model.settelmentreport;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class BaseTax {

    @JacksonXmlProperty(localName = "Amount")
    private Amount amount;
}