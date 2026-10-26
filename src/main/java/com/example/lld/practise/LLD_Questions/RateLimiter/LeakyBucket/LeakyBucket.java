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
        if (this.queue.remainingCapacity() > 0) {
            this.queue.add(1);
            return true;
        }
        return false;
    }

    public void removeRequest() {
//        while (!queue.isEmpty()) {
//            this.queue.poll();
//        }

        ScheduledExecutorService worker =
                Executors.newSingleThreadScheduledExecutor();

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
