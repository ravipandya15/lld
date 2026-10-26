package com.example.lld.practise.MultiThreading.Custom_ConcurrentHashMap;
import java.util.Objects;

class CustomConcurrentHashMap<K, V> {

    private static class Node<K, V> { // child class can be static and can have constructor as well
        final int hash;
        final K key;
        volatile V value;
        volatile Node<K, V> next;

        Node(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }
    }

    private final Node<K, V>[] table;
    private final Object[] locks;
    private final int capacity;

    @SuppressWarnings("unchecked")
    public CustomConcurrentHashMap(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }

        this.capacity = capacity;
        this.table = (Node<K, V>[]) new Node[capacity];
        this.locks = new Object[capacity];

        for (int i = 0; i < capacity; i++) {
            locks[i] = new Object();
        }
    }

    private int index(Object key) {
        int hash = key.hashCode();
        hash ^= hash >>> 16;
        return (hash & 0x7fffffff) % capacity;
    }

    public void put(K key, V value) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(value);

        int i = index(key);

        synchronized (locks[i]) {
            Node<K, V> current = table[i];

            while (current != null) {
                if (current.key.equals(key)) {
                    current.value = value;
                    return;
                }
                current = current.next;
            }

            Node<K, V> node = new Node<>(i, key, value);
            node.next = table[i];
            table[i] = node;
        }
    }

    public V get(K key) {
        Objects.requireNonNull(key);

        int i = index(key);

        synchronized (locks[i]) {
            Node<K, V> current = table[i];

            while (current != null) {
                if (current.key.equals(key)) {
                    return current.value;
                }
                current = current.next;
            }

            return null;
        }
    }

    public V remove(K key) {
        Objects.requireNonNull(key);

        int i = index(key);

        synchronized (locks[i]) {
            Node<K, V> current = table[i];
            Node<K, V> previous = null;

            while (current != null) {
                if (current.key.equals(key)) {
                    if (previous == null) {
                        table[i] = current.next;
                    } else {
                        previous.next = current.next;
                    }

                    return current.value;
                }

                previous = current;
                current = current.next;
            }

            return null;
        }
    }
}


public class ConcurrentHashMap {
    static void main() {
        CustomConcurrentHashMap<String, Integer> map =
                new CustomConcurrentHashMap<>(16);

        // Test 1: Put and Get
        map.put("Apple", 10);
        map.put("Banana", 20);

        System.out.println(map.get("Apple"));  // Expected: 10
        System.out.println(map.get("Banana")); // Expected: 20

        // Test 2: Get non-existing key
        System.out.println(map.get("Orange")); // Expected: null

        // Test 3: Update existing key
        map.put("Apple", 100);
        System.out.println(map.get("Apple"));  // Expected: 100

        // Test 4: Remove existing key
        System.out.println(map.remove("Apple")); // Expected: 100
        System.out.println(map.get("Apple"));    // Expected: null

        // Test 5: Remove non-existing key
        System.out.println(map.remove("Orange")); // Expected: null

        // Test 6: Verify other keys remain unaffected
        System.out.println(map.get("Banana")); // Expected: 20

        // Test 7: Multiple entries
        map.put("Mango", 30);
        map.put("Grapes", 40);

        System.out.println(map.get("Mango"));  // Expected: 30
        System.out.println(map.get("Grapes")); // Expected: 40
    }
}
