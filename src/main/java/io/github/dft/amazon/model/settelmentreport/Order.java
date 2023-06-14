package io.github.dft.amazon.model.settelmentreport;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "Order")
public class Order {

    @JacksonXmlProperty(localName = "AmazonOrderID")
    private String amazonOrderId;

    @JacksonXmlProperty(localName = "ShipmentID")
    private String shipmentId;

    @JacksonXmlProperty(localName = "MarketplaceName")
    private String marketplaceName;

    @JacksonXmlProperty(localName = "MerchantOrderID")
    private String merchantOrderId;

    @JacksonXmlProperty(localName = "Fulfillment")
    private Fulfillment fulfillment;
}