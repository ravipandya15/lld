package com.example.lld.practise.MultiThreading.Custom_BlockingStack;

public class Main {
    public static void main(String[] args) {
        BlockingStack blockingStack = new BlockingStack(5);

        Thread t1 = new Thread(() -> {
            blockingStack.push(1);
            blockingStack.push(2);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            blockingStack.push(3);
            blockingStack.push(4);
            blockingStack.push(5);
            blockingStack.push(6);
        }, "Thread1");
        Thread t2 = new Thread(() -> {
            blockingStack.pop();
            blockingStack.pop();
            blockingStack.pop();
            blockingStack.pop();
        }, "Thread2");

        t1.start();
        t2.start();
    }
}
