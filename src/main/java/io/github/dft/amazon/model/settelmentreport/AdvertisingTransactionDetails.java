package io.github.dft.amazon.model.settelmentreport;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.github.dft.amazon.common.DateDeserializer;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AdvertisingTransactionDetails {

    @JacksonXmlProperty(localName = "TransactionType")
    private String transactionType;

    @JacksonXmlProperty(localName = "PostedDate")
    @JsonDeserialize(using = DateDeserializer.class)
    private LocalDateTime postedDate;

    @JacksonXmlProperty(localName = "InvoiceId")
    private String invoiceId;

    @JacksonXmlProperty(localName = "BaseAmount")
    private Double baseAmount;

    @JacksonXmlProperty(localName = "TaxAmount")
    private Double taxAmount;

    @JacksonXmlProperty(localName = "TransactionAmount")
    private Double transactionAmount;
}