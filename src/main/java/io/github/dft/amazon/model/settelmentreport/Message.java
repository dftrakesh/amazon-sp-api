package io.github.dft.amazon.model.settelmentreport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {

    @JacksonXmlProperty(localName = "MessageID")
    private Integer messageId;

    @JacksonXmlProperty(localName = "SettlementReport")
    private SettlementReport settlementReport;
}