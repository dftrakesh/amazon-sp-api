package io.github.dft.amazon.model.settelmentreport;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.github.dft.amazon.common.DateDeserializer;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OtherTransaction {

    @JacksonXmlProperty(localName = "TransactionType")
    private String transactionType;

    @JacksonXmlProperty(localName = "AmazonOrderID")
    private String amazonOrderId;

    @JacksonXmlProperty(localName = "MerchantFulfillmentID")
    private String merchantFulfillmentId;

    @JacksonXmlProperty(localName = "MarketplaceName")
    private String marketplaceName;

    @JacksonXmlProperty(localName = "TransactionID")
    private String transactionId;

    @JacksonXmlProperty(localName = "PostedDate")
    @JsonDeserialize(using = DateDeserializer.class)
    private LocalDateTime postedDate;

    @JacksonXmlProperty(localName = "Amount")
    private Amount amount;

    @JacksonXmlProperty(localName = "Fees")
    private List<Fee> feeList;

    @JacksonXmlProperty(localName = "ShipmentID")
    private Long shipmentId;
}