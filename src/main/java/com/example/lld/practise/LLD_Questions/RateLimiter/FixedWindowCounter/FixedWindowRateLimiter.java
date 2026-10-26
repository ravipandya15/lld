package com.example.lld.practise.LLD_Questions.RateLimiter.FixedWindowCounter;

import java.util.concurrent.atomic.AtomicInteger;

public class FixedWindowRateLimiter {
    private final int maxRequestsPerWindow;
    private final long windowSizeInMs;

    // Track the start time of the current window and its request count
    private long currentWindowStartTime;
    private final AtomicInteger requestCounter;

    public FixedWindowRateLimiter(int maxRequestsPerWindow, long windowSizeInMs) {
        this.maxRequestsPerWindow = maxRequestsPerWindow;
        this.windowSizeInMs = windowSizeInMs;
        this.currentWindowStartTime = System.currentTimeMillis();
        this.requestCounter = new AtomicInteger(0);
    }

    /**
     * Attempts to acquire a permit. Returns true if allowed, false if rate-limited.
     */
    public synchronized boolean allowRequest() {
        long currentTime = System.currentTimeMillis();

        // Check if we have crossed over into a new time window
        if (currentTime >= windowSizeInMs + currentWindowStartTime) {
            // Reset the window start time and the counter
            currentWindowStartTime = currentTime;
            requestCounter.set(0);
        }

        // Increment and check if it exceeds our threshold
        if (requestCounter.incrementAndGet() <= maxRequestsPerWindow) {
            return true;
        }

        // Over the limit
        return false;
    }

    public static void main(String[] args) throws InterruptedException {
        // Allow 3 requests every 2 seconds
        FixedWindowRateLimiter rateLimiter = new FixedWindowRateLimiter(3, 2000);

        // This is only for a single user.
//        For multi user, create a Hashmap with UserId and FixedWindowRateLimiter
        for (int i = 1; i <= 6; i++) {
            boolean allowed = rateLimiter.allowRequest();
            System.out.println("Request " + i + ": " + (allowed ? "ALLOWED" : "BLOCKED"));
            Thread.sleep(300); // 300ms pause between requests
        }

        System.out.println("\n--- Waiting for the next window to open ---");
        Thread.sleep(1000);

        // This should be allowed now because the 2-second window has reset
        boolean allowedAfterWait = rateLimiter.allowRequest();
        System.out.println("Request 7: " + (allowedAfterWait ? "ALLOWED" : "BLOCKED"));
    }
}