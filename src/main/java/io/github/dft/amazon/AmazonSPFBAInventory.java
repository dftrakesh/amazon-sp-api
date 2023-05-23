package io.github.dft.amazon;

import com.amazonaws.http.HttpMethodName;
import io.github.dft.amazon.constantcode.ConstantCodes;
import io.github.dft.amazon.model.AmazonCredentials;
import io.github.dft.amazon.model.fbainventory.v1.GetInventorySummariesResponse;
import io.github.dft.amazon.model.handler.JsonBodyHandler;
import lombok.SneakyThrows;
import org.apache.http.client.utils.URIBuilder;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class AmazonSPFBAInventory extends AmazonSellingPartnerSdk {

    public AmazonSPFBAInventory(AmazonCredentials amazonCredentials) {
        super(amazonCredentials);
    }

    @SneakyThrows
    public GetInventorySummariesResponse getInventorySummaries(HashMap<String, String> params) {
        final var signRequest = signRequest(ConstantCodes.FBA_INVENTORY_API_V1, HttpMethodName.GET, params, null);

        URIBuilder uriBuilder = new URIBuilder(sellingRegionEndpoint + ConstantCodes.FBA_INVENTORY_API_V1);
        addParameters(uriBuilder, params);

        URI uri = uriBuilder.build();
        HttpRequest request = HttpRequest.newBuilder(uri)
            .header(ConstantCodes.HTTP_HEADER_ACCEPTS, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
            .header(ConstantCodes.HTTP_HEADER_CONTENT_TYPE, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
            .header(ConstantCodes.HTTP_HEADER_X_AMZ_ACCESS_TOKEN, amazonCredentials.getAccessToken())
            .header(ConstantCodes.HTTP_HEADER_AUTHORIZATION, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_AUTHORIZATION))
            .header(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN))
            .header(ConstantCodes.X_AMZ_DATE, signRequest.getHeaders().get(ConstantCodes.X_AMZ_DATE))
            .GET()
            .build();

        HttpResponse.BodyHandler<GetInventorySummariesResponse> handler = new JsonBodyHandler<>(GetInventorySummariesResponse.class);

        return getRequestWrapped(request, handler);
    }
}