package io.github.dft.amazon.model.orders.v0;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {

    @JsonProperty("AmazonOrderId")
    private String amazonOrderId;

    @JsonProperty("SellerOrderId")
    private String sellerOrderId;

    @JsonProperty("PurchaseDate")
    private String purchaseDate;

    @JsonProperty("LastUpdateDate")
    private String lastUpdateDate;

    @JsonProperty("OrderStatus")
    private String orderStatus;

    @JsonProperty("FulfillmentChannel")
    private String fulfillmentChannel;

    @JsonProperty("SalesChannel")
    private String salesChannel;

    @JsonProperty("OrderChannel")
    private String orderChannel;

    @JsonProperty("ShipServiceLevel")
    private String shipServiceLevel;

    @JsonProperty("OrderTotal")
    private Money orderTotal;

    @JsonProperty("NumberOfItemsShipped")
    private Integer numberOfItemsShipped;

    @JsonProperty("NumberOfItemsUnshipped")
    private Integer numberOfItemsUnshipped;

    @JsonProperty("PaymentExecutionDetailItemList")
    private PaymentExecutionDetailItemList PaymentExecutionDetailItemList;

    @JsonProperty("PaymentMethod")
    private String paymentMethod;

    @JsonProperty("PaymentMethodDetailItemList")
    private PaymentMethodDetailItemList paymentMethodDetails;

    @JsonProperty("MarketplaceId")
    private String marketplaceId;

    @JsonProperty("ShipmentServiceLevelCategory")
    private String shipmentServiceLevelCategory;

    @JsonProperty("EasyShipShipmentStatus")
    private String easyShipShipmentStatus;

    @JsonProperty("CbaDisplayableShippingLabel")
    private String cbaDisplayableShippingLabel;

    @JsonProperty("OrderType")
    private String orderType;

    @JsonProperty("EarliestShipDate")
    private String earliestShipDate;

    @JsonProperty("LatestShipDate")
    private String latestShipDate;

    @JsonProperty("EarliestDeliveryDate")
    private String earliestDeliveryDate;

    @JsonProperty("LatestDeliveryDate")
    private String latestDeliveryDate;

    @JsonProperty("IsBusinessOrder")
    private Boolean isBusinessOrder;

    @JsonProperty("IsPrime")
    private Boolean isPrime;

    @JsonProperty("IsPremiumOrder")
    private Boolean isPremiumOrder;

    @JsonProperty("IsGlobalExpressEnabled")
    private Boolean isGlobalExpressEnabled;

    @JsonProperty("ReplacedOrderId")
    private String replacedOrderId;

    @JsonProperty("IsReplacementOrder")
    private Boolean isReplacementOrder;

    @JsonProperty("PromiseResponseDueDate")
    private String promiseResponseDueDate;

    @JsonProperty("IsEstimatedShipDateSet")
    private Boolean isEstimatedShipDateSet;

    @JsonProperty("IsSoldByAB")
    private Boolean isSoldByAB;

    @JsonProperty("IsIBA")
    private Boolean isIBA;

    @JsonProperty("DefaultShipFromLocationAddress")
    private Address defaultShipFromLocationAddress;

    @JsonProperty("BuyerInvoicePreference")
    private String buyerInvoicePreference;

    @JsonProperty("BuyerTaxInformation")
    private BuyerTaxInformation buyerTaxInformation;

    @JsonProperty("FulfillmentInstruction")
    private FulfillmentInstruction fulfillmentInstruction;

    @JsonProperty("IsISPU")
    private Boolean isISPU;

    @JsonProperty("IsAccessPointOrder")
    private Boolean isAccessPointOrder;

    @JsonProperty("MarketplaceTaxInfo")
    private MarketplaceTaxInfo marketplaceTaxInfo;

    @JsonProperty("SellerDisplayName")
    private String sellerDisplayName;

    @JsonProperty("ShippingAddress")
    private Address shippingAddress;

    @JsonProperty("BuyerInfo")
    private BuyerInfo buyerInfo;

    @JsonProperty("AutomatedShippingSettings")
    private AutomatedShippingSettings automatedShippingSettings;

    @JsonProperty("HasRegulatedItems")
    private Boolean hasRegulatedItems;

    @JsonProperty("ElectronicInvoiceStatus")
    private String electronicInvoiceStatus;
}