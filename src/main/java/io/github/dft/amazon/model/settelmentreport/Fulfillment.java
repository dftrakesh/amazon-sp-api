package io.github.dft.amazon.model.settelmentreport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.github.dft.amazon.common.DateDeserializer;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
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