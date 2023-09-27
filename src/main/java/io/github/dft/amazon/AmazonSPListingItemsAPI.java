package io.github.dft.amazon;

import com.amazonaws.http.HttpMethodName;
import io.github.dft.amazon.model.AmazonCredentials;
import io.github.dft.amazon.model.handler.JsonBodyHandler;
import io.github.dft.amazon.model.listing.v20210801.ListingItemsWrapper;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

import static io.github.dft.amazon.constantcode.ConstantCodes.FORWARD_SLASH;
import static io.github.dft.amazon.constantcode.ConstantCodes.HTTP_HEADER_ACCEPTS;
import static io.github.dft.amazon.constantcode.ConstantCodes.HTTP_HEADER_AUTHORIZATION;
import static io.github.dft.amazon.constantcode.ConstantCodes.HTTP_HEADER_CONTENT_TYPE;
import static io.github.dft.amazon.constantcode.ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON;
import static io.github.dft.amazon.constantcode.ConstantCodes.HTTP_HEADER_X_AMZ_ACCESS_TOKEN;
import static io.github.dft.amazon.constantcode.ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN;
import static io.github.dft.amazon.constantcode.ConstantCodes.LISTING_ITEMS_V20210801;
import static io.github.dft.amazon.constantcode.ConstantCodes.X_AMZ_DATE;

public class AmazonSPListingItemsAPI extends AmazonSellingPartnerSdk {

    public AmazonSPListingItemsAPI(AmazonCredentials amazonCredentials) {
        super(amazonCredentials);
    }

    public ListingItemsWrapper getListingItemsBySellerIdAndSku(String sellerId, String sku, HashMap<String, String> params) {
        URI uri = URI.create(sellingRegionEndpoint + LISTING_ITEMS_V20210801 + FORWARD_SLASH + sellerId + FORWARD_SLASH + sku);
        uri = addParameters(uri, params);

        final var signRequest = signRequest(
                String.valueOf(uri),
                HttpMethodName.GET,
                null,
                null
        );

        HttpRequest request = HttpRequest.newBuilder(uri)
                .header(HTTP_HEADER_ACCEPTS, HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(HTTP_HEADER_CONTENT_TYPE, HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(HTTP_HEADER_X_AMZ_ACCESS_TOKEN, amazonCredentials.getAccessToken())
                .header(HTTP_HEADER_AUTHORIZATION, signRequest.getHeaders().get(HTTP_HEADER_AUTHORIZATION))
                .header(HTTP_HEADER_X_AMZ_SECURITY_TOKEN, signRequest.getHeaders().get(HTTP_HEADER_X_AMZ_SECURITY_TOKEN))
                .header(X_AMZ_DATE, signRequest.getHeaders().get(X_AMZ_DATE))
                .GET()
                .build();

        HttpResponse.BodyHandler<ListingItemsWrapper> handler = new JsonBodyHandler<>(ListingItemsWrapper.class);
        return getRequestWrapped(request, handler);
    }
}