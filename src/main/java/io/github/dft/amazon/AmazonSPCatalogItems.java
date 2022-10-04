package io.github.dft.amazon;

import com.amazonaws.http.HttpMethodName;
import com.google.common.util.concurrent.RateLimiter;
import io.github.dft.amazon.constantcode.ConstantCodes;
import io.github.dft.amazon.model.AccessCredentials;
import io.github.dft.amazon.model.catalogitems.v202204.Item;
import io.github.dft.amazon.model.catalogitems.v202204.ItemSearchResults;
import io.github.dft.amazon.model.handler.JsonBodyHandler;
import lombok.SneakyThrows;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

import static io.github.dft.amazon.constantcode.ConstantCodes.MAX_ATTEMPTS;
import static io.github.dft.amazon.constantcode.ConstantCodes.TIME_OUT_DURATION;

public class AmazonSPCatalogItems extends AmazonSellingPartnerSdk {

    private final HttpClient client;
    private final RateLimiter rateLimiter;

    public AmazonSPCatalogItems(AccessCredentials accessCredentials) {
        super(accessCredentials);
        this.rateLimiter = RateLimiter.create(2);
        client = HttpClient.newHttpClient();
    }

    @SneakyThrows
    public Item getCatalogItem(String asin, HashMap<String, String> params) {

        final var signRequest = signRequest(ConstantCodes.CATALOG_ITEMS_API_V202204 + asin, HttpMethodName.GET, params);

        URIBuilder uriBuilder = new URIBuilder(sellingRegionEndpoint + ConstantCodes.CATALOG_ITEMS_API_V202204 + asin);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                uriBuilder.addParameter(key, params.get(key));
            }
        }

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

        HttpResponse.BodyHandler<Item> handler = new JsonBodyHandler<>(Item.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public ItemSearchResults searchCatalogItems(HashMap<String, String> params) {

        final var signRequest = signRequest(ConstantCodes.CATALOG_ITEMS_API_V202204, HttpMethodName.GET, params);

        URIBuilder uriBuilder = new URIBuilder(sellingRegionEndpoint + ConstantCodes.CATALOG_ITEMS_API_V202204);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                uriBuilder.addParameter(key, params.get(key));
            }
        }

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

        HttpResponse.BodyHandler<ItemSearchResults> handler = new JsonBodyHandler<>(ItemSearchResults.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    private <T> T getRequestWrapped(HttpRequest request, HttpResponse.BodyHandler<T> handler) {
        rateLimiter.acquire();
        return client
            .sendAsync(request, handler)
            .thenComposeAsync(response -> tryResend(client, request, handler, response, 1))
            .get()
            .body();
    }

    @SneakyThrows
    private <T> CompletableFuture<HttpResponse<T>> tryResend(HttpClient client,
                                                             HttpRequest request,
                                                             HttpResponse.BodyHandler<T> handler,
                                                             HttpResponse<T> resp, int count) {

        if (resp.statusCode() == 429 && count < MAX_ATTEMPTS) {
            Thread.sleep(TIME_OUT_DURATION);
            return client.sendAsync(request, handler)
                .thenComposeAsync(response -> tryResend(client, request, handler, response, count + 1));
        }
        return CompletableFuture.completedFuture(resp);
    }
}