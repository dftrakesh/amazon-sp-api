package io.github.dft.amazon.model.settelmentreport;

import lombok.Data;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class AdvertisingTransactionDetails {

    @XmlElement(name = "TransactionType")
    private String transactionType;

    @XmlElement(name = "PostedDate")
    private String postedDate;

    @XmlElement(name = "InvoiceId")
    private String invoiceId;

    @XmlElement(name = "BaseAmount")
    private Double baseAmount;

    @XmlElement(name = "TaxAmount")
    private Double taxAmount;

    @XmlElement(name = "TransactionAmount")
    private Double transactionAmount;
}