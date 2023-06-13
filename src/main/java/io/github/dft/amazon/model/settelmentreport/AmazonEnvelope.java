package io.github.dft.amazon.model.settelmentreport;

import lombok.Data;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "AmazonEnvelope")
@XmlAccessorType(XmlAccessType.FIELD)
public class AmazonEnvelope {

    @XmlElement(name = "Header")
    private Header header;

    @XmlElement(name = "MessageType")
    private String messageType;

    @XmlElement(name = "Message")
    private Message message;
}