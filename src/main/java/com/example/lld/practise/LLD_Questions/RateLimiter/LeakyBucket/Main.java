package com.example.lld.practise.LLD_Questions.RateLimiter.LeakyBucket;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        int userId = 10011;
        int capacityPerUser = 10;
        int totalRequest = 15;
        UserBucketCreator userBucketCreator = new UserBucketCreator(userId, capacityPerUser);
        ExecutorService executors = Executors.newFixedThreadPool(totalRequest);

        for (int i = 0; i < totalRequest; i++) {
            executors.execute(() -> userBucketCreator.accessApplication(userId));
        }


        try {
            Thread.sleep(1000);
            userBucketCreator.processRequests(userId);
            Thread.sleep(2000);
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
