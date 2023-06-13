package io.github.dft.amazon.model.settelmentreport;

import lombok.Data;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Fulfillment {

    @XmlElement(name = "MerchantFulfillmentID")
    private String merchantFulfillmentId;

    @XmlElement(name = "PostedDate")
    private String postedDate;

    @XmlElement(name = "Item")
    private Item item;

    @XmlElement(name = "AdjustedItem")
    private AdjustedItem adjustedItem;
}