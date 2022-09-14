package io.github.dft.amazon.model.orders.v0;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderItem {

    @JsonProperty("ASIN")
    private String ASIN;

    @JsonProperty("SellerSKU")
    private String sellerSKU;

    @JsonProperty("OrderItemId")
    private String orderItemId;

    @JsonProperty("Title")
    private String title;

    @JsonProperty("QuantityOrdered")
    private Integer quantityOrdered;

    @JsonProperty("QuantityShipped")
    private Integer quantityShipped;

    @JsonProperty("ProductInfo")
    private ProductInfoDetail productInfo;

    @JsonProperty("PointsGranted")
    private PointsGrantedDetail pointsGranted;

    @JsonProperty("ItemPrice")
    private Money itemPrice;

    @JsonProperty("ShippingPrice")
    private Money shippingPrice;

    @JsonProperty("ItemTax")
    private Money itemTax;

    @JsonProperty("ShippingTax")
    private Money shippingTax;

    @JsonProperty("ShippingDiscount")
    private Money shippingDiscount;

    @JsonProperty("ShippingDiscountTax")
    private Money shippingDiscountTax;

    @JsonProperty("PromotionDiscount")
    private Money promotionDiscount;

    @JsonProperty("PromotionDiscountTax")
    private Money promotionDiscountTax;

    @JsonProperty("PromotionIds")
    private List<String> promotionIds;

    @JsonProperty("CODFee")
    private Money CodFee;

    @JsonProperty("CODFeeDiscount")
    private Money CodFeeDiscount;

    @JsonProperty("IsGift")
    private Boolean isGift;

    @JsonProperty("ConditionNote")
    private String conditionNote;

    @JsonProperty("ConditionId")
    private String conditionId;

    @JsonProperty("ConditionSubtypeId")
    private String conditionSubtypeId;

    @JsonProperty("ScheduledDeliveryStartDate")
    private String scheduledDeliveryStartDate;

    @JsonProperty("ScheduledDeliveryEndDate")
    private String scheduledDeliveryEndDate;

    @JsonProperty("PriceDesignation")
    private String priceDesignation;

    @JsonProperty("TaxCollection")
    private TaxCollection taxCollection;

    @JsonProperty("SerialNumberRequired")
    private Boolean serialNumberRequired;

    @JsonProperty("IsTransparency")
    private Boolean isTransparency;

    @JsonProperty("IossNumber")
    private String iossNumber;

    @JsonProperty("StoreChainStoreId")
    private String storeChainStoreId;

    @JsonProperty("DeemedResellerCategory")
    private String deemedResellerCategory;

    @JsonProperty("BuyerInfo")
    private ItemBuyerInfo buyerInfo;

    @JsonProperty("BuyerRequestedCancel")
    private BuyerRequestedCancel buyerRequestedCancel;
}