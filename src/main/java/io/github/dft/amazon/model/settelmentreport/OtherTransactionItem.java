package io.github.dft.amazon.model.settelmentreport;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class OtherTransactionItem {

    @JacksonXmlProperty(localName = "SKU")
    private String sku;

    @JacksonXmlProperty(localName = "Quantity")
    private int quantity;

    @JacksonXmlProperty(localName = "Amount")
    private Amount amount;
}