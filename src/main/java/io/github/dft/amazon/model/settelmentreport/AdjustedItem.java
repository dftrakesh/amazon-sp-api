package io.github.dft.amazon.model.settelmentreport;

import lombok.Data;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class AdjustedItem {

    @XmlElement(name = "AmazonOrderItemCode")
    private String amazonOrderItemCode;

    @XmlElement(name = "MerchantAdjustmentItemID")
    private String merchantAdjustmentItemId;

    @XmlElement(name = "SKU")
    private String sku;

    @XmlElement(name = "ItemPriceAdjustments")
    private ItemPriceAdjustments itemPriceAdjustments;

    @XmlElement(name = "ItemFeeAdjustments")
    private ItemFeeAdjustments itemFeeAdjustments;
}