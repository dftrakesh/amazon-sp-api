package io.github.dft.amazon.model.settelmentreport;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import java.util.List;

@Data
public class ItemFeeAdjustments {

    @JacksonXmlProperty(localName = "Fee")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Fee> feeList;
}