package io.github.dft.amazon;

import com.amazonaws.http.HttpMethodName;
import com.amazonaws.util.CollectionUtils;
import io.github.dft.amazon.constantcode.ConstantCodes;
import io.github.dft.amazon.constantcode.RateLimitConstants;
import io.github.dft.amazon.model.AccessCredentials;
import io.github.dft.amazon.model.handler.JsonBodyHandler;
import io.github.dft.amazon.model.reports.v202106.CancelResponse;
import io.github.dft.amazon.model.reports.v202106.CreateReportResponse;
import io.github.dft.amazon.model.reports.v202106.CreateReportScheduleResponse;
import io.github.dft.amazon.model.reports.v202106.CreateReportScheduleSpecification;
import io.github.dft.amazon.model.reports.v202106.CreateReportSpecification;
import io.github.dft.amazon.model.reports.v202106.GetReportsResponse;
import io.github.dft.amazon.model.reports.v202106.Report;
import io.github.dft.amazon.model.reports.v202106.ReportDocument;
import io.github.dft.amazon.model.reports.v202106.ReportSchedule;
import io.github.dft.amazon.model.reports.v202106.ReportScheduleList;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

import static io.github.dft.amazon.constantcode.ConstantCodes.MAX_ATTEMPTS;
import static io.github.dft.amazon.constantcode.ConstantCodes.TIME_OUT_DURATION;
import static io.github.dft.amazon.constantcode.ConstantCodes.X_AMZN_RATE_LIMIT;

@Log4j2
public class AmazonSPReports extends AmazonSellingPartnerSdk {

    private final HttpClient client;
    private final RateLimitConstants rateLimitConstants;

    @SneakyThrows
    public AmazonSPReports(AccessCredentials accessCredentials) {
        super(accessCredentials);
        this.rateLimitConstants = new RateLimitConstants();
        client = HttpClient.newHttpClient();
    }

    @SneakyThrows
    public CreateReportResponse createReport(CreateReportSpecification createReportSpecification) {
        String requestBody = getString(createReportSpecification);

        final var signRequest = signRequest(
            ConstantCodes.REPORTS_API_V202106,
            HttpMethodName.POST,
            null,
            requestBody
        );

        HttpRequest request = HttpRequest.newBuilder(new URI(sellingRegionEndpoint + ConstantCodes.REPORTS_API_V202106))
            .header(ConstantCodes.HTTP_HEADER_ACCEPTS, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
            .header(ConstantCodes.HTTP_HEADER_CONTENT_TYPE, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
            .header(ConstantCodes.HTTP_HEADER_X_AMZ_ACCESS_TOKEN, accessCredentials.getAccessToken())
            .header(ConstantCodes.HTTP_HEADER_AUTHORIZATION, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_AUTHORIZATION))
            .header(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN))
            .header(ConstantCodes.X_AMZ_DATE, signRequest.getHeaders().get(ConstantCodes.X_AMZ_DATE))
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .build();

        HttpResponse.BodyHandler<CreateReportResponse> handler = new JsonBodyHandler<>(CreateReportResponse.class);
        rateLimitConstants.CREATE_REPORT_API_CALL = setRateLimit(
            rateLimitConstants.CREATE_REPORT_API_CALL,
            rateLimitConstants.CREATE_REPORT_LIMIT_REFRESH,
            rateLimitConstants.CREATE_REPORT_RATE_LIMIT
        );

        return client
            .sendAsync(request, handler)
            .thenComposeAsync(response -> tryResend(client, request, handler, response, 1))
            .get()
            .body();
    }

    @SneakyThrows
    public Report getReport(String reportId) {

        final var signRequest = signRequest(
            ConstantCodes.REPORTS_API_V202106.concat("/").concat(reportId),
            HttpMethodName.GET,
            null,
            null
        );

        HttpRequest request = HttpRequest.newBuilder(new URI(sellingRegionEndpoint + ConstantCodes.REPORTS_API_V202106.concat("/").concat(reportId)))
            .header(ConstantCodes.HTTP_HEADER_ACCEPTS, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
            .header(ConstantCodes.HTTP_HEADER_CONTENT_TYPE, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
            .header(ConstantCodes.HTTP_HEADER_X_AMZ_ACCESS_TOKEN, accessCredentials.getAccessToken())
            .header(ConstantCodes.HTTP_HEADER_AUTHORIZATION, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_AUTHORIZATION))
            .header(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN))
            .header(ConstantCodes.X_AMZ_DATE, signRequest.getHeaders().get(ConstantCodes.X_AMZ_DATE))
            .GET()
            .build();

        HttpResponse.BodyHandler<Report> handler = new JsonBodyHandler<>(Report.class);
        rateLimitConstants.GET_REPORT_API_CALL = setRateLimit(
            rateLimitConstants.GET_REPORT_API_CALL,
            rateLimitConstants.GET_REPORT_LIMIT_REFRESH,
            rateLimitConstants.GET_REPORT_RATE_LIMIT
        );

        return client
            .sendAsync(request, handler)
            .thenComposeAsync(response -> tryResend(client, request, handler, response, 1))
            .get()
            .body();
    }

    @SneakyThrows
    public ReportDocument getReportDocument(String reportDocumentId) {
        reportDocumentId = StringUtils.isEmpty(reportDocumentId) ? "" : reportDocumentId;

        final var signRequest = signRequest(
            ConstantCodes.REPORT_DOCUMENTS_API_V202106.concat("/").concat(reportDocumentId),
            HttpMethodName.GET,
            null,
            null
        );

        HttpRequest request = HttpRequest.newBuilder(new URI(sellingRegionEndpoint + ConstantCodes.REPORT_DOCUMENTS_API_V202106.concat("/").concat(reportDocumentId)))
            .header(ConstantCodes.HTTP_HEADER_ACCEPTS, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
            .header(ConstantCodes.HTTP_HEADER_CONTENT_TYPE, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
            .header(ConstantCodes.HTTP_HEADER_X_AMZ_ACCESS_TOKEN, accessCredentials.getAccessToken())
            .header(ConstantCodes.HTTP_HEADER_AUTHORIZATION, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_AUTHORIZATION))
            .header(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN))
            .header(ConstantCodes.X_AMZ_DATE, signRequest.getHeaders().get(ConstantCodes.X_AMZ_DATE))
            .GET()
            .build();

        HttpResponse.BodyHandler<ReportDocument> handler = new JsonBodyHandler<>(ReportDocument.class);
        rateLimitConstants.GET_REPORT_DOCUMENT = setRateLimit(
            rateLimitConstants.GET_REPORT_DOCUMENT,
            rateLimitConstants.GET_REPORT_DOCUMENT_LIMIT_REFRESH,
            rateLimitConstants.GET_REPORT_DOCUMENT_RATE_LIMIT
        );

        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public GetReportsResponse getReports(HashMap<String, String> params) {
        final var signRequest = signRequest(
            ConstantCodes.REPORTS_API_V202106,
            HttpMethodName.GET,
            params,
            null
        );

        URIBuilder uriBuilder = new URIBuilder(sellingRegionEndpoint + ConstantCodes.REPORTS_API_V202106);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                uriBuilder.addParameter(key, params.get(key));
            }
        }

        URI uri = uriBuilder.build();
        HttpRequest request = HttpRequest.newBuilder(uri)
            .header(ConstantCodes.HTTP_HEADER_ACCEPTS, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
            .header(ConstantCodes.HTTP_HEADER_CONTENT_TYPE, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
            .header(ConstantCodes.HTTP_HEADER_X_AMZ_ACCESS_TOKEN, accessCredentials.getAccessToken())
            .header(ConstantCodes.HTTP_HEADER_AUTHORIZATION, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_AUTHORIZATION))
            .header(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN))
            .header(ConstantCodes.X_AMZ_DATE, signRequest.getHeaders().get(ConstantCodes.X_AMZ_DATE))
            .GET()
            .build();

        HttpResponse.BodyHandler<GetReportsResponse> handler = new JsonBodyHandler<>(GetReportsResponse.class);
        rateLimitConstants.GET_REPORTS_API_CALL = setRateLimit(
            rateLimitConstants.GET_REPORTS_API_CALL,
            rateLimitConstants.GET_REPORTS_LIMIT_REFRESH,
            rateLimitConstants.GET_REPORTS_RATE_LIMIT
        );

        return client
            .sendAsync(request, handler)
            .thenComposeAsync(response -> tryResend(client, request, handler, response, 1))
            .get()
            .body();
    }

    @SneakyThrows
    public CancelResponse cancelReport(String reportId) {
        reportId = StringUtils.isEmpty(reportId) ? "" : reportId;

        final var signRequest = signRequest(
            ConstantCodes.REPORTS_API_V202106.concat("/").concat(reportId),
            HttpMethodName.DELETE,
            null,
            null
        );

        HttpRequest request = HttpRequest.newBuilder(new URI(sellingRegionEndpoint + ConstantCodes.REPORTS_API_V202106.concat("/").concat(reportId)))
            .header(ConstantCodes.HTTP_HEADER_ACCEPTS, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
            .header(ConstantCodes.HTTP_HEADER_CONTENT_TYPE, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
            .header(ConstantCodes.HTTP_HEADER_X_AMZ_ACCESS_TOKEN, accessCredentials.getAccessToken())
            .header(ConstantCodes.HTTP_HEADER_AUTHORIZATION, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_AUTHORIZATION))
            .header(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN))
            .header(ConstantCodes.X_AMZ_DATE, signRequest.getHeaders().get(ConstantCodes.X_AMZ_DATE))
            .DELETE()
            .build();

        HttpResponse.BodyHandler<CancelResponse> handler = new JsonBodyHandler<>(CancelResponse.class);
        rateLimitConstants.CANCEL_REPORT_API_CALL = setRateLimit(
            rateLimitConstants.CANCEL_REPORT_API_CALL,
            rateLimitConstants.CANCEL_REPORT_LIMIT_REFRESH,
            rateLimitConstants.CANCEL_REPORT_RATE_LIMIT
        );
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public CreateReportScheduleResponse createReportSchedule(CreateReportScheduleSpecification createReportScheduleSpecification) {
        String requestBody = getString(createReportScheduleSpecification);

        final var signRequest = signRequest(ConstantCodes.REPORT_SCHEDULES_API_V202106, HttpMethodName.POST, null, requestBody);

        HttpRequest request = HttpRequest.newBuilder(new URI(sellingRegionEndpoint + ConstantCodes.REPORT_SCHEDULES_API_V202106))
            .header(ConstantCodes.HTTP_HEADER_ACCEPTS, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
            .header(ConstantCodes.HTTP_HEADER_CONTENT_TYPE, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
            .header(ConstantCodes.HTTP_HEADER_X_AMZ_ACCESS_TOKEN, accessCredentials.getAccessToken())
            .header(ConstantCodes.HTTP_HEADER_AUTHORIZATION, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_AUTHORIZATION))
            .header(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN))
            .header(ConstantCodes.X_AMZ_DATE, signRequest.getHeaders().get(ConstantCodes.X_AMZ_DATE))
            .POST(HttpRequest.BodyPublishers.ofString(requestBody)).build();

        HttpResponse.BodyHandler<CreateReportScheduleResponse> handler = new JsonBodyHandler<>(CreateReportScheduleResponse.class);
        rateLimitConstants.CREATE_REPORT_SCHEDULE_API_CALL = setRateLimit(
            rateLimitConstants.CREATE_REPORT_SCHEDULE_API_CALL,
            rateLimitConstants.CREATE_REPORT_SCHEDULE_LIMIT_REFRESH,
            rateLimitConstants.CREATE_REPORT_SCHEDULE_RATE_LIMIT
        );
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public ReportSchedule getReportSchedule(String reportScheduleId) {
        reportScheduleId = StringUtils.isEmpty(reportScheduleId) ? "" : reportScheduleId;

        final var signRequest = signRequest(
            ConstantCodes.REPORT_SCHEDULES_API_V202106.concat("/").concat(reportScheduleId),
            HttpMethodName.GET,
            null,
            null
        );

        HttpRequest request = HttpRequest.newBuilder(new URI(sellingRegionEndpoint + ConstantCodes.REPORT_SCHEDULES_API_V202106.concat("/").concat(reportScheduleId)))
            .header(ConstantCodes.HTTP_HEADER_ACCEPTS, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
            .header(ConstantCodes.HTTP_HEADER_CONTENT_TYPE, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
            .header(ConstantCodes.HTTP_HEADER_X_AMZ_ACCESS_TOKEN, accessCredentials.getAccessToken())
            .header(ConstantCodes.HTTP_HEADER_AUTHORIZATION, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_AUTHORIZATION))
            .header(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN))
            .header(ConstantCodes.X_AMZ_DATE, signRequest.getHeaders().get(ConstantCodes.X_AMZ_DATE))
            .build();

        HttpResponse.BodyHandler<ReportSchedule> handler = new JsonBodyHandler<>(ReportSchedule.class);
        rateLimitConstants.GET_REPORT_SCHEDULE_API_CALL = setRateLimit(
            rateLimitConstants.GET_REPORT_SCHEDULE_API_CALL,
            rateLimitConstants.GET_REPORT_SCHEDULE_LIMIT_REFRESH,
            rateLimitConstants.GET_REPORT_SCHEDULE_RATE_LIMIT
        );
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public ReportScheduleList getReportSchedules(HashMap<String, String> params) {
        final var signRequest = signRequest(ConstantCodes.REPORT_SCHEDULES_API_V202106,
            HttpMethodName.GET,
            params,
            null);

        URIBuilder uriBuilder = new URIBuilder(sellingRegionEndpoint + ConstantCodes.REPORT_SCHEDULES_API_V202106);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                uriBuilder.addParameter(key, params.get(key));
            }
        }

        URI uri = uriBuilder.build();
        HttpRequest request = HttpRequest.newBuilder(uri).header(ConstantCodes.HTTP_HEADER_ACCEPTS,
            ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON).header(ConstantCodes.HTTP_HEADER_CONTENT_TYPE,
            ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON).header(ConstantCodes.HTTP_HEADER_X_AMZ_ACCESS_TOKEN,
            accessCredentials.getAccessToken()).header(ConstantCodes.HTTP_HEADER_AUTHORIZATION,
            signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_AUTHORIZATION)).header(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN,
            signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN)).header(ConstantCodes.X_AMZ_DATE,
            signRequest.getHeaders().get(ConstantCodes.X_AMZ_DATE)).build();

        HttpResponse.BodyHandler<ReportScheduleList> handler = new JsonBodyHandler<>(ReportScheduleList.class);
        rateLimitConstants.GET_REPORT_SCHEDULES_API_CALL = setRateLimit(
            rateLimitConstants.GET_REPORT_SCHEDULES_API_CALL,
            rateLimitConstants.GET_REPORT_SCHEDULES_LIMIT_REFRESH,
            rateLimitConstants.GET_REPORT_SCHEDULES_RATE_LIMIT
        );
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public CancelResponse cancelReportSchedule(String reportScheduleId) {
        reportScheduleId = StringUtils.isEmpty(reportScheduleId) ? "" : reportScheduleId;

        final var signRequest = signRequest(
            ConstantCodes.REPORT_SCHEDULES_API_V202106.concat("/").concat(reportScheduleId),
            HttpMethodName.DELETE,
            null,
            null
        );

        HttpRequest request = HttpRequest.newBuilder(
                new URI(sellingRegionEndpoint + ConstantCodes.REPORT_SCHEDULES_API_V202106.concat("/").concat(reportScheduleId))
            ).header(ConstantCodes.HTTP_HEADER_ACCEPTS, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
            .header(ConstantCodes.HTTP_HEADER_CONTENT_TYPE, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
            .header(ConstantCodes.HTTP_HEADER_X_AMZ_ACCESS_TOKEN, accessCredentials.getAccessToken())
            .header(ConstantCodes.HTTP_HEADER_AUTHORIZATION, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_AUTHORIZATION))
            .header(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN, signRequest.getHeaders().get(ConstantCodes.HTTP_HEADER_X_AMZ_SECURITY_TOKEN))
            .header(ConstantCodes.X_AMZ_DATE, signRequest.getHeaders().get(ConstantCodes.X_AMZ_DATE))
            .DELETE()
            .build();

        HttpResponse.BodyHandler<CancelResponse> handler = new JsonBodyHandler<>(CancelResponse.class);
        rateLimitConstants.CANCEL_REPORT_SCHEDULE_API_CALL = setRateLimit(
            rateLimitConstants.CANCEL_REPORT_SCHEDULE_API_CALL,
            rateLimitConstants.CANCEL_REPORT_SCHEDULE_LIMIT_REFRESH,
            rateLimitConstants.CANCEL_REPORT_SCHEDULE_RATE_LIMIT
        );
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public <T> T getRequestWrapped(HttpRequest request, HttpResponse.BodyHandler<T> handler) {

        return client
            .sendAsync(request, handler)
            .thenComposeAsync(response -> {
                log.debug("statusCode: {} rate-limit: {}", response.statusCode(), response.headers().map().get(X_AMZN_RATE_LIMIT));
                return tryResend(client, request, handler, response, 1);
            })
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

    @SneakyThrows
    public int setRateLimit(int apiCall, int limitRefreshPeriod, int rateLimit) {
        if (apiCall <= 0) {
            Thread.sleep(limitRefreshPeriod);
            apiCall = rateLimit;
        }
        return apiCall - 1;
    }
}