package io.github.dft.amazon.model.settelmentreport;

import lombok.Data;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Order {

    @XmlElement(name = "AmazonOrderID")
    private String amazonOrderId;

    @XmlElement(name = "ShipmentID")
    private String shipmentId;

    @XmlElement(name = "MarketplaceName")
    private String marketplaceName;

    @XmlElement(name = "MerchantOrderID")
    private String merchantOrderId;

    @XmlElement(name = "Fulfillment")
    private Fulfillment fulfillment;
}