package com.example.lld.practise;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpUrlService {
    private final HttpClient httpClient;

    public HttpUrlService() {
        this(HttpClient.newHttpClient());
    }

    public HttpUrlService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public record UrlResponse(
            String url,
            int statusCode,
            String body,
            String error) {
    }

    public List<UrlResponse> fetchAll(List<String> urls) {
        List<UrlResponse> results = new ArrayList<>();

        for (String url : urls) {
            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .GET()
                        .build();

                HttpResponse<String> response = httpClient.send(
                        request,
                        HttpResponse.BodyHandlers.ofString()
                );

                results.add(new UrlResponse(
                        url,
                        response.statusCode(),
                        response.body(),
                        null
                ));

            } catch (IOException | InterruptedException e) {
                if (e instanceof InterruptedException) {
                    Thread.currentThread().interrupt();
                }

                results.add(new UrlResponse(
                        url, -1, null, e.getMessage()
                ));

                // Preserve interruption and stop further requests.
                if (e instanceof InterruptedException) {
                    break;
                }
            } catch (IllegalArgumentException e) {
                // For malformed URLs.
                results.add(new UrlResponse(
                        url, -1, null, e.getMessage()
                ));
            }
        }

        return results;
    }
}
