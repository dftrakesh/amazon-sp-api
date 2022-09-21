package io.github.dft.amazon;

import com.amazonaws.http.HttpMethodName;
import io.github.dft.amazon.constantcode.ConstantCodes;
import io.github.dft.amazon.constantcode.RateLimitConstants;
import io.github.dft.amazon.model.AccessCredentials;
import io.github.dft.amazon.model.handler.JsonBodyHandler;
import io.github.dft.amazon.model.sellersapi.v1.GetMarketplaceParticipationsResponse;
import lombok.SneakyThrows;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

import static io.github.dft.amazon.constantcode.ConstantCodes.MAX_ATTEMPTS;
import static io.github.dft.amazon.constantcode.ConstantCodes.TIME_OUT_DURATION;

public class AmazonSPSellers extends AmazonSellingPartnerSdk {

    private final HttpClient client;
    private final RateLimitConstants rateLimitConstants;

    @SneakyThrows
    public AmazonSPSellers(AccessCredentials accessCredentials) {
        super(accessCredentials);
        this.rateLimitConstants = new RateLimitConstants();
        client = HttpClient.newHttpClient();
    }

    @SneakyThrows
    public GetMarketplaceParticipationsResponse getMarketplaceParticipations() {

        final var signRequest = signRequest(ConstantCodes.SELLERS_API_V1, HttpMethodName.GET, null);

        HttpRequest request = HttpRequest.newBuilder(new URI(sellingRegionEndpoint + ConstantCodes.SELLERS_API_V1))
            .header(ConstantCodes.HTTP_HEADER_ACCEPTS, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
            .header(ConstantCodes.HTTP_HEADER_CONTENT_TYPE, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
            .header(ConstantCodes.HTTP_HEADER_X_AMZ_ACCESS_TOKEN, accessCredentials.getAccessToken())
            .header(ConstantCodes.HTTP_HEADER_AUTHORIZATION, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_AUTHORIZATION))
            .header(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN))
            .header(ConstantCodes.X_AMZ_DATE, signRequest.getHeaders().get(ConstantCodes.X_AMZ_DATE))
            .build();

        HttpResponse.BodyHandler<GetMarketplaceParticipationsResponse> handler = new JsonBodyHandler<>(GetMarketplaceParticipationsResponse.class);
        rateLimitConstants.GET_MARKETPLACE_PARTICIPATION_API_CALL = setRateLimit(
            rateLimitConstants.GET_MARKETPLACE_PARTICIPATION_API_CALL,
            rateLimitConstants.GET_MARKETPLACE_PARTICIPATION_LIMIT_REFRESH,
            rateLimitConstants.GET_MARKETPLACE_PARTICIPATION_RATE_LIMIT
        );

        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public <T> T getRequestWrapped(HttpRequest request, HttpResponse.BodyHandler<T> handler) {

        return client
            .sendAsync(request, handler)
            .thenComposeAsync(response -> tryResend(client, request, handler, response, 1))
            .get()
            .body();
    }

    @SneakyThrows
    public <T> CompletableFuture<HttpResponse<T>> tryResend(HttpClient client,
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

    @SneakyThrows
    public int setRateLimit(int apiCall, int limitRefreshPeriod, int rateLimit) {
        if (apiCall <= 0) {
            Thread.sleep(limitRefreshPeriod);
            apiCall = rateLimit;
        }
        return apiCall - 1;
    }
}
