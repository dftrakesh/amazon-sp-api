package io.github.dft.amazon.model.settelmentreport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.github.dft.amazon.common.DateDeserializer;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OtherTransaction {

    @JacksonXmlProperty(localName = "TransactionType")
    private String transactionType;

    @JacksonXmlProperty(localName = "TransactionID")
    private String transactionId;

    @JacksonXmlProperty(localName = "PostedDate")
    @JsonDeserialize(using = DateDeserializer.class)
    private LocalDateTime postedDate;

    @JacksonXmlProperty(localName = "Amount")
    private Double amount;
}