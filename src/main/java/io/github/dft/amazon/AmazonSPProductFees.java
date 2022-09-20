package io.github.dft.amazon;

import com.amazonaws.http.HttpMethodName;
import io.github.dft.amazon.constantcode.ConstantCodes;
import io.github.dft.amazon.model.AccessCredentials;
import io.github.dft.amazon.model.handler.JsonBodyHandler;
import io.github.dft.amazon.model.productfees.GetMyFeesEstimateResponse;
import io.github.dft.amazon.model.productfees.GetMyFeesEstimatesRequest;
import io.github.dft.amazon.model.tokens.v202103.CreateRestrictedDataTokenRequest;
import io.github.dft.amazon.model.tokens.v202103.CreateRestrictedDataTokenResponse;
import lombok.SneakyThrows;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AmazonSPProductFees extends AmazonSellingPartnerSdk {
    public AmazonSPProductFees(AccessCredentials accessCredentials) {
        super(accessCredentials);
    }

    @SneakyThrows
    public GetMyFeesEstimateResponse getMyFeesEstimateForSKU(String sku, GetMyFeesEstimatesRequest body) {

        String requestBody = getString(body);
        String finalPath = String.format(ConstantCodes.PRODUCTFEES_API_FEESESTIMATE_FOR_SKU_V0, sku);
        final var signRequest = signRequest(finalPath, HttpMethodName.POST, null, requestBody);

        HttpRequest request = HttpRequest.newBuilder(new URI(sellingRegionEndpoint + finalPath))
                .header(ConstantCodes.HTTP_HEADER_ACCEPTS, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(ConstantCodes.HTTP_HEADER_CONTENT_TYPE, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(ConstantCodes.HTTP_HEADER_X_AMZ_ACCESS_TOKEN, accessCredentials.getAccessToken())
                .header(ConstantCodes.HTTP_HEADER_AUTHORIZATION, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_AUTHORIZATION))
                .header(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN))
                .header(ConstantCodes.X_AMZ_DATE, signRequest.getHeaders().get(ConstantCodes.X_AMZ_DATE))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        return HttpClient.newHttpClient()
                .send(request, new JsonBodyHandler<>(GetMyFeesEstimateResponse.class))
                .body();
    }

    @SneakyThrows
    public GetMyFeesEstimateResponse getMyFeesEstimates(GetMyFeesEstimatesRequest body) {

        String requestBody = getString(body);
        final var signRequest = signRequest(ConstantCodes.PRODUCTFEES_API_MY_FEESESTIMATE_V0, HttpMethodName.POST, null, requestBody);

        HttpRequest request = HttpRequest.newBuilder(new URI(sellingRegionEndpoint + ConstantCodes.PRODUCTFEES_API_MY_FEESESTIMATE_V0))
                .header(ConstantCodes.HTTP_HEADER_ACCEPTS, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(ConstantCodes.HTTP_HEADER_CONTENT_TYPE, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(ConstantCodes.HTTP_HEADER_X_AMZ_ACCESS_TOKEN, accessCredentials.getAccessToken())
                .header(ConstantCodes.HTTP_HEADER_AUTHORIZATION, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_AUTHORIZATION))
                .header(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN))
                .header(ConstantCodes.X_AMZ_DATE, signRequest.getHeaders().get(ConstantCodes.X_AMZ_DATE))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        return HttpClient.newHttpClient()
                .send(request, new JsonBodyHandler<>(GetMyFeesEstimateResponse.class))
                .body();
    }


    @SneakyThrows
    public GetMyFeesEstimateResponse getMyFeesEstimateForASIN(String asin, GetMyFeesEstimatesRequest body) {

        refreshAccessToken();
        String requestBody = getString(body);
        String finalPath = String.format(ConstantCodes.PRODUCTFEES_API_FEESESTIMATE_FOR_ASIN_V0, asin);
        final var signRequest = signRequest(finalPath, HttpMethodName.POST, null, requestBody);

        HttpRequest request = HttpRequest.newBuilder(new URI(sellingRegionEndpoint + finalPath))
                .header(ConstantCodes.HTTP_HEADER_ACCEPTS, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(ConstantCodes.HTTP_HEADER_CONTENT_TYPE, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(ConstantCodes.HTTP_HEADER_X_AMZ_ACCESS_TOKEN, accessCredentials.getAccessToken())
                .header(ConstantCodes.HTTP_HEADER_AUTHORIZATION, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_AUTHORIZATION))
                .header(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN))
                .header(ConstantCodes.X_AMZ_DATE, signRequest.getHeaders().get(ConstantCodes.X_AMZ_DATE))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        return HttpClient.newHttpClient()
                .send(request, new JsonBodyHandler<>(GetMyFeesEstimateResponse.class))
                .body();
    }
}
