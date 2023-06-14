package io.github.dft.amazon.model.settelmentreport;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class Promotion {

    @JacksonXmlProperty(localName = "MerchantPromotionID")
    private String merchantPromotionId;

    @JacksonXmlProperty(localName = "Type")
    private String type;

    @JacksonXmlProperty(localName = "Amount")
    private Amount amount;
}