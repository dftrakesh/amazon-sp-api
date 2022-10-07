package io.github.dft.amazon;

import com.amazonaws.http.HttpMethodName;
import io.github.dft.amazon.constantcode.ConstantCodes;
import io.github.dft.amazon.constantcode.RateLimitConstants;
import io.github.dft.amazon.model.AccessCredentials;
import io.github.dft.amazon.model.feeds.v20210630.GetFeedsResponse;
import io.github.dft.amazon.model.handler.JsonBodyHandler;
import io.github.dft.amazon.model.sellersapi.v1.GetMarketplaceParticipationsResponse;
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

public class AmazonSPFeeds extends AmazonSellingPartnerSdk {


    @SneakyThrows
    public AmazonSPFeeds(AccessCredentials accessCredentials) {
        super(accessCredentials);
    }

    @SneakyThrows
    public GetFeedsResponse getFeeds(HashMap<String, String> params) {

        final var signRequest = signRequest(ConstantCodes.FEEDS_API_V20160630, HttpMethodName.GET, params, null);

        URIBuilder uriBuilder = new URIBuilder(sellingRegionEndpoint + ConstantCodes.FEEDS_API_V20160630);
        addParameters(uriBuilder, params);
        URI uri = uriBuilder.build();

        HttpRequest request = HttpRequest.newBuilder(uri)
            .header(ConstantCodes.HTTP_HEADER_ACCEPTS, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
            .header(ConstantCodes.HTTP_HEADER_CONTENT_TYPE, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
            .header(ConstantCodes.HTTP_HEADER_X_AMZ_ACCESS_TOKEN, accessCredentials.getAccessToken())
            .header(ConstantCodes.HTTP_HEADER_AUTHORIZATION, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_AUTHORIZATION))
            .header(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN))
            .header(ConstantCodes.X_AMZ_DATE, signRequest.getHeaders().get(ConstantCodes.X_AMZ_DATE))
            .build();

        HttpResponse.BodyHandler<GetFeedsResponse> handler = new JsonBodyHandler<>(GetFeedsResponse.class);

        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public GetFeedsResponse createFeedDocument(HashMap<String, String> params) {

        final var signRequest = signRequest(ConstantCodes.FEEDS_API_V20160630, HttpMethodName.GET, params, null);

        URIBuilder uriBuilder = new URIBuilder(sellingRegionEndpoint + ConstantCodes.FEEDS_API_V20160630);
        addParameters(uriBuilder, params);
        URI uri = uriBuilder.build();

        HttpRequest request = HttpRequest.newBuilder(uri)
                .header(ConstantCodes.HTTP_HEADER_ACCEPTS, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(ConstantCodes.HTTP_HEADER_CONTENT_TYPE, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(ConstantCodes.HTTP_HEADER_X_AMZ_ACCESS_TOKEN, accessCredentials.getAccessToken())
                .header(ConstantCodes.HTTP_HEADER_AUTHORIZATION, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_AUTHORIZATION))
                .header(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN))
                .header(ConstantCodes.X_AMZ_DATE, signRequest.getHeaders().get(ConstantCodes.X_AMZ_DATE))
                .build();

        HttpResponse.BodyHandler<GetFeedsResponse> handler = new JsonBodyHandler<>(GetFeedsResponse.class);

        return getRequestWrapped(request, handler);
    }
}
