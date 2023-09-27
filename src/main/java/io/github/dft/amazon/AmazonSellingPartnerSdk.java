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
import io.github.dft.amazon.model.AmazonCredentials;
import io.github.dft.amazon.model.auth.AccessTokenResponse;
import io.github.dft.amazon.model.handler.JsonBodyHandler;
import lombok.SneakyThrows;
import org.apache.http.client.utils.URIBuilder;

import java.io.ByteArrayInputStream;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static io.github.dft.amazon.constantcode.ConstantCodes.MAX_ATTEMPTS;
import static io.github.dft.amazon.constantcode.ConstantCodes.TIME_OUT_DURATION;
import static java.nio.charset.StandardCharsets.UTF_8;

public class AmazonSellingPartnerSdk {

    protected AmazonCredentials amazonCredentials;
    protected String sellingRegionEndpoint;
    protected HttpClient client;
    private ObjectMapper objectMapper;

    public AmazonSellingPartnerSdk() {
        client = HttpClient.newHttpClient();
    }

    @SneakyThrows
    public AmazonSellingPartnerSdk(AmazonCredentials amazonCredentials) {
        this.amazonCredentials = amazonCredentials;
        if (ConstantCodes.AWS_REGION_EU_WEST_1.equalsIgnoreCase(amazonCredentials.getRegion())) {
            this.sellingRegionEndpoint = "https://sellingpartnerapi-eu.amazon.com";

        } else if (ConstantCodes.AWS_REGION_US_EAST_1.equalsIgnoreCase(amazonCredentials.getRegion())) {
            this.sellingRegionEndpoint = "https://sellingpartnerapi-na.amazon.com";

        } else if (ConstantCodes.AWS_REGION_US_WEST_1.equalsIgnoreCase(amazonCredentials.getRegion())) {
            this.sellingRegionEndpoint = "https://sellingpartnerapi-fe.amazon.com";
        } else {
            this.sellingRegionEndpoint = null;
        }
        this.objectMapper = new ObjectMapper();
        client = HttpClient.newHttpClient();
    }

    protected DefaultRequest<Object> signRequest(String resourcePath, HttpMethodName httpMethodName, Map<String, String> params) {
        return signRequest(resourcePath, httpMethodName, params, null);
    }

    protected DefaultRequest<Object> signRequest(String resourcePath, HttpMethodName httpMethodName, Map<String, String> params, String payload) {
        refreshAccessToken();
        var basicAWSCredentials = new BasicAWSCredentials(amazonCredentials.getAccessKeyId(), amazonCredentials.getSecretAccessKey());
        var credentialsProvider = new STSAssumeRoleSessionCredentialsProvider.Builder(amazonCredentials.getRoleArn(),
                "rakesh")
                .withStsClient(AWSSecurityTokenServiceClientBuilder.standard().withRegion(amazonCredentials.getRegion()).
                        withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials)).build()).build();


        AWS4Signer signer = new AWS4Signer();
        signer.setRegionName(amazonCredentials.getRegion());
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
    protected void refreshAccessToken() {
        if (amazonCredentials.getAccessToken() == null || amazonCredentials.getExpiresInTime() == null || ZonedDateTime.now(ZoneOffset.UTC).isAfter(amazonCredentials.getExpiresInTime())) {
            Map<Object, Object> data = new HashMap<>();
            data.put(ConstantCodes.HTTP_OAUTH_PARAMETER_GRANT_TYPE, ConstantCodes.REFRESH_TOKEN);
            data.put(ConstantCodes.HTTP_OAUTH_PARAMETER_REFRESH_TOKEN, amazonCredentials.getRefreshToken());
            data.put(ConstantCodes.HTTP_OAUTH_PARAMETER_CLIENT_ID, amazonCredentials.getClientIdentifier());
            data.put(ConstantCodes.HTTP_OAUTH_PARAMETER_CLIENT_SECRET, amazonCredentials.getClientSecret());

            HttpRequest request = HttpRequest.newBuilder(new URI(ConstantCodes.LWA_AUTHORIZATION_SERVER))
                    .header(ConstantCodes.HTTP_HEADER_CONTENT_TYPE, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_FORM_URL_ENCODED)
                    .header(ConstantCodes.HTTP_HEADER_ACCEPTS, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
                    .POST(ofFormData(data))
                    .build();

            AccessTokenResponse accessTokenResponse = HttpClient.newHttpClient()
                    .send(request, new JsonBodyHandler<>(AccessTokenResponse.class))
                    .body();

            amazonCredentials.setAccessToken(accessTokenResponse.getAccessToken());
            amazonCredentials.setRefreshToken(accessTokenResponse.getRefreshToken());
            amazonCredentials.setExpiresInTime(ZonedDateTime.now(ZoneOffset.UTC).plusSeconds(accessTokenResponse.getExpiresIn()));
            amazonCredentials.setTokenType(accessTokenResponse.getTokenType());
        }
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

    @SneakyThrows
    protected String getString(Object body) {
        return objectMapper.writeValueAsString(body);
    }

    protected void addParameters(URIBuilder uriBuilder, HashMap<String, String> params) {
        if (params == null || params.isEmpty()) return;
        for (String key : params.keySet()) {
            uriBuilder.addParameter(key, params.get(key));
        }
    }

    protected URI addParameters(URI uri, HashMap<String, String> params) {
        String query = uri.getQuery();
        StringBuilder builder = new StringBuilder();

        if (query != null)
            builder.append(query);

        for (Map.Entry<String, String> entry : params.entrySet()) {
            String keyValueParam = entry.getKey() + "=" + entry.getValue();
            if (!builder.toString().isEmpty())
                builder.append("&");
            builder.append(keyValueParam);
        }
        return URI.create(uri.getScheme() + "://" + uri.getAuthority() + uri.getPath() + "?" + builder);
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
}