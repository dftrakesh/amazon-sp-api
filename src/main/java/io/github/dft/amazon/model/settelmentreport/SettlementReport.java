package io.github.dft.amazon.model.settelmentreport;

import lombok.Data;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class SettlementReport {

    @XmlElement(name = "SettlementData")
    private SettlementData settlementData;

    @XmlElement(name = "Order")
    private List<Order> order;

    @XmlElement(name = "Refund")
    private List<Refund> refund;

    @XmlElement(name = "OtherTransaction")
    private List<OtherTransaction> otherTransaction;

    @XmlElement(name = "AdvertisingTransactionDetails")
    private List<AdvertisingTransactionDetails> advertisingTransactionDetails;
}