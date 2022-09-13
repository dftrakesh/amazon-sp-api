package io.github.dft.amazon.model.orders.v0;

import lombok.Data;

@Data
public class Order {

    private String amazonOrderId;
    private String sellerOrderId;
    private String purchaseDate;
    private String lastUpdateDate;
    private OrderStatusEnum orderStatus;
    private FulfillmentChannelEnum fulfillmentChannel;
    private String salesChannel;
    private String orderChannel;
    private String shipServiceLevel;
    private Money orderTotal;
    private Integer numberOfItemsShipped;
    private Integer numberOfItemsUnshipped;
    private PaymentExecutionDetailItemList paymentExecutionDetail;
    private PaymentMethodEnum paymentMethod;
    private PaymentMethodDetailItemList paymentMethodDetails;
    private String marketplaceId;
    private String shipmentServiceLevelCategory;
    private EasyShipShipmentStatusEnum easyShipShipmentStatus;
    private String cbaDisplayableShippingLabel;
    private OrderTypeEnum orderType;
    private String earliestShipDate;
    private String latestShipDate;
    private String earliestDeliveryDate;
    private String latestDeliveryDate;
    private Boolean isBusinessOrder;
    private Boolean isPrime;
    private Boolean isPremiumOrder;
    private Boolean isGlobalExpressEnabled;
    private String replacedOrderId;
    private Boolean isReplacementOrder;
    private String promiseResponseDueDate;
    private Boolean isEstimatedShipDateSet;
    private Boolean isSoldByAB;
    private Boolean isIBA;
    private Address defaultShipFromLocationAddress;
    private BuyerInvoicePreferenceEnum buyerInvoicePreference;
    private BuyerTaxInformation buyerTaxInformation;
    private FulfillmentInstruction fulfillmentInstruction;
    private Boolean isISPU;
    private Boolean isAccessPointOrder;
    private MarketplaceTaxInfo marketplaceTaxInfo;
    private String sellerDisplayName;
    private Address shippingAddress;
    private BuyerInfo buyerInfo;
    private AutomatedShippingSettings automatedShippingSettings;
    private Boolean hasRegulatedItems;
    private ElectronicInvoiceStatus electronicInvoiceStatus;

    private enum OrderStatusEnum {
        Pending, Unshipped, PartiallyShipped, Shipped, Unfulfillable, InvoiceUnconfirmed, PendingAvailability
    }

    private enum FulfillmentChannelEnum {
        MFN, AFN
    }

    private enum PaymentMethodEnum {
        COD, CVS, Other
    }

    private enum OrderTypeEnum {
        StandardOrder, LongLeadTimeOrder, Preorder, BackOrder, SourcingOnDemandOrder
    }

    private enum EasyShipShipmentStatusEnum {
        PendingSchedule, PendingPickUp, PendingDropOff, LabelCanceled, PickedUp, DroppedOff, AtOriginFC, AtDestinationFC, Delivered,
        RejectedByBuyer, Undeliverable, ReturningToSeller, ReturnedToSeller, Lost, OutForDelivery, Damaged
    }

    private enum BuyerInvoicePreferenceEnum {
        INDIVIDUAL, BUSINESS
    }

    private enum ElectronicInvoiceStatus {
        NotRequired, NotFound, Processing, Errored, Accepted
    }
}