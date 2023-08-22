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

    @JacksonXmlProperty(localName = "MerchantOrderItemID")
    private String merchantOrderItemID;

    @JacksonXmlProperty(localName = "ItemPrice")
    private ItemPrice itemPrice;

    @JacksonXmlProperty(localName = "ItemFees")
    private ItemFees itemFees;

    @JacksonXmlProperty(localName = "OrderItemCode")
    private String orderItemCode;

    @JacksonXmlProperty(localName = "ItemPriceAdjustments")
    private ItemPriceAdjustments itemPriceAdjustments;

    @JacksonXmlProperty(localName = "Component")
    private Component component;

    @JacksonXmlProperty(localName = "ShipmentID")
    private String shipmentID;
}