package io.github.dft.amazon.model.settelmentreport;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class SellerCouponPayment {

    @JacksonXmlProperty(localName = "PostedDate")
    private String postedDate;

    @JacksonXmlProperty(localName = "TransactionType")
    private String transactionType;

    @JacksonXmlProperty(localName = "PaymentReason")
    private String paymentReason;

    @JacksonXmlProperty(localName = "CouponFee")
    private CouponFee couponFee;

    @JacksonXmlProperty(localName = "Count")
    private int count;

    @JacksonXmlProperty(localName = "CouponID")
    private String couponID;

    @JacksonXmlProperty(localName = "MarketplaceName")
    private String marketplaceName;
}