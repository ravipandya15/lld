package com.example.lld.practise.LLD_Questions.RateLimiter.TokenBucket;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        int userId = 10011;
        int capacityPerUser = 10;
        int totalUsers = 15;
        int refreshRate = 10;
        TokenBucketCreator userBucketCreator = new TokenBucketCreator(capacityPerUser, refreshRate);
        ExecutorService executors = Executors.newFixedThreadPool(totalUsers);

        for (int i = 0; i < totalUsers; i++) {
            executors.execute(() -> userBucketCreator.accessApplication(userId));
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("===========================================================");
        for (int i = 0; i < 12; i++) {
            executors.execute(() -> userBucketCreator.accessApplication(userId));
        }

        executors.shutdown();
    }
}
