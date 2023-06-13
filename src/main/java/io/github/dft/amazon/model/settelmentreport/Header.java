package io.github.dft.amazon.model.settelmentreport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Header {

    @JacksonXmlProperty(localName = "DocumentVersion")
    private String documentVersion;

    @JacksonXmlProperty(localName = "MerchantIdentifier")
    private String merchantIdentifier;
}