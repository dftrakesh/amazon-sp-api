package io.github.dft.amazon.model.settelmentreport;

import com.fasterxml.jackson.annotation.JsonMerge;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import java.util.List;

@Data
@JacksonXmlRootElement(localName = "SettlementReport")
public class SettlementReport {

    @JacksonXmlProperty(localName = "SettlementData")
    private SettlementData settlementData;

    @JsonMerge
    @JacksonXmlProperty(localName = "Order")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Order> order;

    @JsonMerge
    @JacksonXmlProperty(localName = "Refund")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Refund> refund;

    @JsonMerge
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "OtherTransaction")
    private List<OtherTransaction> otherTransaction;

    @JsonMerge
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "AdvertisingTransactionDetails")
    private List<AdvertisingTransactionDetails> advertisingTransactionDetails;
}