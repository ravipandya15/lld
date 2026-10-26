package com.example.lld.practise.MultiThreading.Threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorExample {

    static void main() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Executors.newCachedThreadPool();
        Executors.newSingleThreadExecutor();

        Executors.newVirtualThreadPerTaskExecutor();
    }
}
