package io.github.dft.amazon.model.settelmentreport;

import lombok.Data;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Header {

    @XmlElement(name = "DocumentVersion")
    private String documentVersion;

    @XmlElement(name = "MerchantIdentifier")
    private String merchantIdentifier;
}