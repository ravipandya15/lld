package com.example.lld.practise.Coding_Questions.LRUWithTTL;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LRUCacheWithTTL {

    public static void main(String[] args) throws InterruptedException {

        DataStore ds = new DataStore(3);

        ds.put(1, "A", 10000);
        ds.put(2, "B", 10000);
        ds.put(3, "C", 10000);

        // LRU order: 1 -> 2 -> 3

        ds.get(1);

        // LRU order: 2 -> 3 -> 1

        ds.put(4, "D", 10000);

        // Key 2 should be evicted

        System.out.println(ds.get(2)); // NO_VALUE
        System.out.println(ds.get(1)); // A
        System.out.println(ds.get(4)); // D

        System.out.println("Cache size: " + ds.size());
    }
}

class Node {

    int key;
    String value;
    long expiryTime;

    Node prev;
    Node next;

    public Node(int key, String value, long expiryTime) {
        this.key = key;
        this.value = value;
        this.expiryTime = expiryTime;
    }
}

class DataStore {

    Map<Integer, Node> map;
    PriorityQueue<Node> minHeap;

    Node head;
    Node tail;

    int capacity;

    public DataStore(int capacity) {

        if (capacity <= 0) {
            throw new IllegalArgumentException(
                    "Capacity must be positive"
            );
        }

        this.capacity = capacity;

        map = new HashMap<>();

        minHeap = new PriorityQueue<>(
                (a, b) -> Long.compare(a.expiryTime, b.expiryTime)
        );

        // Dummy head and tail nodes
        head = new Node(-1, "", -1);
        tail = new Node(-1, "", -1);

        head.next = tail;
        tail.prev = head;
    }

    public synchronized void put(
            int key, String value, long expiry) {

        if (expiry <= 0) {
            System.out.println("Expiry must be positive");
            return;
        }

        cleanupExpiredKeys();

        long currentTime = System.currentTimeMillis();

        // Update existing key
        if (map.containsKey(key)) {

            Node oldNode = map.get(key);

            removeNode(oldNode);
            map.remove(key);
        }

        // Evict LRU if cache is full
        if (map.size() >= capacity) {
            evictLRU();
        }

        Node newNode = new Node(
                key, value, currentTime + expiry
        );

        map.put(key, newNode);

        // Add new node at MRU position
        addToTail(newNode);

        minHeap.add(newNode);
    }

    public synchronized String get(int key) {

        Node node = map.get(key);

        if (node == null) {
            System.out.println("Key is not present");
            return "NO_VALUE";
        }

        long currentTime = System.currentTimeMillis();

        if (node.expiryTime <= currentTime) {

            System.out.println("Key is expired");

            map.remove(key);
            removeNode(node);

            return "NO_VALUE";
        }

        // Move accessed node to MRU position
        removeNode(node);
        addToTail(node);

        return node.value;
    }

    public synchronized boolean delete(int key) {

        Node node = map.remove(key);

        if (node == null) {
            System.out.println("Key is not present");
            return false;
        }

        removeNode(node);

        return true;
    }

    public synchronized int size() {

        cleanupExpiredKeys();

        return map.size();
    }

    // Add node immediately before dummy tail
    private void addToTail(Node node) {

        Node last = tail.prev;

        last.next = node;
        node.prev = last;

        node.next = tail;
        tail.prev = node;
    }

    // Remove a node from doubly linked list
    private void removeNode(Node node) {

        Node previous = node.prev;
        Node next = node.next;

        previous.next = next;
        next.prev = previous;

        node.prev = null;
        node.next = null;
    }

    // Remove least recently used node
    private void evictLRU() {

        Node lruNode = head.next;

        if (lruNode == tail) {
            return;
        }

        System.out.println(
                "Evicting LRU key: " + lruNode.key
        );

        map.remove(lruNode.key);
        removeNode(lruNode);
    }

    // Remove expired entries using Min Heap
    private void cleanupExpiredKeys() {

        long currentTime = System.currentTimeMillis();

        while (!minHeap.isEmpty()
                && minHeap.peek().expiryTime <= currentTime) {

            Node expiredNode = minHeap.poll();

            // Ensure this is still the latest version
            if (map.get(expiredNode.key) == expiredNode) {

                map.remove(expiredNode.key);
                removeNode(expiredNode);

                System.out.println(
                        "Expired key: " + expiredNode.key
                );
            }
        }
    }
}

