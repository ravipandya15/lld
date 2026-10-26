package com.example.lld.practise;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class URLCalls {
    public static void main(String[] args) {
        List<String> urls = new ArrayList<>();
        urls.add("https://fake-json-api.mock.beeceptor.com/users");
        urls.add("https://fake-json-api.mock.beeceptor.com/companies");

        Map<String, String> cache = new ConcurrentHashMap<>();
        List<String> responses = new ArrayList<>();

        ExecutorService service = Executors.newFixedThreadPool(10);

        try (HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(100))
                .build()) {

            // 2. Build the GET request
            for (String url: urls) {
                if (cache.containsKey(url)) {
                    responses.add(cache.get(url));
                    continue;
                }
                service.execute(() -> fetchUrlResponse(cache, responses, url, client));
            }

            service.awaitTermination(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            service.shutdown();
        }
        System.out.println("Total responses: " + responses.size());

    }

    private static void fetchUrlResponse(Map<String, String> cache, List<String> responses, String url, HttpClient client) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            // 3. Send the request and get the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("response: " + response.body());
            responses.add(response.body());
            cache.put(url, response.body());
        }
        catch (IOException e) {
            System.err.println("IOException for URL: " + url);
            e.printStackTrace();
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            System.err.println("Request interrupted: " + url);
        }
    }
}
