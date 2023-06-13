package io.github.dft.amazon.model.settelmentreport;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.github.dft.amazon.common.DateDeserializer;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Fulfillment {

    @JacksonXmlProperty(localName = "MerchantFulfillmentID")
    private String merchantFulfillmentId;

    @JacksonXmlProperty(localName = "PostedDate")
    @JsonDeserialize(using = DateDeserializer.class)
    private LocalDateTime postedDate;

    @JacksonXmlProperty(localName = "Item")
    private Item item;

    @JacksonXmlProperty(localName = "AdjustedItem")
    private AdjustedItem adjustedItem;
}