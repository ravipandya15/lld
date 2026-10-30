package com.example.lld.practise.Coding_Questions;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryDataStoreWithTTL {

    // Asked in Goldman sachs

    static void main() {
        System.out.println("Implementing In memory Cache with TTL");
        DataStore ds = new DataStore(3);
    }
}

class Data {
    int key;
    String value;
    long expiryTime;

    public Data(int key, String value, long expiryTime) {
        this.key = key;
        this.value = value;
        this.expiryTime = expiryTime;
    }
}

class DataStore {
    Map<Integer, Data> map;
    PriorityQueue<Data> minHeap;
    int capacity;

    public DataStore(int capacity) {
        if (capacity <= 0) {
            System.out.println("Data Store size should not be 0");
            throw new RuntimeException("Data Store size should not be 0");
        }
        this.map = new ConcurrentHashMap<>();
        this.minHeap = new PriorityQueue<>((a, b) -> Long.compare(a.expiryTime, b.expiryTime));
        this.capacity = capacity;
    }

    public synchronized void put(int key, String value, long expiry) {
        if (expiry <= 0) {
            System.out.println("expiry must be positive");
            return;
        }
        long currentTime = System.currentTimeMillis();
        if (map.containsKey(key)) {
            Data updatedData = new Data(key, value, currentTime + expiry);
            map.put(key, updatedData);
            minHeap.add(updatedData);
        } else {
            // check if cache is full or not
            cleanupExpiredKeys();
            if (map.size() == capacity) {
                System.out.println("Cache is already full, can't insert new value");
            } else {
                Data newData = new Data(key, value, currentTime + expiry);
                map.put(key, newData);
                minHeap.add(newData);
            }
        }
    }

    public synchronized String get(int key) {
        if (!map.containsKey(key)) {
            System.out.println("Key is not present");
            return "NO_VALUE";
        }
        long currentTime = System.currentTimeMillis();
        if (map.get(key).expiryTime <= currentTime) {
            System.out.println("Key is expired");
            map.remove(key);
            return "NO_VALUE";
        }
        return map.get(key).value;
    }

    public synchronized int size() {
        cleanupExpiredKeys();
        return map.size();
    }

    public synchronized boolean delete(int key) {
        if (!map.containsKey(key)) {
            System.out.println("Key is not present");
            return false;
        }
        map.remove(key);
        return true;
    }

    private synchronized void cleanupExpiredKeys() {
        long currentTime = System.currentTimeMillis();
        while (!minHeap.isEmpty() && minHeap.peek().expiryTime <= currentTime) {
            int expiredKey = minHeap.peek().key;
            if (map.containsKey(expiredKey) && map.get(expiredKey).expiryTime <= currentTime) {
                map.remove(expiredKey);
            }

            //or can also remove when expiryTime is matched with heap
            if (map.containsKey(expiredKey) && map.get(expiredKey).expiryTime == minHeap.peek().expiryTime) {
                map.remove(expiredKey);
            }
            minHeap.poll();
        }
    }
}
