package io.github.dft.amazon.model.settelmentreport;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.github.dft.amazon.common.DateDeserializer;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class SettlementData {

    @JacksonXmlProperty(localName = "AmazonSettlementID")
    private Long AmazonSettlementID;

    @JacksonXmlProperty(localName = "TotalAmount")
    private Double totalAmount;

    @JacksonXmlProperty(localName = "StartDate")
    @JsonDeserialize(using = DateDeserializer.class)
    private LocalDateTime StartDate;

    @JacksonXmlProperty(localName = "EndDate")
    @JsonDeserialize(using = DateDeserializer.class)
    private LocalDateTime EndDate;

    @JacksonXmlProperty(localName = "DepositDate")
    @JsonDeserialize(using = DateDeserializer.class)
    private LocalDateTime DepositDate;
}