package io.github.dft.amazon;

import com.amazonaws.http.HttpMethodName;
import io.github.dft.amazon.constantcode.ConstantCodes;
import io.github.dft.amazon.model.AccessCredentials;
import io.github.dft.amazon.model.catalogitems.v202204.Item;
import io.github.dft.amazon.model.catalogitems.v202204.ItemSearchResults;
import io.github.dft.amazon.model.handler.JsonBodyHandler;
import lombok.SneakyThrows;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class AmazonSPCatalogItems extends AmazonSellingPartnerSdk {

    public AmazonSPCatalogItems(AccessCredentials accessCredentials) {
        super(accessCredentials);
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
}