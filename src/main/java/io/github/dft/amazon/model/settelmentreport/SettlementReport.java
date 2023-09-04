package io.github.dft.amazon.model.settelmentreport;

import com.fasterxml.jackson.annotation.JsonMerge;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import java.util.List;

@Data
@JacksonXmlRootElement(localName = "SettlementReport")
public class SettlementReport {

    @JacksonXmlProperty(localName = "SettlementData")
    private SettlementData settlementData;

    @JsonMerge
    @JacksonXmlProperty(localName = "Order")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Order> orderList;

    @JsonMerge
    @JacksonXmlProperty(localName = "Refund")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Refund> refundList;

    @JsonMerge
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "OtherTransaction")
    private List<OtherTransaction> otherTransactionList;

    @JsonMerge
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "AdvertisingTransactionDetails")
    private List<AdvertisingTransactionDetails> advertisingTransactionDetailsList;

    @JacksonXmlProperty(localName = "Retrocharge")
    private RetroCharge retroCharge;

    @JacksonXmlProperty(localName = "SellerCouponPayment")
    private SellerCouponPayment sellerCouponPayment;

    @JacksonXmlProperty(localName = "GuaranteeClaim")
    private GuaranteeClaim guaranteeClaim;

    @JacksonXmlProperty(localName = "RetrochargeReversal")
    private RetroChargeReversal retroChargeReversal;

    @JacksonXmlProperty(localName = "Chargeback")
    private ChargeBack chargeBack;

    @JacksonXmlProperty(localName = "Liquidations")
    private Liquidations liquidations;

    @JacksonXmlProperty(localName = "TaxWithholding")
    private TaxWithholding taxWithholding;
}