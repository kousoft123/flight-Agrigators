package com.kou.flight.http;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

import java.util.Map;

@Component
public class HttpClientHelper {

    private final RestClient.Builder builder;

    public HttpClientHelper(RestClient.Builder builder) {
        this.builder = builder;
    }

    public <T> T get(String baseUrl, String path, Map<String, String> queryParams, Class<T> responseType) {
        try {
            RestClient restClient = builder.baseUrl(baseUrl).build();

            return restClient.get()
                .uri(uriBuilder -> {
                    uriBuilder.path(path);
                    queryParams.forEach(uriBuilder::queryParam);
                    return uriBuilder.build();
                })
                .retrieve()
                .body(responseType);
        } catch (RestClientResponseException e) {
            // Log and rather or return null/fallback
            System.err.printf("HTTP error calling %s%s: %s%n", baseUrl, path, e.getMessage());
            return null;
        } catch (Exception e) {
            System.err.printf("General error: %s%n", e.getMessage());
            return null;
        }
    }

    public <T> T post(String baseUrl, String path, Object body, Class<T> responseType) {
        try {
            RestClient restClient = builder.baseUrl(baseUrl).build();

            return restClient.post()
                .uri(path)
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(body)
                .retrieve()
                .body(responseType);
        } catch (RestClientResponseException e) {
            System.err.printf("HTTP error POST %s%s: %s%n", baseUrl, path, e.getMessage());
            return null;
        } catch (Exception e) {
            System.err.printf("General error: %s%n", e.getMessage());
            return null;
        }
    }
}