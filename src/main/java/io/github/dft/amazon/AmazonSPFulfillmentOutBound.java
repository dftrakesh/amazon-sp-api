package io.github.dft.amazon;

import com.amazonaws.http.HttpMethodName;
import io.github.dft.amazon.constantcode.ConstantCodes;
import io.github.dft.amazon.model.AccessCredentials;
import io.github.dft.amazon.model.fulfillmentoutbound.v20200701.GetFulfillmentOrderResponse;
import io.github.dft.amazon.model.fulfillmentoutbound.v20200701.ListAllFulfillmentOrdersResponse;
import io.github.dft.amazon.model.handler.JsonBodyHandler;
import lombok.SneakyThrows;
import org.apache.http.client.utils.URIBuilder;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class AmazonSPFulfillmentOutBound extends AmazonSellingPartnerSdk {

    public AmazonSPFulfillmentOutBound(AccessCredentials accessCredentials) {
        super(accessCredentials);
    }

    @SneakyThrows
    public ListAllFulfillmentOrdersResponse listAllFulfillmentOrders(HashMap<String, String> params) {
        final var signRequest = signRequest(ConstantCodes.FULFILLMENT_OUTBOUND_API_V202007, HttpMethodName.GET, params, null);

        URIBuilder uriBuilder = new URIBuilder(sellingRegionEndpoint + ConstantCodes.FULFILLMENT_OUTBOUND_API_V202007);
        addParameters(uriBuilder, params);

        URI uri = uriBuilder.build();
        HttpRequest request = HttpRequest.newBuilder(uri)
            .header(ConstantCodes.HTTP_HEADER_ACCEPTS, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
            .header(ConstantCodes.HTTP_HEADER_CONTENT_TYPE, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
            .header(ConstantCodes.HTTP_HEADER_X_AMZ_ACCESS_TOKEN, accessCredentials.getAccessToken())
            .header(ConstantCodes.HTTP_HEADER_AUTHORIZATION, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_AUTHORIZATION))
            .header(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN))
            .header(ConstantCodes.X_AMZ_DATE, signRequest.getHeaders().get(ConstantCodes.X_AMZ_DATE))
            .GET()
            .build();

        HttpResponse.BodyHandler<ListAllFulfillmentOrdersResponse> handler = new JsonBodyHandler<>(ListAllFulfillmentOrdersResponse.class);

        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public GetFulfillmentOrderResponse getFulfillmentOrder(String sellerFulfillmentOrderId) {
        String fulfillmentOutboundURL = ConstantCodes.FULFILLMENT_OUTBOUND_API_V202007 + "/" + sellerFulfillmentOrderId;
        final var signRequest = signRequest(fulfillmentOutboundURL, HttpMethodName.GET, null, null);

        URIBuilder uriBuilder = new URIBuilder(sellingRegionEndpoint + fulfillmentOutboundURL);

        URI uri = uriBuilder.build();
        HttpRequest request = HttpRequest.newBuilder(uri)
            .header(ConstantCodes.HTTP_HEADER_ACCEPTS, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
            .header(ConstantCodes.HTTP_HEADER_CONTENT_TYPE, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
            .header(ConstantCodes.HTTP_HEADER_X_AMZ_ACCESS_TOKEN, accessCredentials.getAccessToken())
            .header(ConstantCodes.HTTP_HEADER_AUTHORIZATION, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_AUTHORIZATION))
            .header(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN))
            .header(ConstantCodes.X_AMZ_DATE, signRequest.getHeaders().get(ConstantCodes.X_AMZ_DATE))
            .GET()
            .build();

        HttpResponse.BodyHandler<GetFulfillmentOrderResponse> handler = new JsonBodyHandler<>(GetFulfillmentOrderResponse.class);

        return getRequestWrapped(request, handler);
    }

    protected void addParameters(URIBuilder uriBuilder, HashMap<String, String> params) {
        if (params == null || params.isEmpty()) return;
        for (String key : params.keySet()) {
            uriBuilder.addParameter(key, params.get(key));
        }
    }
}