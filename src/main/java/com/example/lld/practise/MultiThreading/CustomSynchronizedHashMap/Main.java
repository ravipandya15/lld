package com.example.lld.practise.MultiThreading.CustomSynchronizedHashMap;

public class Main {
    public static void main(String[] args) {
        SynchronizedHashMapWithReadWriteLock synchronizedHashMap = new SynchronizedHashMapWithReadWriteLock();

        // TODO: do it in multiple threads
        Thread t1 = new Thread(() -> {
            synchronizedHashMap.put("Ravi", "Pandya");
            synchronizedHashMap.put("Raj", "Pandey");
            synchronizedHashMap.put("Keyur", "Bhatt");
        }, "Thread1");

        Thread t2 = new Thread( () -> {
            synchronizedHashMap.put("Ravi", "Vyas");
            synchronizedHashMap.put("Raj", "Charkhawala");
            synchronizedHashMap.remove("Keyur");
        }, "Thread2");

        t1.start();
        t2.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(synchronizedHashMap.get("Ravi"));
        System.out.println(synchronizedHashMap.get("Raj"));
        System.out.println(synchronizedHashMap.get("Keyur"));
    }
}
