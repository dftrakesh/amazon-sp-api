package io.github.dft.amazon.model.settelmentreport;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "AmazonEnvelope")
public class SettlementReportResponse {

    private String noNamespaceSchemaLocation;

    @JacksonXmlProperty(localName = "Message")
    private Message message;

    @JacksonXmlProperty(localName = "Header")
    private Header header;

    @JacksonXmlProperty(localName = "MessageType")
    private String messageType;
}