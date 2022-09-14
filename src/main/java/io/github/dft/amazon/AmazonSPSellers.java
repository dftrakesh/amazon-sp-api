package io.github.dft.amazon;

import com.amazonaws.http.HttpMethodName;
import io.github.dft.amazon.constantcode.ConstantCodes;
import io.github.dft.amazon.model.AccessCredentials;
import io.github.dft.amazon.model.handler.JsonBodyHandler;
import io.github.dft.amazon.model.sellersapi.v1.GetMarketplaceParticipationsResponse;
import lombok.SneakyThrows;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

public class AmazonSPSellers extends AmazonSellingPartnerSdk {

    @SneakyThrows
    public AmazonSPSellers(AccessCredentials accessCredentials) {
        super(accessCredentials);
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

        return HttpClient.newHttpClient()
                .send(request, new JsonBodyHandler<>(GetMarketplaceParticipationsResponse.class))
                .body();
    }

}
