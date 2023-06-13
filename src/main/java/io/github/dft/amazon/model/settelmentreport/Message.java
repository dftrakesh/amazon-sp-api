package io.github.dft.amazon.model.settelmentreport;

import lombok.Data;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Message {

    @XmlElement(name = "MessageID")
    private Integer messageId;

    @XmlElement(name = "SettlementReport")
    private SettlementReport settlementReport;
}