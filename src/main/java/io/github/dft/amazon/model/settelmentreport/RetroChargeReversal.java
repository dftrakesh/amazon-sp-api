package io.github.dft.amazon.model.settelmentreport;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class RetroChargeReversal {

    @JacksonXmlProperty(localName = "PostedDate")
    private String postedDate;

    @JacksonXmlProperty(localName = "BaseTax")
    private BaseTax baseTax;

    @JacksonXmlProperty(localName = "ShippingTax")
    private ShippingTax shippingTax;

    @JacksonXmlProperty(localName = "MarketplaceName")
    private String marketplaceName;

    @JacksonXmlProperty(localName = "AmazonOrderID")
    private String amazonOrderID;

    @JacksonXmlProperty(localName = "Charge")
    private Charge charge;
}