package io.github.dft.amazon;

import com.amazonaws.DefaultRequest;
import com.amazonaws.auth.AWS4Signer;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.STSAssumeRoleSessionCredentialsProvider;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClientBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.dft.amazon.constantcode.ConstantCodes;
import io.github.dft.amazon.model.AccessCredentials;
import io.github.dft.amazon.model.auth.AccessTokenResponse;
import io.github.dft.amazon.model.handler.JsonBodyHandler;
import io.github.dft.amazon.model.reports.v202106.CreateReportResponse;
import io.github.dft.amazon.model.reports.v202106.CreateReportSpecification;
import io.github.dft.amazon.model.sellersapi.v1.GetMarketplaceParticipationsResponse;
import lombok.SneakyThrows;

import java.io.ByteArrayInputStream;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

public class AmazonSellingPartnerSdk {

    private final AccessCredentials accessCredentials;
    private String sellingRegionEndpoint;

    @SneakyThrows
    public AmazonSellingPartnerSdk(AccessCredentials accessCredentials) {
        this.accessCredentials = accessCredentials;
        if (ConstantCodes.AWS_REGION_EU_WEST_1.equalsIgnoreCase(accessCredentials.getRegion())) {
            sellingRegionEndpoint = "https://sellingpartnerapi-eu.amazon.com";

        } else if (ConstantCodes.AWS_REGION_US_EAST_1.equalsIgnoreCase(accessCredentials.getRegion())) {
            sellingRegionEndpoint = "https://sellingpartnerapi-na.amazon.com";

        } else if (ConstantCodes.AWS_REGION_US_WEST_1.equalsIgnoreCase(accessCredentials.getRegion())) {
            sellingRegionEndpoint = "https://sellingpartnerapi-fe.amazon.com";
        }

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

    @SneakyThrows
    public CreateReportResponse createReport(CreateReportSpecification body) {
        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(body);

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

    private DefaultRequest<Object> signRequest(String resourcePath, HttpMethodName httpMethodName, Map<String, String> params) {
        return signRequest(resourcePath, httpMethodName, params, null);
    }

    private DefaultRequest<Object> signRequest(String resourcePath, HttpMethodName httpMethodName, Map<String, String> params, String payload) {

        var basicAWSCredentials = new BasicAWSCredentials(accessCredentials.getAccessKeyId(), accessCredentials.getSecretAccessKey());
        var credentialsProvider =  new STSAssumeRoleSessionCredentialsProvider.Builder(accessCredentials.getRoleArn(),
                "rakesh")
                .withStsClient(AWSSecurityTokenServiceClientBuilder.standard().withRegion(accessCredentials.getRegion()).
                        withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials)).build()).build();


        AWS4Signer signer = new AWS4Signer();
        signer.setRegionName(accessCredentials.getRegion());
        signer.setServiceName(ConstantCodes.SERVICE_NAME);

        var request = new DefaultRequest<>(ConstantCodes.SERVICE_NAME);
        request.setEndpoint(URI.create(sellingRegionEndpoint));
        if (!resourcePath.isBlank()) {
            request.setResourcePath(resourcePath);
        }
        if (params != null) {
            params.forEach(request::addParameter);
        }

        request.setHttpMethod(httpMethodName);
        if (null != payload) {
            request.setContent(new ByteArrayInputStream(payload.getBytes(UTF_8)));
        }

        signer.sign(request, credentialsProvider.getCredentials());
        return request;
    }

    @SneakyThrows
    public void refreshAccessToken() {

        Map<Object, Object> data = new HashMap<>();
        data.put(ConstantCodes.HTTP_OAUTH_PARAMETER_GRANT_TYPE, ConstantCodes.REFRESH_TOKEN);
        data.put(ConstantCodes.HTTP_OAUTH_PARAMETER_REFRESH_TOKEN, accessCredentials.getRefreshToken());
        data.put(ConstantCodes.HTTP_OAUTH_PARAMETER_CLIENT_ID, accessCredentials.getClientIdentifier());
        data.put(ConstantCodes.HTTP_OAUTH_PARAMETER_CLIENT_SECRET, accessCredentials.getClientSecret());

        HttpRequest request = HttpRequest.newBuilder(new URI(ConstantCodes.LWA_AUTHORIZATION_SERVER))
                .header(ConstantCodes.HTTP_HEADER_CONTENT_TYPE, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_FORM_URL_ENCODED)
                .header(ConstantCodes.HTTP_HEADER_ACCEPTS, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
                .POST(ofFormData(data))
                .build();

        AccessTokenResponse accessTokenResponse = HttpClient.newHttpClient()
                .send(request, new JsonBodyHandler<>(AccessTokenResponse.class))
                .body();

        accessCredentials.setAccessToken(accessTokenResponse.getAccessToken());
        accessCredentials.setRefreshToken(accessTokenResponse.getRefreshToken());
        accessCredentials.setExpiresInTime(accessTokenResponse.getExpiresIn());
        accessCredentials.setTokenType(accessTokenResponse.getTokenType());
    }

    public HttpRequest.BodyPublisher ofFormData(Map<Object, Object> data) {
        var builder = new StringBuilder();
        for (Map.Entry<Object, Object> entry : data.entrySet()) {
            if (builder.length() > 0) {
                builder.append("&");
            }
            builder.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
        }
        return HttpRequest.BodyPublishers.ofString(builder.toString());
    }
}
