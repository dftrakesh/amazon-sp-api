package io.github.dft.amazon.model.settelmentreport;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class Item {

    @JacksonXmlProperty(localName = "AmazonOrderItemCode")
    private Long amazonOrderItemCode;

    @JacksonXmlProperty(localName = "SKU")
    private String sku;

    @JacksonXmlProperty(localName = "Promotion")
    private Promotion promotion;

    @JacksonXmlProperty(localName = "Quantity")
    private Integer quantity;

    @JacksonXmlProperty(localName = "ItemPrice")
    private ItemPrice itemPrice;

    @JacksonXmlProperty(localName = "ItemFees")
    private ItemFees itemFees;
}