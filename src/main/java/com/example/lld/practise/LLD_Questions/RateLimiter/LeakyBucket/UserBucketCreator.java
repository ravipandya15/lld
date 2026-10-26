package com.example.lld.practise.LLD_Questions.RateLimiter.LeakyBucket;

import java.util.HashMap;
import java.util.Map;

//IGNORE USER BUCKET CREATOR IN REAL INTERVIEW -> if per user throttling is not required.
public class UserBucketCreator {

    // check that can we create this bucket as Static? -> so it can be a single map per instance,
    // it we want to scale it for multiple instance(server) then we can use redis or Db and get Data from it instead of local map.
    Map<Integer, LeakyBucket> bucket;

    public UserBucketCreator(int userId, int capacity) {
        bucket = new HashMap<>();
        bucket.put(userId, new LeakyBucket(capacity));
        // this is one to one mapping of user and bucket.
//        I should have one to many mapping. 1 bucket and many user.
        // addUser(int user, int capacity) method and in constructor I will only initialize HashMap. line no: 14, line no 15 will be part of addUser method
    }

    public void accessApplication(int userId) {
        if (bucket.containsKey(userId) && bucket.get(userId).grantAccess()) {
            System.out.println(Thread.currentThread().getName() + " --> able to access the application for userId: " + userId);
        } else {
            System.out.println(Thread.currentThread().getName() + " --> Too Many request, Request Throtteled for userId: " + userId + " try after some time");
        }
    }

    public void processRequests(int userId) {
        bucket.get(userId).removeRequest();
    }
}
