package io.github.dft.amazon.model.productprice.listinganditemoffer;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class Offer {

    private Shipping shipping;
    private ListingPrice listingPrice;
    private ShippingTime shippingTime;
    private SellerFeedbackRating sellerFeedbackRating;
    private ShipsFrom shipsFrom;
    private String subCondition;
    private String sellerId;
    private Boolean isFeaturedMerchant;
    private Boolean isBuyBoxWinner;
    private Boolean myOffer;
    private Boolean isFulfilledByAmazon;
}