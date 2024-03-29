package io.github.dft.amazon;

import com.amazonaws.http.HttpMethodName;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.dft.amazon.constantcode.ConstantCodes;
import io.github.dft.amazon.model.AmazonCredentials;
import io.github.dft.amazon.model.handler.JsonBodyHandler;
import io.github.dft.amazon.model.productfees.GetMyFeesEstimateResponse;
import io.github.dft.amazon.model.productfees.GetMyFeesEstimateResults;
import io.github.dft.amazon.model.productfees.GetMyFeesEstimatesRequest;
import io.github.dft.amazon.model.productfees.GetMyFeesEstimatesRequestBody;
import io.github.dft.amazon.model.productfees.GetMyFeesEstimatesResponse;
import io.github.dft.amazon.model.reports.v202106.ErrorList;
import lombok.SneakyThrows;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AmazonSPProductFees extends AmazonSellingPartnerSdk {

    private final ObjectMapper objectMapper;

    public AmazonSPProductFees(AmazonCredentials amazonCredentials) {
        super(amazonCredentials);
        objectMapper = new ObjectMapper();
    }

    @SneakyThrows
    public GetMyFeesEstimateResponse getMyFeesEstimateForSKU(String sku, GetMyFeesEstimatesRequest body) {

        String requestBody = getString(body);
        String finalPath = String.format(ConstantCodes.PRODUCTFEES_API_FEESESTIMATE_FOR_SKU_V0, sku);
        final var signRequest = signRequest(finalPath, HttpMethodName.POST, null, requestBody);

        HttpRequest request = HttpRequest.newBuilder(new URI(sellingRegionEndpoint + finalPath))
                .header(ConstantCodes.HTTP_HEADER_ACCEPTS, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(ConstantCodes.HTTP_HEADER_CONTENT_TYPE, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(ConstantCodes.HTTP_HEADER_X_AMZ_ACCESS_TOKEN, amazonCredentials.getAccessToken())
                .header(ConstantCodes.HTTP_HEADER_AUTHORIZATION, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_AUTHORIZATION))
                .header(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN))
                .header(ConstantCodes.X_AMZ_DATE, signRequest.getHeaders().get(ConstantCodes.X_AMZ_DATE))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse.BodyHandler<GetMyFeesEstimateResponse> handler = new JsonBodyHandler<>(GetMyFeesEstimateResponse.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public GetMyFeesEstimatesResponse getMyFeesEstimates(GetMyFeesEstimatesRequestBody body) {

        String requestBody = getString(body);
        final var signRequest = signRequest(ConstantCodes.PRODUCTFEES_API_MY_FEESESTIMATE_V0, HttpMethodName.POST, null, requestBody);

        HttpRequest request = HttpRequest.newBuilder(new URI(sellingRegionEndpoint + ConstantCodes.PRODUCTFEES_API_MY_FEESESTIMATE_V0))
                .header(ConstantCodes.HTTP_HEADER_ACCEPTS, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(ConstantCodes.HTTP_HEADER_CONTENT_TYPE, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(ConstantCodes.HTTP_HEADER_X_AMZ_ACCESS_TOKEN, amazonCredentials.getAccessToken())
                .header(ConstantCodes.HTTP_HEADER_AUTHORIZATION, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_AUTHORIZATION))
                .header(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN))
                .header(ConstantCodes.X_AMZ_DATE, signRequest.getHeaders().get(ConstantCodes.X_AMZ_DATE))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse.BodyHandler<Object> handler = new JsonBodyHandler<>(Object.class);

        final var response = getRequestWrapped(request, handler);
        if (!response.getClass().getSimpleName().equalsIgnoreCase("ArrayList")) {
            final var getMyFeesEstimateResults = new GetMyFeesEstimateResults();
            final var getMyFeesEstimatesResponse = new GetMyFeesEstimatesResponse();
            getMyFeesEstimateResults.setErrors(objectMapper.convertValue(response, ErrorList.class));
            getMyFeesEstimatesResponse.add(getMyFeesEstimateResults);
            return getMyFeesEstimatesResponse;
        }
        return objectMapper.convertValue(response, GetMyFeesEstimatesResponse.class);
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
                .header(ConstantCodes.HTTP_HEADER_X_AMZ_ACCESS_TOKEN, amazonCredentials.getAccessToken())
                .header(ConstantCodes.HTTP_HEADER_AUTHORIZATION, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_AUTHORIZATION))
                .header(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN))
                .header(ConstantCodes.X_AMZ_DATE, signRequest.getHeaders().get(ConstantCodes.X_AMZ_DATE))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse.BodyHandler<GetMyFeesEstimateResponse> handler = new JsonBodyHandler<>(GetMyFeesEstimateResponse.class);
        return getRequestWrapped(request, handler);
    }
}