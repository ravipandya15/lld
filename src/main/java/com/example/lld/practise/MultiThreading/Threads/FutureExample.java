package com.example.lld.practise.MultiThreading.Threads;

import java.util.concurrent.*;

public class FutureExample {

    static void main() throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(5);

        Future<Integer> future = service.submit(() -> {
            Thread.sleep(100);
            return 10;
        });

        System.out.println("Do some other work");

        Integer result = future.get();
        System.out.println(result);



//        =================================================================
        System.out.println("CompletableFutute Example");

        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
                                        return 10;
                                    });

        completableFuture.thenApply(res -> res * 2)
                .thenAccept(res -> System.out.println(res));

        /*
        Combining multiple async cals
        CompletableFuture<User> userFuture =
        CompletableFuture.supplyAsync(() -> getUser());

    CompletableFuture<List<Order>> orderFuture =
            CompletableFuture.supplyAsync(() -> getOrders());

    CompletableFuture<String> result =
            userFuture.thenCombine(
                    orderFuture,
                    (user, orders) ->
                            user.getName() + " has " + orders.size() + " orders"
        );
         */

        /*
            CompletableFuture
                .supplyAsync(() -> {
                    throw new RuntimeException("Something failed");
                })
                .exceptionally(ex -> {
                    System.out.println("Error: " + ex.getMessage());
                    return 0;
                });
         */
    }
}
