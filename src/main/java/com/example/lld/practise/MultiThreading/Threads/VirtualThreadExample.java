package com.example.lld.practise.MultiThreading.Threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VirtualThreadExample {
    static void main(String[] args) {
        Thread.startVirtualThread(() -> System.out.println("Hello World"));

        // Virtual Thread with Executor Service


        /***
        * This code can create 10 virtual threads — one for each submitted task.
         */
        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {

            for (int i = 0; i < 10; i++) {
                executorService.submit(() -> System.out.println("Calling from Executor Service"));
//                executorService.execute(() -> System.out.println("Calling from Executor Service"));
            }

//            Submit vs execute
//            execute take Runnable and return void, submit take runnable but return Future<?>
        }

    }
}
