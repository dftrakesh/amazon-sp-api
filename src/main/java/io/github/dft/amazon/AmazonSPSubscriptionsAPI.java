package io.github.dft.amazon;

import com.amazonaws.http.HttpMethodName;
import io.github.dft.amazon.model.AmazonCredentials;
import io.github.dft.amazon.model.handler.JsonBodyHandler;
import io.github.dft.amazon.model.subscription.SubscriptionRequest;
import io.github.dft.amazon.model.subscription.SubscriptionWrapper;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static io.github.dft.amazon.constantcode.ConstantCodes.FORWARD_SLASH;
import static io.github.dft.amazon.constantcode.ConstantCodes.HTTP_HEADER_ACCEPTS;
import static io.github.dft.amazon.constantcode.ConstantCodes.HTTP_HEADER_AUTHORIZATION;
import static io.github.dft.amazon.constantcode.ConstantCodes.HTTP_HEADER_CONTENT_TYPE;
import static io.github.dft.amazon.constantcode.ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON;
import static io.github.dft.amazon.constantcode.ConstantCodes.HTTP_HEADER_X_AMZ_ACCESS_TOKEN;
import static io.github.dft.amazon.constantcode.ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN;
import static io.github.dft.amazon.constantcode.ConstantCodes.NOTIFICATION_SUBSCRIPTION_API_V1;
import static io.github.dft.amazon.constantcode.ConstantCodes.X_AMZ_DATE;

public class AmazonSPSubscriptionsAPI extends AmazonSellingPartnerSdk {

    public AmazonSPSubscriptionsAPI(AmazonCredentials amazonCredentials) {
        super(amazonCredentials);
    }

    public SubscriptionWrapper createSubscription(String notificationType, SubscriptionRequest subscriptionRequest) {
        URI uri = URI.create(sellingRegionEndpoint + NOTIFICATION_SUBSCRIPTION_API_V1 + FORWARD_SLASH + notificationType);
        String requestBody = getString(subscriptionRequest);

        final var signRequest = signRequest(
                String.valueOf(uri),
                HttpMethodName.POST,
                null,
                requestBody
        );

        HttpRequest request = HttpRequest.newBuilder(uri)
                .header(HTTP_HEADER_ACCEPTS, HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(HTTP_HEADER_CONTENT_TYPE, HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(HTTP_HEADER_X_AMZ_ACCESS_TOKEN, amazonCredentials.getAccessToken())
                .header(HTTP_HEADER_AUTHORIZATION, signRequest.getHeaders().get(HTTP_HEADER_AUTHORIZATION))
                .header(HTTP_HEADER_X_AMZ_SECURITY_TOKEN, signRequest.getHeaders().get(HTTP_HEADER_X_AMZ_SECURITY_TOKEN))
                .header(X_AMZ_DATE, signRequest.getHeaders().get(X_AMZ_DATE))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse.BodyHandler<SubscriptionWrapper> handler = new JsonBodyHandler<>(SubscriptionWrapper.class);
        return getRequestWrapped(request, handler);
    }


    public SubscriptionWrapper getAllSubscription(String notificationType) {
        URI uri = URI.create(sellingRegionEndpoint + NOTIFICATION_SUBSCRIPTION_API_V1 + FORWARD_SLASH + notificationType);

        final var signRequest = signRequest(
                String.valueOf(uri),
                HttpMethodName.GET,
                null,
                null
        );

        HttpRequest request = HttpRequest.newBuilder(uri)
                .header(HTTP_HEADER_ACCEPTS, HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(HTTP_HEADER_CONTENT_TYPE, HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(HTTP_HEADER_X_AMZ_ACCESS_TOKEN, amazonCredentials.getAccessToken())
                .header(HTTP_HEADER_AUTHORIZATION, signRequest.getHeaders().get(HTTP_HEADER_AUTHORIZATION))
                .header(HTTP_HEADER_X_AMZ_SECURITY_TOKEN, signRequest.getHeaders().get(HTTP_HEADER_X_AMZ_SECURITY_TOKEN))
                .header(X_AMZ_DATE, signRequest.getHeaders().get(X_AMZ_DATE))
                .GET()
                .build();

        HttpResponse.BodyHandler<SubscriptionWrapper> handler = new JsonBodyHandler<>(SubscriptionWrapper.class);
        return getRequestWrapped(request, handler);
    }

    public SubscriptionWrapper getByNotificationTypeAndSubscriptionId(String notificationType, String subscriptionId) {
        URI uri = URI.create(sellingRegionEndpoint + NOTIFICATION_SUBSCRIPTION_API_V1 + FORWARD_SLASH + notificationType + FORWARD_SLASH + subscriptionId);

        final var signRequest = signRequest(
                String.valueOf(uri),
                HttpMethodName.GET,
                null,
                null
        );

        HttpRequest request = HttpRequest.newBuilder(uri)
                .header(HTTP_HEADER_ACCEPTS, HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(HTTP_HEADER_CONTENT_TYPE, HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(HTTP_HEADER_X_AMZ_ACCESS_TOKEN, amazonCredentials.getAccessToken())
                .header(HTTP_HEADER_AUTHORIZATION, signRequest.getHeaders().get(HTTP_HEADER_AUTHORIZATION))
                .header(HTTP_HEADER_X_AMZ_SECURITY_TOKEN, signRequest.getHeaders().get(HTTP_HEADER_X_AMZ_SECURITY_TOKEN))
                .header(X_AMZ_DATE, signRequest.getHeaders().get(X_AMZ_DATE))
                .GET()
                .build();

        HttpResponse.BodyHandler<SubscriptionWrapper> handler = new JsonBodyHandler<>(SubscriptionWrapper.class);
        return getRequestWrapped(request, handler);
    }

    public void deleteByNotificationTypeAndSubscriptionId(String notificationType, String subscriptionId) {
        URI uri = URI.create(sellingRegionEndpoint + NOTIFICATION_SUBSCRIPTION_API_V1 + FORWARD_SLASH + notificationType + FORWARD_SLASH + subscriptionId);

        final var signRequest = signRequest(
                String.valueOf(uri),
                HttpMethodName.DELETE,
                null,
                null
        );

        HttpRequest request = HttpRequest.newBuilder(uri)
                .header(HTTP_HEADER_ACCEPTS, HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(HTTP_HEADER_CONTENT_TYPE, HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(HTTP_HEADER_X_AMZ_ACCESS_TOKEN, amazonCredentials.getAccessToken())
                .header(HTTP_HEADER_AUTHORIZATION, signRequest.getHeaders().get(HTTP_HEADER_AUTHORIZATION))
                .header(HTTP_HEADER_X_AMZ_SECURITY_TOKEN, signRequest.getHeaders().get(HTTP_HEADER_X_AMZ_SECURITY_TOKEN))
                .header(X_AMZ_DATE, signRequest.getHeaders().get(X_AMZ_DATE))
                .DELETE()
                .build();

        getRequestWrapped(request, HttpResponse.BodyHandlers.ofString());
    }
}
