package io.github.dft.amazon.model.settelmentreport;

import lombok.Data;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class SettlementData {

    @XmlElement(name = "AmazonSettlementID")
    private Long AmazonSettlementID;

    @XmlElement(name = "TotalAmount")
    private Double totalAmount;

    @XmlElement(name = "StartDate")
    private String StartDate;

    @XmlElement(name = "EndDate")
    private String EndDate;

    @XmlElement(name = "DepositDate")
    private String DepositDate;
}