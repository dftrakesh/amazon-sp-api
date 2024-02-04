package io.github.dft.amazon.constantcode;

public interface ConstantCodes {

    String HTTP_OAUTH_PARAMETER_GRANT_TYPE = "grant_type";
    String HTTP_OAUTH_PARAMETER_REFRESH_TOKEN = "refresh_token";
    String HTTP_OAUTH_PARAMETER_CLIENT_ID = "client_id";
    String HTTP_OAUTH_PARAMETER_CLIENT_SECRET = "client_secret";
    String REFRESH_TOKEN = "refresh_token";

    String HTTP_HEADER_ACCEPTS = "Accept";
    String HTTP_HEADER_AUTHORIZATION = "Authorization";
    String HTTP_HEADER_X_AMZ_SECURITY_TOKEN = "X-Amz-Security-Token";
    String HTTP_HEADER_CONTENT_TYPE = "Content-Type";
    String HTTP_HEADER_X_AMZ_ACCESS_TOKEN = "x-amz-access-token";
    String HTTP_HEADER_VALUE_APPLICATION_JSON = "application/json";
    String HTTP_HEADER_VALUE_APPLICATION_FORM_URL_ENCODED = "application/x-www-form-urlencoded";

    String SELLERS_API_V1 = "/sellers/v1/marketplaceParticipations";
    String FEEDS_API_V20160630 = "/feeds/2021-06-30/feeds";
    String FEEDS_API_V20160630_CREATE_FEED_DOCUMENT = "/feeds/2021-06-30/documents";
    String REPORTS_API_V202106 = "/reports/2021-06-30/reports";
    String REPORT_DOCUMENTS_API_V202106 = "/reports/2021-06-30/documents";
    String REPORT_SCHEDULES_API_V202106 = "/reports/2021-06-30/schedules";
    String ORDERS_API_V0 = "/orders/v0/orders";
    String ORDER_API_V0 = "/orders/v0/orders/{orderId}";
    String ORDER_BUYER_INFO_API_V0 = "/orders/v0/orders/{orderId}/buyerInfo";
    String ORDER_ADDRESS_API_V0 = "/orders/v0/orders/{orderId}/address";
    String ORDER_ITEMS_API_V0 = "/orders/v0/orders/{orderId}/orderItems";
    String ORDER_ITEMS_BUYER_INFO_API_V0 = "/orders/v0/orders/{orderId}/orderItems/buyerInfo";
    String ORDER_REGULATED_INFO_API_V0 = "/orders/v0/orders/{orderId}/regulatedInfo";
    String TOKENS_API_V202103 = "/tokens/2021-03-01/restrictedDataToken";
    String  PRODUCTFEES_API_FEESESTIMATE_FOR_SKU_V0 = "/products/fees/v0/listings/%s/feesEstimate";
    String  PRODUCTFEES_API_FEESESTIMATE_FOR_ASIN_V0 = "/products/fees/v0/items/%s/feesEstimate";
    String  PRODUCTFEES_API_MY_FEESESTIMATE_V0 = "/products/fees/v0/feesEstimate";
    String LWA_AUTHORIZATION_SERVER = "https://api.amazon.com/auth/o2/token";
    String CATALOG_ITEMS_API_V202204 = "/catalog/2022-04-01/items/";
    String CATALOG_CATEGORIES_API_V0 = "/catalog/v0/categories";
    String FULFILLMENT_OUTBOUND_API_V202007 = "/fba/outbound/2020-07-01/fulfillmentOrders";
    String FBA_INVENTORY_API_V1 = "/fba/inventory/v1/summaries";
    String OFFERS_ENDPOINT = "/offers";
    String PRODUCTS_PRICING_ITEMS_V0 = "/products/pricing/v0/items/";
    String BATCH_PRODUCTS_PRICING_ITEMS_OFFERS_V0 = "/batches/products/pricing/v0/itemOffers";
    String BATCH_PRODUCTS_PRICING_ITEMS_LISTINGS_V0 = "/batches/products/pricing/v0/listingOffers";
    String PRODUCTS_PRICING_LISTINGS_V0 = "/products/pricing/v0/listings/";
    String PRODUCTS_PRICING_COMPETITIVE_PRICE_API_V0 = "/products/pricing/v0/competitivePrice";
    String PRODUCTS_PRICING_GET_LISTING_OFFERS = "/products/pricing/v0/listings/%s/offers";
    String NOTIFICATION_SUBSCRIPTION_API_V1 = "/notifications/v1/subscriptions";
    String LISTING_ITEMS_V20210801 = "/listings/2021-08-01/items";

    String FULFILLMENT_INBOUND_SHIPMENT_VO_SHIPMENTS = "/fba/inbound/v0/shipments";
    String FULFILLMENT_INBOUND_SHIPMENT_VO_SHIPMENT_ITEMS = "/fba/inbound/v0/shipments/%s/items";
    String FORWARD_SLASH = "/";

    String AWS_REGION_US_EAST_1 = "us-east-1";
    String AWS_REGION_US_WEST_1 = "us-west-2";
    String AWS_REGION_EU_WEST_1 = "eu-west-1";

    String SERVICE_NAME = "execute-api";
    String X_AMZ_DATE = "X-Amz-Date" ;

    int MAX_ATTEMPTS = 50;
    int TIME_OUT_DURATION = 15000;

    String USA_MARKETPLACE_ID = "ATVPDKIKX0DER";
    String PARAMETER_NAME_ITEM_TYPE = "ItemType";
    String PARAMETER_VALUE_ITEM_TYPE_SKU = "Sku";
    String PARAMETER_VALUE_ITEM_TYPE_ASIN = "ASIN";
}