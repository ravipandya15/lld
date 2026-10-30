package com.example.lld.practise.MultiThreading.requestCoalescing;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
    Also known as SingleFlight Pattern (aka Request Coalescing)
 */
public class RequestCoalescer<K, V> {
    // store completed responses
    private final Map<K,V> cache = new ConcurrentHashMap<>();

    // store request being currently executed
    private final Map<K, CompletableFuture<V>> inFlightRequests = new ConcurrentHashMap<>();

    public V get(K key, Function<K,V> dbLoader) {

        V cachedValue = cache.get(key);
        if (cachedValue != null) {
            System.out.println(Thread.currentThread().getName() + "- CACHE HIT");
            return cachedValue;
        }

        CompletableFuture<V> future = new CompletableFuture<>();

        CompletableFuture<V> existingFuture = inFlightRequests.putIfAbsent(key, future);
        if (existingFuture != null) {
            System.out.println(Thread.currentThread().getName() + "- Waiting for existing request");
            return existingFuture.join();
        }

        // It's a first request so execute the DB call
        try {

            cachedValue = cache.get(key);

            if (cachedValue != null) {
                future.complete(cachedValue);
                return cachedValue;
            }

            System.out.println(Thread.currentThread().getName() + "- Executing DB query");

            V value = dbLoader.apply(key);
            cache.put(key, value);
            future.complete(value);
            return value;
        } catch (RuntimeException | Error ex) {
            future.completeExceptionally(ex);
            throw ex;
        } finally {
            inFlightRequests.remove(key, future);
        }
    }
}
