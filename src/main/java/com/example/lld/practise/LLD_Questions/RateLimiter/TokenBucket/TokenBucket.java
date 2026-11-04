package com.example.lld.practise.LLD_Questions.RateLimiter.TokenBucket;

import com.example.lld.practise.LLD_Questions.RateLimiter.RateLimiter;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class TokenBucket implements RateLimiter {

    private final int bucketCapacity;
    private final int refreshRate; // per second this many new tokens should be added.

    private final AtomicInteger currentCapacity;
    private final AtomicLong lastUpdatedTime;

    public TokenBucket(int bucketCapacity, int refreshRate) {
        if (bucketCapacity <= 0 || refreshRate <= 0) {
            throw new IllegalArgumentException(
                    "Capacity and refresh rate must be positive");
        }

        this.bucketCapacity = bucketCapacity;
        this.refreshRate = refreshRate;
        currentCapacity = new AtomicInteger(bucketCapacity);
        lastUpdatedTime = new AtomicLong(System.currentTimeMillis());
    }

//    @Override
//    public synchronized boolean grantAccess() {
//        refreshBucket();
//
//        if (currentCapacity <= 0) {
//            return false;
//        }
//
//        currentCapacity--;
//        return true;
//    }

    @Override
    public boolean grantAccess() {
        refreshBucket(); // synchronized
        int previous = currentCapacity.getAndUpdate(
                tokens -> tokens > 0 ? tokens - 1 : 0
        ); // it will return old value and not the new value so count = 1 still works as previous > 0 and then it will decrement it to 0.

        return previous > 0;
    }
//    @Override
//    public  boolean grantAccess() { // instead of compareAndSet can use synchronized method as well for simplicity.
//        refreshBucket();
////        if (currentCapacity.get() > 0) {
////            currentCapacity.decrementAndGet();
////        }
//        int current = currentCapacity.get();
//        if (current <= 0) return false;
//        if (currentCapacity.compareAndSet(current, current - 1)) {
//            return true;
//        }
//        return false;
//    }

    // check that this should be synchronized or not, as multiple threads will try to use it, check Atomic Integer will be threadSafe for multiThreaded environment?
    // Yes AtomicInteger is threadsafe, so no worries in multi threaded environment as well.
    private synchronized void refreshBucket() {
        long currentTime = System.currentTimeMillis();
//        System.nanoTime() - use System.nanoTime()

//        long now = System.nanoTime();
//        long elapsedTime = now - lastRefillTimestamp;
//
//        // Convert elapsed time from nanoseconds to seconds
//        double elapsedTimeInSeconds = (double) elapsedTime / TimeUnit.SECONDS.toNanos(1);


        int timeDifference = (int) ((currentTime - lastUpdatedTime.get()) / 1000);
        int additionalTokens;
        if (timeDifference > 0) {
            additionalTokens = timeDifference * refreshRate;
            int refreshedCapacity = Math.min(currentCapacity.get() + additionalTokens, bucketCapacity);
            currentCapacity.getAndSet(refreshedCapacity);
            System.out.println("Setting refreshed capacity: " + refreshedCapacity + "by thread: " + Thread.currentThread().getName());
//            currentCapacity.set(refreshedCapacity);
            lastUpdatedTime.addAndGet(timeDifference * 1000L);
            lastUpdatedTime.getAndSet(currentTime);
        }
    }
}
