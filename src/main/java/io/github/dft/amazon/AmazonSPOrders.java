package io.github.dft.amazon;

import com.amazonaws.http.HttpMethodName;
import io.github.dft.amazon.constantcode.ConstantCodes;
import io.github.dft.amazon.model.AmazonCredentials;
import io.github.dft.amazon.model.handler.JsonBodyHandler;
import io.github.dft.amazon.model.orders.v0.*;
import io.github.dft.amazon.model.tokens.v202103.CreateRestrictedDataTokenRequest;
import io.github.dft.amazon.model.tokens.v202103.CreateRestrictedDataTokenResponse;
import io.github.dft.amazon.model.tokens.v202103.RestrictedResource;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class AmazonSPOrders extends AmazonSellingPartnerSdk {

    private static final String AMAZON_ORDERS_PII_PATH = "/orders/v0/orders";
    private static final List<String> AMAZON_ORDERS_PII_DATA_ELEMENTS = Arrays.asList("buyerInfo", "shippingAddress");

    private String restrictedAccessToken;
    private ZonedDateTime restrictedAccessTokenExpireIn;
    private final AmazonSPTokens amazonSPTokens;

    @SneakyThrows
    public AmazonSPOrders(AmazonCredentials amazonCredentials) {
        super(amazonCredentials);
        this.amazonSPTokens = new AmazonSPTokens(amazonCredentials);
    }

    @SneakyThrows
    public GetOrdersResponse getOrders(HashMap<String, String> params, boolean isPII) {

        final var signRequest = signRequest(ConstantCodes.ORDERS_API_V0, HttpMethodName.GET, params, null);

        String token = isPII ? getPIIAccessToken(createRestrictedDataToken(AMAZON_ORDERS_PII_PATH)) : amazonCredentials.getAccessToken();
        URIBuilder uriBuilder = new URIBuilder(sellingRegionEndpoint + ConstantCodes.ORDERS_API_V0);
        addParameters(uriBuilder, params);

        URI uri = uriBuilder.build();
        HttpRequest request = HttpRequest.newBuilder(uri)
                .header(ConstantCodes.HTTP_HEADER_ACCEPTS, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(ConstantCodes.HTTP_HEADER_CONTENT_TYPE, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(ConstantCodes.HTTP_HEADER_X_AMZ_ACCESS_TOKEN, token)
                .header(ConstantCodes.HTTP_HEADER_AUTHORIZATION, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_AUTHORIZATION))
                .header(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN))
                .header(ConstantCodes.X_AMZ_DATE, signRequest.getHeaders().get(ConstantCodes.X_AMZ_DATE))
                .GET()
                .build();

        HttpResponse.BodyHandler<GetOrdersResponse> handler = new JsonBodyHandler<>(GetOrdersResponse.class);

        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public GetOrdersResponse getOrders(HashMap<String, String> params) {
        return getOrders(params, false);
    }

    public CreateRestrictedDataTokenRequest createRestrictedDataToken(String restrictedTokenPath) {
        CreateRestrictedDataTokenRequest createRestrictedDataTokenRequest = new CreateRestrictedDataTokenRequest();
        List<RestrictedResource> restrictedResources = new ArrayList<>();

        RestrictedResource restrictedResource = new RestrictedResource();
        restrictedResource.setMethod("GET");
        restrictedResource.setPath(restrictedTokenPath);
        restrictedResource.setDataElements(AMAZON_ORDERS_PII_DATA_ELEMENTS);

        restrictedResources.add(restrictedResource);
        createRestrictedDataTokenRequest.setRestrictedResources(restrictedResources);

        return createRestrictedDataTokenRequest;
    }

    private String getPIIAccessToken(CreateRestrictedDataTokenRequest createRestrictedDataTokenRequest) {
        if (restrictedAccessToken == null || ZonedDateTime.now(ZoneOffset.UTC).isAfter(this.restrictedAccessTokenExpireIn)) {
            CreateRestrictedDataTokenResponse createRestrictedDataTokenResponse = amazonSPTokens.createRestrictedDataToken(createRestrictedDataTokenRequest);
            this.restrictedAccessToken = createRestrictedDataTokenResponse.getRestrictedDataToken();
            this.restrictedAccessTokenExpireIn = ZonedDateTime.now(ZoneOffset.UTC).plusSeconds(createRestrictedDataTokenResponse.getExpiresIn());
        }

        return restrictedAccessToken;
    }

    @SneakyThrows
    public GetOrderResponse getOrder(String orderId) {
        orderId = StringUtils.isEmpty(orderId) ? "" : orderId;

        final var signRequest = signRequest(ConstantCodes.ORDER_API_V0.replace("{orderId}", orderId), HttpMethodName.GET, null, null);

        HttpRequest request = HttpRequest.newBuilder(new URI(sellingRegionEndpoint + ConstantCodes.ORDERS_API_V0 + "/" + orderId))
                .header(ConstantCodes.HTTP_HEADER_ACCEPTS, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(ConstantCodes.HTTP_HEADER_CONTENT_TYPE, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(ConstantCodes.HTTP_HEADER_X_AMZ_ACCESS_TOKEN, amazonCredentials.getAccessToken())
                .header(ConstantCodes.HTTP_HEADER_AUTHORIZATION, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_AUTHORIZATION))
                .header(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN))
                .header(ConstantCodes.X_AMZ_DATE, signRequest.getHeaders().get(ConstantCodes.X_AMZ_DATE))
                .GET()
                .build();

        HttpResponse.BodyHandler<GetOrderResponse> handler = new JsonBodyHandler<>(GetOrderResponse.class);

        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public GetOrderBuyerInfoResponse getOrderBuyerInfo(String orderId) {
        orderId = StringUtils.isEmpty(orderId) ? "" : orderId;

        final var signRequest = signRequest(ConstantCodes.ORDER_BUYER_INFO_API_V0.replace("{orderId}", orderId), HttpMethodName.GET, null, null);

        HttpRequest request = HttpRequest.newBuilder(new URI(sellingRegionEndpoint + ConstantCodes.ORDER_BUYER_INFO_API_V0.replace("{orderId}", orderId)))
                .header(ConstantCodes.HTTP_HEADER_ACCEPTS, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(ConstantCodes.HTTP_HEADER_CONTENT_TYPE, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(ConstantCodes.HTTP_HEADER_X_AMZ_ACCESS_TOKEN, amazonCredentials.getAccessToken())
                .header(ConstantCodes.HTTP_HEADER_AUTHORIZATION, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_AUTHORIZATION))
                .header(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN))
                .header(ConstantCodes.X_AMZ_DATE, signRequest.getHeaders().get(ConstantCodes.X_AMZ_DATE))
                .GET()
                .build();

        HttpResponse.BodyHandler<GetOrderBuyerInfoResponse> handler = new JsonBodyHandler<>(GetOrderBuyerInfoResponse.class);

        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public GetOrderAddressResponse getAddress(String orderId) {
        orderId = StringUtils.isEmpty(orderId) ? "" : orderId;

        final var signRequest = signRequest(ConstantCodes.ORDER_ADDRESS_API_V0.replace("{orderId}", orderId), HttpMethodName.GET, null, null);

        HttpRequest request = HttpRequest.newBuilder(new URI(sellingRegionEndpoint + ConstantCodes.ORDER_ADDRESS_API_V0.replace("{orderId}", orderId)))
                .header(ConstantCodes.HTTP_HEADER_ACCEPTS, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(ConstantCodes.HTTP_HEADER_CONTENT_TYPE, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(ConstantCodes.HTTP_HEADER_X_AMZ_ACCESS_TOKEN, amazonCredentials.getAccessToken())
                .header(ConstantCodes.HTTP_HEADER_AUTHORIZATION, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_AUTHORIZATION))
                .header(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN))
                .header(ConstantCodes.X_AMZ_DATE, signRequest.getHeaders().get(ConstantCodes.X_AMZ_DATE))
                .GET()
                .build();

        HttpResponse.BodyHandler<GetOrderAddressResponse> handler = new JsonBodyHandler<>(GetOrderAddressResponse.class);

        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public GetOrderItemsResponse getOrderItems(String orderId) {
        orderId = StringUtils.isEmpty(orderId) ? "" : orderId;

        final var signRequest = signRequest(ConstantCodes.ORDER_ITEMS_API_V0.replace("{orderId}", orderId), HttpMethodName.GET, null, null);

        HttpRequest request = HttpRequest.newBuilder(new URI(sellingRegionEndpoint + ConstantCodes.ORDER_ITEMS_API_V0.replace("{orderId}", orderId)))
                .header(ConstantCodes.HTTP_HEADER_ACCEPTS, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(ConstantCodes.HTTP_HEADER_CONTENT_TYPE, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(ConstantCodes.HTTP_HEADER_X_AMZ_ACCESS_TOKEN, amazonCredentials.getAccessToken())
                .header(ConstantCodes.HTTP_HEADER_AUTHORIZATION, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_AUTHORIZATION))
                .header(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN))
                .header(ConstantCodes.X_AMZ_DATE, signRequest.getHeaders().get(ConstantCodes.X_AMZ_DATE))
                .GET()
                .build();

        HttpResponse.BodyHandler<GetOrderItemsResponse> handler = new JsonBodyHandler<>(GetOrderItemsResponse.class);

        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public GetOrderItemsBuyerInfoResponse getOrderItemsBuyerInfo(String orderId) {
        orderId = StringUtils.isEmpty(orderId) ? "" : orderId;

        final var signRequest = signRequest(ConstantCodes.ORDER_ITEMS_BUYER_INFO_API_V0.replace("{orderId}", orderId), HttpMethodName.GET, null, null);

        HttpRequest request = HttpRequest.newBuilder(new URI(sellingRegionEndpoint + ConstantCodes.ORDER_ITEMS_BUYER_INFO_API_V0.replace("{orderId}", orderId)))
                .header(ConstantCodes.HTTP_HEADER_ACCEPTS, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(ConstantCodes.HTTP_HEADER_CONTENT_TYPE, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(ConstantCodes.HTTP_HEADER_X_AMZ_ACCESS_TOKEN, amazonCredentials.getAccessToken())
                .header(ConstantCodes.HTTP_HEADER_AUTHORIZATION, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_AUTHORIZATION))
                .header(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN))
                .header(ConstantCodes.X_AMZ_DATE, signRequest.getHeaders().get(ConstantCodes.X_AMZ_DATE))
                .GET()
                .build();

        HttpResponse.BodyHandler<GetOrderItemsBuyerInfoResponse> handler = new JsonBodyHandler<>(GetOrderItemsBuyerInfoResponse.class);

        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public GetOrderRegulatedInfoResponse getOrderRegulatedInfo(String orderId) {
        orderId = StringUtils.isEmpty(orderId) ? "" : orderId;

        final var signRequest = signRequest(ConstantCodes.ORDER_REGULATED_INFO_API_V0.replace("{orderId}", orderId), HttpMethodName.GET, null, null);

        HttpRequest request = HttpRequest.newBuilder(new URI(sellingRegionEndpoint + ConstantCodes.ORDER_REGULATED_INFO_API_V0.replace("{orderId}", orderId)))
                .header(ConstantCodes.HTTP_HEADER_ACCEPTS, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(ConstantCodes.HTTP_HEADER_CONTENT_TYPE, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(ConstantCodes.HTTP_HEADER_X_AMZ_ACCESS_TOKEN, amazonCredentials.getAccessToken())
                .header(ConstantCodes.HTTP_HEADER_AUTHORIZATION, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_AUTHORIZATION))
                .header(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN))
                .header(ConstantCodes.X_AMZ_DATE, signRequest.getHeaders().get(ConstantCodes.X_AMZ_DATE))
                .GET()
                .build();

        HttpResponse.BodyHandler<GetOrderRegulatedInfoResponse> handler = new JsonBodyHandler<>(GetOrderRegulatedInfoResponse.class);

        return getRequestWrapped(request, handler);
    }

}