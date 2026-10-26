package com.example.lld.practise.MultiThreading.Custom_BlockingQueue;

public class Main {
    public static void main(String[] args) {
        BlockingQueue blockingQueue = new BlockingQueue(5);

        // TODO: run in multiple threads

        Thread t1 = new Thread( () -> {
            blockingQueue.pop();
            blockingQueue.pop();
            blockingQueue.pop();
            blockingQueue.pop();
        }, "Thread1");
        Thread t2 = new Thread( () -> {
            blockingQueue.push(1);
            blockingQueue.push(2);
            blockingQueue.push(3);
            blockingQueue.push(4);
            blockingQueue.push(5);
            blockingQueue.push(6);
        }, "Thread2");

        t1.start();
        t2.start();
    }
}
