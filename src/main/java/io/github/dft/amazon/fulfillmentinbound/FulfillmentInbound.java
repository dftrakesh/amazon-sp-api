package io.github.dft.amazon.fulfillmentinbound;

import com.amazonaws.http.HttpMethodName;
import io.github.dft.amazon.AmazonSellingPartnerSdk;
import io.github.dft.amazon.constantcode.ConstantCodes;
import io.github.dft.amazon.fulfillmentinbound.model.*;
import io.github.dft.amazon.model.AmazonCredentials;
import io.github.dft.amazon.model.handler.JsonBodyHandler;
import io.github.dft.amazon.model.sellersapi.v1.GetMarketplaceParticipationsResponse;
import lombok.SneakyThrows;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FulfillmentInbound extends AmazonSellingPartnerSdk {

    @SneakyThrows
    public FulfillmentInbound(AmazonCredentials amazonCredentials) {
        super(amazonCredentials);
    }

    @SneakyThrows
    public GetShipmentsResponse getShipments(HashMap<String, String> query) {

        final var signRequest = signRequest(ConstantCodes.FULFILLMENT_INBOUND_SHIPMENT_VO_SHIPMENTS, HttpMethodName.GET, query);

        URI uri = new URI(sellingRegionEndpoint + ConstantCodes.FULFILLMENT_INBOUND_SHIPMENT_VO_SHIPMENTS);
        uri = addParameters(uri, query);
        HttpRequest request = HttpRequest.newBuilder(uri)
            .header(ConstantCodes.HTTP_HEADER_ACCEPTS, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
            .header(ConstantCodes.HTTP_HEADER_CONTENT_TYPE, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
            .header(ConstantCodes.HTTP_HEADER_X_AMZ_ACCESS_TOKEN, amazonCredentials.getAccessToken())
            .header(ConstantCodes.HTTP_HEADER_AUTHORIZATION, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_AUTHORIZATION))
            .header(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN))
            .header(ConstantCodes.X_AMZ_DATE, signRequest.getHeaders().get(ConstantCodes.X_AMZ_DATE))
            .build();

        HttpResponse.BodyHandler<GetShipmentsResponse> handler = new JsonBodyHandler<>(GetShipmentsResponse.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public GetShipmentItemsResponse getShipmentItemsByShipmentId(String shipmentId) {
        String url = String.format(ConstantCodes.FULFILLMENT_INBOUND_SHIPMENT_VO_SHIPMENT_ITEMS, shipmentId);
        final var signRequest = signRequest(url, HttpMethodName.GET, null);

        URI uri = new URI(sellingRegionEndpoint + url);
        HttpRequest request = HttpRequest.newBuilder(uri)
                .header(ConstantCodes.HTTP_HEADER_ACCEPTS, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(ConstantCodes.HTTP_HEADER_CONTENT_TYPE, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
                .header(ConstantCodes.HTTP_HEADER_X_AMZ_ACCESS_TOKEN, amazonCredentials.getAccessToken())
                .header(ConstantCodes.HTTP_HEADER_AUTHORIZATION, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_AUTHORIZATION))
                .header(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN))
                .header(ConstantCodes.X_AMZ_DATE, signRequest.getHeaders().get(ConstantCodes.X_AMZ_DATE))
                .build();

        HttpResponse.BodyHandler<GetShipmentItemsResponse> handler = new JsonBodyHandler<>(GetShipmentItemsResponse.class);
        return getRequestWrapped(request, handler);
    }


    @SneakyThrows
    public List<InboundShipmentInfo> getAllShipmentsByStatuses(String statuses) {

        HashMap<String, String> query = new HashMap<>();
        query.put("ShipmentStatusList",statuses);
        query.put("QueryType","SHIPMENT");

        GetShipmentsResponse getShipmentsResponse = getShipments(query);
        GetShipmentsResult getShipmentsResult = getShipmentsResponse.getPayload();
        InboundShipmentList<InboundShipmentInfo> inboundShipmentList = getShipmentsResult.getShipmentData();
        List<InboundShipmentInfo> inboundShipmentInfoList = new ArrayList<>(inboundShipmentList);

        while (getShipmentsResult.getNextToken() != null) {
            query = new HashMap<>();
            query.put("NextToken", getShipmentsResult.getNextToken());
            query.put("QueryType","NEXT_TOKEN");
            getShipmentsResponse = getShipments(query);

            getShipmentsResult = getShipmentsResponse.getPayload();
            inboundShipmentList = getShipmentsResult.getShipmentData();
            inboundShipmentInfoList.addAll(inboundShipmentList);
        }
        return inboundShipmentInfoList;
    }


}