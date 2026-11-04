package com.example.lld.practise.LLD_Questions.RateLimiter.LeakyBucket;

import com.example.lld.practise.LLD_Questions.RateLimiter.RateLimiter;

import java.util.concurrent.*;

public class LeakyBucket implements RateLimiter {

    private final BlockingQueue<Integer> queue;

    public LeakyBucket(int capacity) {
        this.queue = new LinkedBlockingQueue<>(capacity); // LinkedBlockingQueue is thread-safe and designed for concurrent access by multiple threads.
    } // actually LinkedBlokingDeque.


    @Override
    public boolean grantAccess() {// here if requirement is like, per second 5 request should be allowed and not more than that, so
        // should have LinkedBlockingQueue<>(5);
        // refreshCapacity() method, where I will compare currentTime with lastUpdatedTime. If it's > 0 then re initialize capacity as 5 again. otherwise BAU.
        // I think refresh bucket should not be part of leaky bucket algorithm.
//        if (this.queue.remainingCapacity() > 0) { // this is not atomic operation, it's standard check then act.
//            this.queue.add(1);
//            return true;
//        }

        // use offer - as it's atomic and internally performs
//        Is there capacity?
//    ↓
//        If yes → insert element
//        If no  → return false
        return this.queue.offer(1); // offer threadId
    }

    public void removeRequest() {
//        while (!queue.isEmpty()) {
//            this.queue.poll();
//        }

        ScheduledExecutorService worker =
                Executors.newSingleThreadScheduledExecutor();

//        ScheduledExecutorService scheduler =
//                Executors.newScheduledThreadPool(1);

        // Remove one request every 500 milliseconds
        worker.scheduleAtFixedRate(() -> {
            if (!queue.isEmpty()) {
                int request = queue.poll();

                System.out.println(
                        "Processing " + request +
                                " th request at " + System.currentTimeMillis()
                );

                // Forward the request to the downstream service here.
            }
        }, 0, 500, TimeUnit.MILLISECONDS);
    }
}
