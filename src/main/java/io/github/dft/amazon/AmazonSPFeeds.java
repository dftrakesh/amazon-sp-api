package io.github.dft.amazon;

import com.amazonaws.http.HttpMethodName;
import io.github.dft.amazon.constantcode.ConstantCodes;
import io.github.dft.amazon.model.AmazonCredentials;
import io.github.dft.amazon.model.feeds.v20210630.*;
import io.github.dft.amazon.model.handler.JsonBodyHandler;
import lombok.SneakyThrows;
import org.apache.http.client.utils.URIBuilder;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class AmazonSPFeeds extends AmazonSellingPartnerSdk {


    @SneakyThrows
    public AmazonSPFeeds(AmazonCredentials amazonCredentials) {
        super(amazonCredentials);
    }

    @SneakyThrows
    public GetFeedsResponse getFeeds(HashMap<String, String> params) {

        final var signRequest = signRequest(ConstantCodes.FEEDS_API_V20160630, HttpMethodName.GET, params, null);

        URIBuilder uriBuilder = new URIBuilder(sellingRegionEndpoint + ConstantCodes.FEEDS_API_V20160630);
        addParameters(uriBuilder, params);

        HttpRequest request = HttpRequest.newBuilder(uriBuilder.build())
                .header(ConstantCodes.HTTP_HEADER_ACCEPTS, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(ConstantCodes.HTTP_HEADER_CONTENT_TYPE, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(ConstantCodes.HTTP_HEADER_X_AMZ_ACCESS_TOKEN, amazonCredentials.getAccessToken())
                .header(ConstantCodes.HTTP_HEADER_AUTHORIZATION, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_AUTHORIZATION))
                .header(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN))
                .header(ConstantCodes.X_AMZ_DATE, signRequest.getHeaders().get(ConstantCodes.X_AMZ_DATE))
                .build();

        HttpResponse.BodyHandler<GetFeedsResponse> handler = new JsonBodyHandler<>(GetFeedsResponse.class);

        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public Feed getFeed(String feedId) {

        String feedUrl = ConstantCodes.FEEDS_API_V20160630 + "/" + feedId;
        final var signRequest = signRequest(feedUrl, HttpMethodName.GET, null, null);

        HttpRequest request = HttpRequest.newBuilder(new URI(sellingRegionEndpoint + feedUrl))
                .header(ConstantCodes.HTTP_HEADER_ACCEPTS, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(ConstantCodes.HTTP_HEADER_CONTENT_TYPE, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(ConstantCodes.HTTP_HEADER_X_AMZ_ACCESS_TOKEN, amazonCredentials.getAccessToken())
                .header(ConstantCodes.HTTP_HEADER_AUTHORIZATION, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_AUTHORIZATION))
                .header(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN))
                .header(ConstantCodes.X_AMZ_DATE, signRequest.getHeaders().get(ConstantCodes.X_AMZ_DATE))
                .build();

        HttpResponse.BodyHandler<Feed> handler = new JsonBodyHandler<>(Feed.class);

        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public FeedDocument getFeedDocument(String feedDocumentId) {

        String feedUrl = ConstantCodes.FEEDS_API_V20160630_CREATE_FEED_DOCUMENT + "/" + feedDocumentId;
        final var signRequest = signRequest(feedUrl, HttpMethodName.GET, null, null);

        HttpRequest request = HttpRequest.newBuilder(new URI(sellingRegionEndpoint + feedUrl))
                .header(ConstantCodes.HTTP_HEADER_ACCEPTS, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(ConstantCodes.HTTP_HEADER_CONTENT_TYPE, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(ConstantCodes.HTTP_HEADER_X_AMZ_ACCESS_TOKEN, amazonCredentials.getAccessToken())
                .header(ConstantCodes.HTTP_HEADER_AUTHORIZATION, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_AUTHORIZATION))
                .header(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN))
                .header(ConstantCodes.X_AMZ_DATE, signRequest.getHeaders().get(ConstantCodes.X_AMZ_DATE))
                .build();

        HttpResponse.BodyHandler<FeedDocument> handler = new JsonBodyHandler<>(FeedDocument.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public String cancelFeed(String feedId) {

        String feedUrl = ConstantCodes.FEEDS_API_V20160630 + "/" + feedId;
        final var signRequest = signRequest(feedUrl, HttpMethodName.GET, null, null);

        HttpRequest request = HttpRequest.newBuilder(new URI(sellingRegionEndpoint + feedUrl))
                .header(ConstantCodes.HTTP_HEADER_ACCEPTS, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(ConstantCodes.HTTP_HEADER_CONTENT_TYPE, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(ConstantCodes.HTTP_HEADER_X_AMZ_ACCESS_TOKEN, amazonCredentials.getAccessToken())
                .header(ConstantCodes.HTTP_HEADER_AUTHORIZATION, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_AUTHORIZATION))
                .header(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN))
                .header(ConstantCodes.X_AMZ_DATE, signRequest.getHeaders().get(ConstantCodes.X_AMZ_DATE))
                .DELETE()
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .get()
                .body();
    }

    @SneakyThrows
    public CreateFeedDocumentResponse createFeedDocument(CreateFeedDocumentSpecification createFeedDocumentSpecification) {

        String requestBody = getString(createFeedDocumentSpecification);
        final var signRequest = signRequest(ConstantCodes.FEEDS_API_V20160630_CREATE_FEED_DOCUMENT, HttpMethodName.POST, null, requestBody);

        URIBuilder uriBuilder = new URIBuilder(sellingRegionEndpoint + ConstantCodes.FEEDS_API_V20160630_CREATE_FEED_DOCUMENT);

        HttpRequest request = HttpRequest.newBuilder(uriBuilder.build())
                .header(ConstantCodes.HTTP_HEADER_ACCEPTS, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(ConstantCodes.HTTP_HEADER_CONTENT_TYPE, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(ConstantCodes.HTTP_HEADER_X_AMZ_ACCESS_TOKEN, amazonCredentials.getAccessToken())
                .header(ConstantCodes.HTTP_HEADER_AUTHORIZATION, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_AUTHORIZATION))
                .header(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN))
                .header(ConstantCodes.X_AMZ_DATE, signRequest.getHeaders().get(ConstantCodes.X_AMZ_DATE))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse.BodyHandler<CreateFeedDocumentResponse> handler = new JsonBodyHandler<>(CreateFeedDocumentResponse.class);

        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public CreateFeedResponse createFeed(CreateFeedSpecification createFeedSpecification) {

        String requestBody = getString(createFeedSpecification);
        final var signRequest = signRequest(ConstantCodes.FEEDS_API_V20160630, HttpMethodName.POST, null, requestBody);

        HttpRequest request = HttpRequest.newBuilder(new URI(sellingRegionEndpoint + ConstantCodes.FEEDS_API_V20160630))
                .header(ConstantCodes.HTTP_HEADER_ACCEPTS, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(ConstantCodes.HTTP_HEADER_CONTENT_TYPE, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(ConstantCodes.HTTP_HEADER_X_AMZ_ACCESS_TOKEN, amazonCredentials.getAccessToken())
                .header(ConstantCodes.HTTP_HEADER_AUTHORIZATION, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_AUTHORIZATION))
                .header(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN))
                .header(ConstantCodes.X_AMZ_DATE, signRequest.getHeaders().get(ConstantCodes.X_AMZ_DATE))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse.BodyHandler<CreateFeedResponse> handler = new JsonBodyHandler<>(CreateFeedResponse.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public void uploadFeedDocument(String feedUrl, InputStream inputStream) {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(feedUrl))
                .POST(HttpRequest.BodyPublishers.ofByteArray(inputStream.readAllBytes()))
                .build();
    }

    @SneakyThrows
    public String uploadFeedDocument(String feedUrl, File file, String contentType) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(feedUrl))
                .header(ConstantCodes.HTTP_HEADER_CONTENT_TYPE, contentType)
                .PUT(HttpRequest.BodyPublishers.ofFile(file.toPath()))
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .get()
                .body();
    }

    @SneakyThrows
    public String uploadFeedDocument(String feedUrl, String file, String contentType) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(feedUrl))
                .header(ConstantCodes.HTTP_HEADER_CONTENT_TYPE, contentType)
                .PUT(HttpRequest.BodyPublishers.ofByteArray(file.getBytes(StandardCharsets.UTF_8)))
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString())
                .body();
    }
}