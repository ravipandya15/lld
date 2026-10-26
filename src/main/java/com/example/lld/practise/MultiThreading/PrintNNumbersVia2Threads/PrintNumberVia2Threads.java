package com.example.lld.practise.MultiThreading.PrintNNumbersVia2Threads;

public class PrintNumberVia2Threads {

    private final int maxValue;
    private final Object lock;
    private int value;
    public PrintNumberVia2Threads(int maxValue) {
        this.maxValue = maxValue;
        this.lock = new Object();
        this.value = 1;
    }

    public void printNumbers() {

        Thread t1 = new Thread(() -> printOddNumbers(), "OddThread");

        Thread t2 = new Thread(() -> printEvenNumbers(), "EvenThread");

        t1.start();
        t2.start();
    }

    private void printOddNumbers() {

        while (value <= maxValue) {
            synchronized (lock) {
                if (value % 2 == 0) { // do not print even number
                    try {
                        lock.wait(); // here before wait, it will release the lock
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + ": " + value);
                value++;
                lock.notifyAll();
            }
        }
    }

    private void printEvenNumbers() {
        while (value <= maxValue) {
            synchronized (lock) {
                if (value % 2 != 0) { // do not print even numbers
                    try {
                        lock.wait(); // here before wait, it will release the lock
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println(Thread.currentThread().getName() + ": " + value);
                value++;
                lock.notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        PrintNumberVia2Threads printNumber = new PrintNumberVia2Threads(50);
        printNumber.printNumbers();
    }
}
