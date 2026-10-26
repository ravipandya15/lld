package com.example.lld.practise.MultiThreading.DeadLock;

public class DeadLock {

    public static void main(String[] args) {
        String printer = "Printer";
        String scanner = "Scanner";

        Object lock1 = new Object();
        Object lock2 = new Object();

        Runnable run1 = () -> {
            synchronized (lock1) { // locking printer
                System.out.println(Thread.currentThread().getName() + " locked " + printer);

                try {
                    Thread.sleep(100);
                } catch (Exception e) {}

                synchronized (lock2) { // locking scanner along with printer
                    System.out.println(Thread.currentThread().getName() + " locked " + scanner);
                }
            }
        };

        Runnable run2 = () -> {
            synchronized (lock2) { // locking scanner
                System.out.println(Thread.currentThread().getName() + " locked " + scanner);

                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                }

                synchronized (lock1) { // locking printer along with scanner
                    System.out.println(Thread.currentThread().getName() + " locked " + printer);
                }
            }
        };

        // To overcome deadlock
//        Runnable run2 = () -> {
//            synchronized (scanner) { // locking scanner
//                System.out.println(Thread.currentThread().getName() + " locked " + scanner);
//
//                try {
//                    Thread.sleep(100);
//                } catch (Exception e) {
//                }
//            }
//
//            synchronized (printer) { // locking printer after releasing scanner
//                System.out.println(Thread.currentThread().getName() + " locked " + printer);
//            }
//        };

        Thread thread1 = new Thread(run1, "Desktop");
        Thread thread2 = new Thread(run2, "Laptop");
        thread1.start();
        thread2.start();
    }
}
