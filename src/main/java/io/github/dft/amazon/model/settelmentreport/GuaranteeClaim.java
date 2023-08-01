package io.github.dft.amazon.model.settelmentreport;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class GuaranteeClaim {

    @JacksonXmlProperty(localName = "AmazonOrderID")
    private String amazonOrderID;

    @JacksonXmlProperty(localName = "AdjustmentID")
    private String adjustmentID;

    @JacksonXmlProperty(localName = "MarketplaceName")
    private String marketplaceName;

    @JacksonXmlProperty(localName = "Fulfillment")
    private Fulfillment fulfillment;
}