package io.github.dft.amazon.model.settelmentreport;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class AdjustedItem {

    @JacksonXmlProperty(localName = "AmazonOrderItemCode")
    private Long amazonOrderItemCode;

    @JacksonXmlProperty(localName = "MerchantAdjustmentItemID")
    private Long merchantAdjustmentItemId;

    @JacksonXmlProperty(localName = "SKU")
    private String sku;

    @JacksonXmlProperty(localName = "ItemPriceAdjustments")
    private ItemPriceAdjustments itemPriceAdjustments;

    @JacksonXmlProperty(localName = "ItemFeeAdjustments")
    private ItemFeeAdjustments itemFeeAdjustments;
}