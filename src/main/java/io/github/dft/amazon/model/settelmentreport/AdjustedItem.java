package io.github.dft.amazon.model.settelmentreport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdjustedItem {

    @JacksonXmlProperty(localName = "AmazonOrderItemCode")
    private String amazonOrderItemCode;

    @JacksonXmlProperty(localName = "MerchantAdjustmentItemID")
    private String merchantAdjustmentItemId;

    @JacksonXmlProperty(localName = "SKU")
    private String sku;

    @JacksonXmlProperty(localName = "ItemPriceAdjustments")
    private ItemPriceAdjustments itemPriceAdjustments;

    @JacksonXmlProperty(localName = "ItemFeeAdjustments")
    private ItemFeeAdjustments itemFeeAdjustments;
}