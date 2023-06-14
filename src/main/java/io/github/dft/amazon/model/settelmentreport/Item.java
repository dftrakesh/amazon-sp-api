package io.github.dft.amazon.model.settelmentreport;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class Item {

    @JacksonXmlProperty(localName = "AmazonOrderItemCode")
    private String amazonOrderItemCode;

    @JacksonXmlProperty(localName = "SKU")
    private String sku;

    @JacksonXmlProperty(localName = "Quantity")
    private String quantity;

    @JacksonXmlProperty(localName = "ItemPrice")
    private ItemPrice itemPrice;

    @JacksonXmlProperty(localName = "ItemFees")
    private ItemFees itemFees;
}