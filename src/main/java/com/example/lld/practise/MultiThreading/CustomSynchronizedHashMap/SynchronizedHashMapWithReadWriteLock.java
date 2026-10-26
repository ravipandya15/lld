package com.example.lld.practise.MultiThreading.CustomSynchronizedHashMap;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SynchronizedHashMapWithReadWriteLock {

    Map<String, String> synchronizedHashMap;
    ReadWriteLock lock;
    Lock readLock;
    Lock writeLock;

    public SynchronizedHashMapWithReadWriteLock() {
        this.synchronizedHashMap = new HashMap<>();
        this.lock = new ReentrantReadWriteLock();
        readLock = lock.readLock();
        writeLock = lock.writeLock();
    }

    public void put(String key, String value) {
        try {
            writeLock.lock();
            synchronizedHashMap.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    public void remove(String key) {
        try {
            writeLock.lock();
            synchronizedHashMap.remove(key);
        } finally {
             writeLock.unlock();
        }
    }

    public String get(String key) {
        try {
            readLock.lock();
            if (synchronizedHashMap.containsKey(key)) {
                return synchronizedHashMap.get(key);
            }
        } finally {
            readLock.unlock();
        }

        return null;
    }
}
