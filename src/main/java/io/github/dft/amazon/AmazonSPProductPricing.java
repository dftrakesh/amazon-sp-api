package io.github.dft.amazon;

import com.amazonaws.http.HttpMethodName;
import io.github.dft.amazon.model.AmazonCredentials;
import io.github.dft.amazon.model.handler.JsonBodyHandler;
import io.github.dft.amazon.model.productprice.GetCompetitivePriceResponse;
import io.github.dft.amazon.model.productprice.listingoffer.GetOffersResponse;
import lombok.SneakyThrows;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

import static io.github.dft.amazon.constantcode.ConstantCodes.HTTP_HEADER_ACCEPTS;
import static io.github.dft.amazon.constantcode.ConstantCodes.HTTP_HEADER_AUTHORIZATION;
import static io.github.dft.amazon.constantcode.ConstantCodes.HTTP_HEADER_CONTENT_TYPE;
import static io.github.dft.amazon.constantcode.ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON;
import static io.github.dft.amazon.constantcode.ConstantCodes.HTTP_HEADER_X_AMZ_ACCESS_TOKEN;
import static io.github.dft.amazon.constantcode.ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN;
import static io.github.dft.amazon.constantcode.ConstantCodes.OFFERS_ENDPOINT;
import static io.github.dft.amazon.constantcode.ConstantCodes.PRODUCTS_PRICING_COMPETITIVE_PRICE_API_V0;
import static io.github.dft.amazon.constantcode.ConstantCodes.PRODUCTS_PRICING_V0;
import static io.github.dft.amazon.constantcode.ConstantCodes.X_AMZ_DATE;

public class AmazonSPProductPricing extends AmazonSellingPartnerSdk {

    public AmazonSPProductPricing(AmazonCredentials amazonCredentials) {
        super(amazonCredentials);
    }

    @SneakyThrows
    public GetCompetitivePriceResponse getCompetitivePricing(HashMap<String, String> params) {
        final var signRequest = signRequest(PRODUCTS_PRICING_COMPETITIVE_PRICE_API_V0, HttpMethodName.GET, params, null);

        URIBuilder uriBuilder = new URIBuilder(sellingRegionEndpoint + PRODUCTS_PRICING_COMPETITIVE_PRICE_API_V0);
        addParameters(uriBuilder, params);
        URI uri = uriBuilder.build();

        HttpRequest request = HttpRequest.newBuilder(uri)
            .header(HTTP_HEADER_ACCEPTS, HTTP_HEADER_VALUE_APPLICATION_JSON)
            .header(HTTP_HEADER_CONTENT_TYPE, HTTP_HEADER_VALUE_APPLICATION_JSON)
            .header(HTTP_HEADER_X_AMZ_ACCESS_TOKEN, amazonCredentials.getAccessToken())
            .header(HTTP_HEADER_AUTHORIZATION, signRequest.getHeaders().get(HTTP_HEADER_AUTHORIZATION))
            .header(HTTP_HEADER_X_AMZ_SECURITY_TOKEN, signRequest.getHeaders().get(HTTP_HEADER_X_AMZ_SECURITY_TOKEN))
            .header(X_AMZ_DATE, signRequest.getHeaders().get(X_AMZ_DATE))
            .GET()
            .build();

        HttpResponse.BodyHandler<GetCompetitivePriceResponse> handler = new JsonBodyHandler<>(GetCompetitivePriceResponse.class);
        return getRequestWrapped(request, handler);
    }

    public GetOffersResponse getListingOffers(String sellerSku, HashMap<String, String> params) {
        URI uri = URI.create(sellingRegionEndpoint + PRODUCTS_PRICING_V0 + sellerSku + OFFERS_ENDPOINT);
        uri = addParameters(uri, params);

        final var signRequest = signRequest(String.valueOf(uri), HttpMethodName.GET, params, null);

        HttpRequest request = HttpRequest.newBuilder(uri)
                                         .header(HTTP_HEADER_ACCEPTS, HTTP_HEADER_VALUE_APPLICATION_JSON)
                                         .header(HTTP_HEADER_CONTENT_TYPE, HTTP_HEADER_VALUE_APPLICATION_JSON)
                                         .header(HTTP_HEADER_X_AMZ_ACCESS_TOKEN, amazonCredentials.getAccessToken())
                                         .header(HTTP_HEADER_AUTHORIZATION, signRequest.getHeaders().get(HTTP_HEADER_AUTHORIZATION))
                                         .header(HTTP_HEADER_X_AMZ_SECURITY_TOKEN, signRequest.getHeaders().get(HTTP_HEADER_X_AMZ_SECURITY_TOKEN))
                                         .header(X_AMZ_DATE, signRequest.getHeaders().get(X_AMZ_DATE))
                                         .GET()
                                         .build();

        HttpResponse.BodyHandler<GetOffersResponse> handler = new JsonBodyHandler<>(GetOffersResponse.class);
        return getRequestWrapped(request, handler);
    }
}