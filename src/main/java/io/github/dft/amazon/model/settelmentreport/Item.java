package io.github.dft.amazon.model.settelmentreport;

import lombok.Data;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Item {

    @XmlElement(name = "AmazonOrderItemCode")
    private String amazonOrderItemCode;

    @XmlElement(name = "SKU")
    private String sku;

    @XmlElement(name = "Quantity")
    private String quantity;

    @XmlElement(name = "ItemPrice")
    private ItemPrice itemPrice;

    @XmlElement(name = "ItemFees")
    private ItemFees itemFees;
}