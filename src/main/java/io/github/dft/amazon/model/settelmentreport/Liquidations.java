package io.github.dft.amazon.model.settelmentreport;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class Liquidations {

    @JacksonXmlProperty(localName = "PostedDate")
    private String postedDate;

    @JacksonXmlProperty(localName = "OrderId")
    private String orderId;

    @JacksonXmlProperty(localName = "SellerOrderId")
    private String sellerOrderId;

    @JacksonXmlProperty(localName = "Item")
    private Item item;
}