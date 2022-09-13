package io.github.dft.amazon;

import com.amazonaws.http.HttpMethodName;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.dft.amazon.constantcode.ConstantCodes;
import io.github.dft.amazon.model.AccessCredentials;
import io.github.dft.amazon.model.handler.JsonBodyHandler;
import io.github.dft.amazon.model.reports.v202106.CreateReportResponse;
import io.github.dft.amazon.model.reports.v202106.CreateReportSpecification;
import lombok.SneakyThrows;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

public class AmazonSPReports extends AmazonSellingPartnerSdk {

    @SneakyThrows
    public AmazonSPReports(AccessCredentials accessCredentials) {
        super(accessCredentials);
    }

    @SneakyThrows
    public CreateReportResponse createReport(CreateReportSpecification body) {
        String requestBody = getString(body);

        final var signRequest = signRequest(ConstantCodes.CREATE_REPORT_API_V202106, HttpMethodName.POST, null, requestBody);

        HttpRequest request = HttpRequest.newBuilder(new URI(sellingRegionEndpoint + ConstantCodes.CREATE_REPORT_API_V202106))
                .header(ConstantCodes.HTTP_HEADER_ACCEPTS, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(ConstantCodes.HTTP_HEADER_CONTENT_TYPE, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(ConstantCodes.HTTP_HEADER_X_AMZ_ACCESS_TOKEN, accessCredentials.getAccessToken())
                .header(ConstantCodes.HTTP_HEADER_AUTHORIZATION, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_AUTHORIZATION))
                .header(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN))
                .header(ConstantCodes.X_AMZ_DATE, signRequest.getHeaders().get(ConstantCodes.X_AMZ_DATE))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        return HttpClient.newHttpClient()
                .send(request, new JsonBodyHandler<>(CreateReportResponse.class))
                .body();
    }

}
