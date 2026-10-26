package com.example.lld.practise.MultiThreading.ConcurrencyDataStructures;

import java.util.*;
import java.util.concurrent.*;

public class DataStructures {
    public static void main(String[] args) {

        // For Map
        // 1. SynchronizedMap -> Applies lock on entire map for every read and write operation
        Map<Integer, String> map = new HashMap<>();
        Map<Integer, String> synchMap = Collections.synchronizedMap(map);

        // 2. ConcurrentHashMap -> best in concurrency -> any no. of threads can read and at max 16 threads can perform write operation - 16 is not fixed, as it depends on bucket count. Also for empty bucket, Java 8 uses CAS so no locking is required.
        // it applies the lock on segment (like on individual key/bucket) and not on entire map
        Map<Integer, String> concurrentMap = new ConcurrentHashMap<>();


        // For List
        // 1. Vector -> by default synchronized - but legacy
        Vector<Integer> vector = new Vector<>();

        // 2 Synchronized List -> entire lock on list for each read and write operation
        // can used for heavy write operation
        List<Integer> syncList = Collections.synchronizedList(List.of(1, 2, 3, 4, 5));

        // 3 CopyOnWriteArrayList -> can be used for read heavy and low write condition
        // 10,000 read/second and 10 write/second
        // for write operation, it will create a new array (Copy) and after write, will update the actual list
        List<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();

        // 4. ConcurrentLinkedQueue
        // if we don't need to fetch data from a specific index, then this is best
        Queue<Integer> concurrentQueue = new ConcurrentLinkedQueue<>();

        // 5. Thread safe priorityQueue
        PriorityBlockingQueue<Integer> minHeap =
                new PriorityBlockingQueue<>(
                        11,
                        (a, b) -> Long.compare(a, b)
                );

//        Sorted concurrent HashMap
        ConcurrentNavigableMap<Integer, String> concurrentNavigableMap = new ConcurrentSkipListMap<>();
        concurrentNavigableMap.put(10, "Ravi");
        concurrentNavigableMap.put(5, "Raj");
        concurrentNavigableMap.put(15, "Keval");
        concurrentNavigableMap.put(20, "Keval");

        System.out.println(concurrentNavigableMap);

        System.out.println(concurrentNavigableMap.floorKey(12));
        System.out.println(concurrentNavigableMap.ceilingKey(12));

        List<Integer> unmodifiableList = Collections.unmodifiableList(List.of(1,2,3,4,5));
        try {
            unmodifiableList.add(10);
        } catch (UnsupportedOperationException ex) {
            System.out.println("Can't update unmodifiable list");
        }


        // computeIfAbsent

        Map<Character, Integer> map1 = new HashMap<>();
        String str = "aabbcccc";
        for (char c : str.toCharArray()) {
            map1.computeIfAbsent(c, k -> 0);
            map1.put(c, map1.get(c) + 1);
        }

        Map<String, List<String>> map2 = new HashMap<>();

        // computeIfAbsent -> best in this case
        map2.computeIfAbsent("Java", k -> new ArrayList<>())
                .add("Spring Boot");

        // compute
        for (char c : str.toCharArray()) {
            map1.compute(c, (key, value) -> value == null ? 1 : value + 1);
        }

        // merge -> best in this case -> instead of getOrDefault
        for (char c : str.toCharArray()) {
            map1.merge(c, 1, Integer::sum);

            map1.merge(c, 1,(a, b) -> a * b);
        }
    }

    /**
    ConcurrentHasMap internally uses CAS (Compare and Set)
     CAS(table, index, null, newNode);

     Only 1 thread will be able to add newNode in empty bucket, rest all threads will retry. This is how
     ConcurrentHashMap insert a node in EMPTY bucket without locking the bucket.
     If bucket is not empty, ConcurrentHasMap aquires a lock on that bucket to avoid concurrency issue.
     If thread1 write the same bucket and thread2 read from that bucket, thread2 can either read old or new value based on timing and ordering subject to
     CPU and Java Memory Model
     For read operation it uses volatile/atomic internally
     */
    private static void operationsInConcurrentHashMap() {

        /*

         */
        // for a normal and concurrent HashMap
        // before
        HashMap<String, Integer> map = new HashMap<>();
        map.put("a", map.getOrDefault("a", 0) + 1);

        // after
        map.putIfAbsent("a", 0);
        map.put("a", map.get("a") + 1);

        // better
        map.computeIfAbsent("a", key -> 0);
        map.put("a", map.get("a") + 1);

        // in single line
        map.compute("b", (key, value) -> value == null ? 1 : value + 1);

        // Best
        map.merge("a", 1, Integer::sum); // instead of 1 -> I can pass any custom value.

        Map<Integer, List<String>> map2 = new ConcurrentHashMap<>();
        map2.computeIfAbsent(1, k -> new CopyOnWriteArrayList<>()).add("Ravi"); // proper thread safety in concurrent applications.

        // VVIMP: This works because concurrentHashMap does not allow null values, therefor a null result from get means key is absent.
        if (map.get("a") != null) {
            // Key exists
        }
    }
}
