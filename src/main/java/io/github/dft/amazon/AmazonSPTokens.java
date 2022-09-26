package io.github.dft.amazon;

import com.amazonaws.http.HttpMethodName;
import io.github.dft.amazon.constantcode.ConstantCodes;
import io.github.dft.amazon.constantcode.RateLimitConstants;
import io.github.dft.amazon.model.AccessCredentials;
import io.github.dft.amazon.model.handler.JsonBodyHandler;
import io.github.dft.amazon.model.tokens.v202103.CreateRestrictedDataTokenRequest;
import io.github.dft.amazon.model.tokens.v202103.CreateRestrictedDataTokenResponse;
import lombok.SneakyThrows;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

import static io.github.dft.amazon.constantcode.ConstantCodes.MAX_ATTEMPTS;
import static io.github.dft.amazon.constantcode.ConstantCodes.TIME_OUT_DURATION;

public class AmazonSPTokens extends AmazonSellingPartnerSdk {

    private final HttpClient client;
    private final RateLimitConstants rateLimitConstants;

    public AmazonSPTokens(AccessCredentials accessCredentials) {
        super(accessCredentials);
        this.rateLimitConstants = new RateLimitConstants();
        client = HttpClient.newHttpClient();
    }

    @SneakyThrows
    public CreateRestrictedDataTokenResponse createRestrictedDataToken(CreateRestrictedDataTokenRequest body) {
        String requestBody = getString(body);

        final var signRequest = signRequest(ConstantCodes.TOKENS_API_V202103, HttpMethodName.POST, null,requestBody);

        HttpRequest request = HttpRequest.newBuilder(new URI(sellingRegionEndpoint + ConstantCodes.TOKENS_API_V202103))
            .header(ConstantCodes.HTTP_HEADER_ACCEPTS, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
            .header(ConstantCodes.HTTP_HEADER_CONTENT_TYPE, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
            .header(ConstantCodes.HTTP_HEADER_X_AMZ_ACCESS_TOKEN, accessCredentials.getAccessToken())
            .header(ConstantCodes.HTTP_HEADER_AUTHORIZATION, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_AUTHORIZATION))
            .header(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN))
            .header(ConstantCodes.X_AMZ_DATE, signRequest.getHeaders().get(ConstantCodes.X_AMZ_DATE))
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .build();

        HttpResponse.BodyHandler<CreateRestrictedDataTokenResponse> handler = new JsonBodyHandler<>(CreateRestrictedDataTokenResponse.class);
        rateLimitConstants.CREATE_RESTRICTED_DATA_TOKEN_API_CALL = setRateLimit(
            rateLimitConstants.CREATE_RESTRICTED_DATA_TOKEN_API_CALL,
            rateLimitConstants.CREATE_RESTRICTED_DATA_TOKEN_LIMIT_REFRESH,
            rateLimitConstants.CREATE_RESTRICTED_DATA_TOKEN_RATE_LIMIT
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