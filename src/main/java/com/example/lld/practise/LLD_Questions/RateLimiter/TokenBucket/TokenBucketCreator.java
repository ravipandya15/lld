package com.example.lld.practise.LLD_Questions.RateLimiter.TokenBucket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//IGNORE TOKEN BUCKET CREATOR IN REAL INTERVIEW -> if per user throttling is not required.
public class TokenBucketCreator {

    // check that can we create this bucket as Static? -> so it can be a single map per instance,
    // it we want to scale it for multiple instance then we can use redis or Db and get Data from it instead of local map.
    Map<Integer, TokenBucket> buckets;
    private final int capacity;
    private final int refreshRate;

    public TokenBucketCreator(int capacity, int refreshRate) {
        this.capacity = capacity;
        this.refreshRate = refreshRate;
        buckets = new ConcurrentHashMap<>();
//        bucket.put(userId, new TokenBucket(capacity, refreshRate));
    }

    public void accessApplication(int userId) {
        TokenBucket bucket = buckets.computeIfAbsent(userId, k -> new TokenBucket(this.capacity, this.refreshRate));
        if (bucket.grantAccess()) {
            System.out.println(Thread.currentThread().getName() + " --> able to access the application for userId: " + userId);
        } else {
            System.out.println(Thread.currentThread().getName() + " --> Too Many request, Request Throtteled for userId: " + userId + " try after some time");
        }
    }
}
