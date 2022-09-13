package io.github.dft.amazon.constantcode;

import lombok.SneakyThrows;

import java.net.URI;
import java.util.AbstractMap;
import java.util.Map;

public interface ConstantCodes {

    String HTTP_METHOD_GET = "GET";
    String HTTP_METHOD_POST = "POST";
    String HTTP_METHOD_PUT = "PUT";
    String HTTP_METHOD_DELETE = "DELETE";
    String HTTP_METHOD_PATCH = "PATCH";

    String HTTP_OAUTH_PARAMETER_GRANT_TYPE = "grant_type";
    String HTTP_OAUTH_PARAMETER_REFRESH_TOKEN = "refresh_token";
    String HTTP_OAUTH_PARAMETER_CLIENT_ID = "client_id";
    String HTTP_OAUTH_PARAMETER_CLIENT_SECRET = "client_secret";
    String REFRESH_TOKEN = "refresh_token";

    String HTTP_HEADER_ACCEPTS = "Accept";
    String HTTP_HEADER_AUTHORIZATION = "Authorization";
    String HTTP_HEADER_X_AMZ_SECURITY_TOKEN = "X-Amz-Security-Token";
    String HTTP_HEADER_CONTENT_TYPE = "Content-Type";
    String HTTP_HEADER_X_AMZ_ACCESS_TOKEN = "x-amz-access-token";
    String HTTP_HEADER_VALUE_APPLICATION_JSON = "application/json";
    String HTTP_HEADER_VALUE_APPLICATION_FORM_URL_ENCODED = "application/x-www-form-urlencoded";

    String SELLERS_API_V1 = "/sellers/v1/marketplaceParticipations";
    String CREATE_REPORT_API_V202106 = "/reports/2021-06-30/reports";

    String LWA_AUTHORIZATION_SERVER = "https://api.amazon.com/auth/o2/token";

    String AWS_REGION_US_EAST_1 = "us-east-1";
    String AWS_REGION_US_WEST_1 = "us-west-2";
    String AWS_REGION_EU_WEST_1 = "eu-west-1";

    String SERVICE_NAME = "execute-api";
    String X_AMZ_DATE = "X-Amz-Date" ;
}
