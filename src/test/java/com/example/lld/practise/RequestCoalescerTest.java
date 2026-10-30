
package com.example.lld.practise;

import com.example.lld.practise.MultiThreading.requestCoalescing.RequestCoalescer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class RequestCoalescerTest {

    private RequestCoalescer<String, String> coalescer;

    @BeforeEach
    void setUp() {
        coalescer = new RequestCoalescer<>();
    }

    // Test 1: 100 concurrent requests for the same key
    @Test
    void shouldExecuteOnlyOneDbCallForConcurrentRequests()
            throws Exception {

        int totalRequests = 100;

        ExecutorService executor =
                Executors.newFixedThreadPool(totalRequests);

        CountDownLatch startLatch = new CountDownLatch(1);
//        CountDownLatch dbStarted = new CountDownLatch(1);
//        CountDownLatch releaseDb = new CountDownLatch(1);

        AtomicInteger dbCallCount = new AtomicInteger(0);

        List<Future<String>> futures = new ArrayList<>();

        for (int i = 0; i < totalRequests; i++) {

            Future<String> future = executor.submit(() -> {

                startLatch.await();

                return coalescer.get("user-101", key -> {

                    dbCallCount.incrementAndGet();

//                    dbStarted.countDown();

                    try {
//                        releaseDb.await();
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException(e);
                    }

                    return "User details";
                });
            });

            futures.add(future);
        }

        // Start all requests
        startLatch.countDown();

        // Wait until one request starts the DB call
//        assertTrue(dbStarted.await(5, TimeUnit.SECONDS));

        // Keep DB request blocked while other requests execute
//        Thread.sleep(300);

        // Allow DB request to complete
//        releaseDb.countDown();

        // Verify all requests receive the same response
        for (Future<String> future : futures) {
            assertEquals(
                    "User details",
                    future.get(10, TimeUnit.SECONDS)
            );
        }

        // Only one DB call should have happened
        assertEquals(1, dbCallCount.get());

        executor.shutdownNow();
    }

    // Test 2: Subsequent request should use cache
    @Test
    void shouldReturnCachedResponseWithoutDbCall() {

        AtomicInteger dbCallCount = new AtomicInteger(0);

        String firstResponse = coalescer.get(
                "user-101",
                key -> {
                    dbCallCount.incrementAndGet();
                    return "User details";
                }
        );

        String secondResponse = coalescer.get(
                "user-101",
                key -> {
                    dbCallCount.incrementAndGet();
                    return "Another response";
                }
        );

        assertEquals("User details", firstResponse);
        assertEquals("User details", secondResponse);

        assertEquals(1, dbCallCount.get());
    }

    // Test 3: DB failure should propagate exception
    @Test
    void shouldPropagateExceptionWhenDbCallFails() {

        AtomicInteger dbCallCount = new AtomicInteger(0);

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> coalescer.get("user-101", key -> {

                    dbCallCount.incrementAndGet();

                    throw new RuntimeException("Database unavailable");
                })
        );

        assertEquals("Database unavailable", exception.getMessage());
        assertEquals(1, dbCallCount.get());
    }

    // Test 4: New request should retry after DB failure
    @Test
    void shouldRetryDbCallAfterFailure() {

        AtomicInteger dbCallCount = new AtomicInteger(0);

        // First request fails
        assertThrows(
                RuntimeException.class,
                () -> coalescer.get("user-101", key -> {

                    dbCallCount.incrementAndGet();

                    throw new RuntimeException("DB failure");
                })
        );

        // Second request should retry and succeed
        String response = coalescer.get(
                "user-101",
                key -> {
                    dbCallCount.incrementAndGet();
                    return "User details";
                }
        );

        assertEquals("User details", response);
        assertEquals(2, dbCallCount.get());
    }

    // Test 5: Different keys should execute independently
    @Test
    void shouldAllowDbCallsForDifferentKeys() {

        AtomicInteger dbCallCount = new AtomicInteger(0);

        String user1 = coalescer.get(
                "user-101",
                key -> {
                    dbCallCount.incrementAndGet();
                    return "User 101";
                }
        );

        String user2 = coalescer.get(
                "user-102",
                key -> {
                    dbCallCount.incrementAndGet();
                    return "User 102";
                }
        );

        assertEquals("User 101", user1);
        assertEquals("User 102", user2);

        assertEquals(2, dbCallCount.get());
    }
}
