package io.github.dft.amazon;

import com.amazonaws.http.HttpMethodName;
import com.google.common.util.concurrent.RateLimiter;
import io.github.dft.amazon.constantcode.ConstantCodes;
import io.github.dft.amazon.model.AmazonCredentials;
import io.github.dft.amazon.model.handler.JsonBodyHandler;
import io.github.dft.amazon.model.orders.v0.*;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class AmazonSPOrders extends AmazonSellingPartnerSdk {

    private final RateLimiter rateLimiter;

    @SneakyThrows
    public AmazonSPOrders(AmazonCredentials amazonCredentials) {
        super(amazonCredentials);
        this.rateLimiter = RateLimiter.create(0.8);
    }

    @SneakyThrows
    public GetOrdersResponse getOrders(HashMap<String, String> params) {

        final var signRequest = signRequest(ConstantCodes.ORDERS_API_V0, HttpMethodName.GET, params, null);

        URIBuilder uriBuilder = new URIBuilder(sellingRegionEndpoint + ConstantCodes.ORDERS_API_V0);
        addParameters(uriBuilder, params);

        URI uri = uriBuilder.build();
        HttpRequest request = HttpRequest.newBuilder(uri)
                .header(ConstantCodes.HTTP_HEADER_ACCEPTS, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(ConstantCodes.HTTP_HEADER_CONTENT_TYPE, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(ConstantCodes.HTTP_HEADER_X_AMZ_ACCESS_TOKEN, amazonCredentials.getAccessToken())
                .header(ConstantCodes.HTTP_HEADER_AUTHORIZATION, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_AUTHORIZATION))
                .header(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN))
                .header(ConstantCodes.X_AMZ_DATE, signRequest.getHeaders().get(ConstantCodes.X_AMZ_DATE))
                .GET()
                .build();

        HttpResponse.BodyHandler<GetOrdersResponse> handler = new JsonBodyHandler<>(GetOrdersResponse.class);

        return getRequestWrapped(request, handler);
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