package com.example.lld.practise;

import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.InetSocketAddress;
import java.net.http.HttpClient;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HttpUrlServiceTest {

    private HttpServer server;
    private HttpUrlService service;
    private String baseUrl;

    @BeforeEach
    void setUp() throws Exception {
        server = HttpServer.create(
                new InetSocketAddress("127.0.0.1", 0), 0);

        server.createContext("/success", exchange -> {
            byte[] body = "Hello World".getBytes();
            exchange.sendResponseHeaders(200, body.length);
            exchange.getResponseBody().write(body);
            exchange.close();
        });

        server.createContext("/missing", exchange -> {
            byte[] body = "Not Found".getBytes();
            exchange.sendResponseHeaders(404, body.length);
            exchange.getResponseBody().write(body);
            exchange.close();
        });

        server.start();

        baseUrl = "http://127.0.0.1:" +
                server.getAddress().getPort();

        service = new HttpUrlService(HttpClient.newHttpClient());
    }

    @AfterEach
    void tearDown() {
        server.stop(0);
    }

    @Test
    void shouldReturnSuccessfulResponse() {
        var results = service.fetchAll(
                List.of(baseUrl + "/success"));

        assertEquals(1, results.size());
        assertEquals(200, results.get(0).statusCode());
        assertEquals("Hello World", results.get(0).body());
        assertNull(results.get(0).error());
    }

    @Test
    void shouldHandle404Response() {
        var results = service.fetchAll(
                List.of(baseUrl + "/missing"));

        assertEquals(404, results.get(0).statusCode());
        assertEquals("Not Found", results.get(0).body());
        assertNull(results.get(0).error());
    }

//    @Test
//    void shouldContinueAfterFailedRequest() {
//        String invalidHost =
//                "http://invalid-host-that-does-not-exist.invalid";
//
//        var results = service.fetchAll(List.of(
//                invalidHost,
//                baseUrl + "/success"
//        ));
//
//        assertEquals(2, results.size());
//        assertEquals(-1, results.get(0).statusCode());
//        assertNotNull(results.get(0).error());
//        assertEquals(200, results.get(1).statusCode());
//    }

    @Test
    void shouldHandleEmptyUrlList() {
        assertTrue(service.fetchAll(List.of()).isEmpty());
    }
}
