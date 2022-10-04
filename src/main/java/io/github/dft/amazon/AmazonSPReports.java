package io.github.dft.amazon;

import com.amazonaws.http.HttpMethodName;
import com.google.common.util.concurrent.RateLimiter;
import io.github.dft.amazon.constantcode.ConstantCodes;
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

public class AmazonSPReports extends AmazonSellingPartnerSdk {

    private final HttpClient client;
    private final RateLimiter rateLimiter02;
    private final RateLimiter rateLimiter01;
    private final RateLimiter rateLimiter2;

    @SneakyThrows
    public AmazonSPReports(AccessCredentials accessCredentials) {
        super(accessCredentials);
        this.rateLimiter02 = RateLimiter.create(0.0222);
        this.rateLimiter01 = RateLimiter.create(0.0167);
        this.rateLimiter2 = RateLimiter.create(2);
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

        return getRequestWrapped(request, handler, rateLimiter01);
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

        return getRequestWrapped(request, handler, rateLimiter2);
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

        return getRequestWrapped(request, handler, rateLimiter01);
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

        return getRequestWrapped(request, handler, rateLimiter02);
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

        return getRequestWrapped(request, handler, rateLimiter02);
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

        return getRequestWrapped(request, handler, rateLimiter02);
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

        return getRequestWrapped(request, handler, rateLimiter02);
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

        return getRequestWrapped(request, handler, rateLimiter02);
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

        return getRequestWrapped(request, handler, rateLimiter02);
    }

    @SneakyThrows
    public <T> T getRequestWrapped(HttpRequest request, HttpResponse.BodyHandler<T> handler, RateLimiter rateLimiter) {
        rateLimiter.acquire();
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