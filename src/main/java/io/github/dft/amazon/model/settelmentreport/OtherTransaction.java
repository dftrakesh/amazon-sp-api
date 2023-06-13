package io.github.dft.amazon.model.settelmentreport;

import lombok.Data;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class OtherTransaction {

    @XmlElement(name = "TransactionType")
    private String transactionType;

    @XmlElement(name = "TransactionID")
    private String transactionId;

    @XmlElement(name = "PostedDate")
    private String postedDate;

    @XmlElement(name = "Amount")
    private Double amount;
}