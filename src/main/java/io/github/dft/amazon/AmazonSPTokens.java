package io.github.dft.amazon;

import com.amazonaws.http.HttpMethodName;
import io.github.dft.amazon.constantcode.ConstantCodes;
import io.github.dft.amazon.model.AccessCredentials;
import io.github.dft.amazon.model.handler.JsonBodyHandler;
import io.github.dft.amazon.model.tokens.v202103.CreateRestrictedDataTokenRequest;
import io.github.dft.amazon.model.tokens.v202103.CreateRestrictedDataTokenResponse;
import lombok.SneakyThrows;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AmazonSPTokens extends AmazonSellingPartnerSdk {

    public AmazonSPTokens(AccessCredentials accessCredentials) {
        super(accessCredentials);
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
        return getRequestWrapped(request, handler);
    }

}