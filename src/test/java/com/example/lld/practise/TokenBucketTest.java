package com.example.lld.practise;

import com.example.lld.practise.LLD_Questions.RateLimiter.TokenBucket.TokenBucket;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;


public class TokenBucketTest {
    @Test
    void shouldAllowRequestsUpToBucketCapacity() {
        TokenBucket bucket = new TokenBucket(3, 1);

        assertTrue(bucket.grantAccess());
        assertTrue(bucket.grantAccess());
        assertTrue(bucket.grantAccess());

        assertFalse(bucket.grantAccess());
    }

    @Test
    void shouldRejectRequestWhenBucketIsEmpty() {
        TokenBucket bucket = new TokenBucket(1, 1);

        assertTrue(bucket.grantAccess());
        assertFalse(bucket.grantAccess());
        assertFalse(bucket.grantAccess());
    }

    @Test
    void shouldRefillTokensAfterElapsedTime()
            throws InterruptedException {

        TokenBucket bucket = new TokenBucket(2, 2);

        // Consume both initial tokens.
        assertTrue(bucket.grantAccess());
        assertTrue(bucket.grantAccess());
        assertFalse(bucket.grantAccess());

        // Two tokens per second means one token every 500 ms.
        Thread.sleep(600);

        assertTrue(bucket.grantAccess());
        assertFalse(bucket.grantAccess());
    }

    @Test
    void shouldNeverExceedBucketCapacity()
            throws InterruptedException {

        TokenBucket bucket = new TokenBucket(3, 10);

        // Consume all initial tokens.
        assertTrue(bucket.grantAccess());
        assertTrue(bucket.grantAccess());
        assertTrue(bucket.grantAccess());

        // Allow enough time for more than three tokens to refill.
        Thread.sleep(600);

        // The bucket must still hold at most three tokens.
        assertTrue(bucket.grantAccess());
        assertTrue(bucket.grantAccess());
        assertTrue(bucket.grantAccess());

        assertFalse(bucket.grantAccess());
    }

    @Test
    void shouldAllowExactlyInitialCapacityUnderConcurrency()
            throws InterruptedException {

        int capacity = 100;
        int requestCount = 500;

        long testStart = System.nanoTime();

        TokenBucket bucket = new TokenBucket(capacity, 1);

        ExecutorService executor =
                Executors.newFixedThreadPool(32);

        CountDownLatch ready = new CountDownLatch(requestCount);
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch done = new CountDownLatch(requestCount);

        AtomicInteger allowed = new AtomicInteger();
        AtomicInteger rejected = new AtomicInteger();

        try {
            for (int i = 0; i < requestCount; i++) {
                executor.submit(() -> {
                    ready.countDown();

                    try {
                        start.await();

                        if (bucket.grantAccess()) {
                            allowed.incrementAndGet();
                        } else {
                            rejected.incrementAndGet();
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } finally {
                        done.countDown();
                    }
                });
            }

            assertTrue(
                    ready.await(5, TimeUnit.SECONDS),
                    "Workers did not become ready in time");

            start.countDown();

            assertTrue(
                    done.await(10, TimeUnit.SECONDS),
                    "Requests did not complete in time");

            long elapsedNanos = System.nanoTime() - testStart;

            // A refill may occur if the test takes over one second.
            long maximumPossibleTokens =
                    capacity + elapsedNanos / 1_000_000_000L + 1;

            assertEquals(
                    requestCount,
                    allowed.get() + rejected.get());

            assertTrue(
                    allowed.get() >= capacity,
                    "The initial tokens should be consumed");

            assertTrue(
                    allowed.get() <= maximumPossibleTokens,
                    "More requests succeeded than the refill rate permits");

        } finally {
            executor.shutdownNow();
            assertTrue(
                    executor.awaitTermination(5, TimeUnit.SECONDS),
                    "Executor did not terminate");
        }
    }

    @Test
    void shouldSupportConcurrentRequestsWithoutExceptions()
            throws InterruptedException {

        TokenBucket bucket = new TokenBucket(100, 50);

        int threadCount = 20;
        int requestsPerThread = 100;

        ExecutorService executor =
                Executors.newFixedThreadPool(threadCount);

        CountDownLatch done =
                new CountDownLatch(threadCount);

        AtomicInteger completed = new AtomicInteger();
        AtomicInteger allowed = new AtomicInteger();

        try {
            for (int i = 0; i < threadCount; i++) {
                executor.submit(() -> {
                    try {
                        for (int j = 0; j < requestsPerThread; j++) {
                            if (bucket.grantAccess()) {
                                allowed.incrementAndGet();
                            }

                            completed.incrementAndGet();
                        }
                    } finally {
                        done.countDown();
                    }
                });
            }

            assertTrue(done.await(15, TimeUnit.SECONDS));

            assertEquals(
                    threadCount * requestsPerThread,
                    completed.get());

            assertTrue(allowed.get() >= 0);
            assertTrue(allowed.get() <= completed.get());

        } finally {
            executor.shutdownNow();
            assertTrue(
                    executor.awaitTermination(5, TimeUnit.SECONDS));
        }
    }
}
