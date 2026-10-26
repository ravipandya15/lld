package com.example.lld.practise.LLD_Questions.Consistent_Hashing;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHashRouter<T> {

    // The sorted map representing the Hash Ring
    private final SortedMap<Long, T> ring = new TreeMap<>();
    private final int numberOfReplicas; // Number of virtual nodes per physical node
    private final MessageDigest md5Digest;

    public ConsistentHashRouter(int numberOfReplicas, Collection<T> nodes) {
        this.numberOfReplicas = numberOfReplicas;
        try {
            this.md5Digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not found", e);
        }

        // Initialize the ring with the provided starting nodes
        for (T node : nodes) {
            addNode(node);
        }
    }

    /**
     * Adds a physical node to the ring by creating its virtual replicas.
     */
    public synchronized void addNode(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            // Unique identifier for each virtual node
            String vNodeKey = node.toString() + "-vnode-" + i;
            long hash = generateHash(vNodeKey);
            ring.put(hash, node);
        }
    }

    /**
     * Removes a physical node and all its virtual replicas from the ring.
     */
    public synchronized void removeNode(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            String vNodeKey = node.toString() + "-vnode-" + i;
            long hash = generateHash(vNodeKey);
            ring.remove(hash);
        }
    }

    /**
     * Routes a data key to its designated node by moving clockwise along the ring.
     */
    public T routeRequest(String key) {
        if (ring.isEmpty()) {
            return null;
        }

        long hash = generateHash(key);

        // If the hash matches exactly or there are nodes further clockwise
        if (!ring.containsKey(hash)) {
            // Get the portion of the ring that comes after this hash
            SortedMap<Long, T> tailMap = ring.tailMap(hash);
            // If tailMap is empty, wrap around to the beginning of the ring (firstKey)
            hash = tailMap.isEmpty() ? ring.firstKey() : tailMap.firstKey();
        }

        return ring.get(hash);
    }

    /**
     * Helper to generate a 32-bit hash value using MD5.
     */
    private long generateHash(String key) {
        md5Digest.reset();
        byte[] digest = md5Digest.digest(key.getBytes(StandardCharsets.UTF_8));

        // Truncating MD5 16-byte result to a 32-bit long integer for simpler ring positions
        return ((long) (digest[3] & 0xFF) << 24) |
                ((long) (digest[2] & 0xFF) << 16) |
                ((long) (digest[1] & 0xFF) << 8)  |
                ((long) (digest[0] & 0xFF));
    }

    // --- Demo execution ---
    public static void main(String[] args) {
        java.util.List<String> servers = java.util.Arrays.asList("Server_A", "Server_B", "Server_C");

        // Create router with 100 virtual nodes per server for optimal distribution
        ConsistentHashRouter<String> router = new ConsistentHashRouter<>(100, servers);

        System.out.println("--- Initial Routing ---");
        String[] keys = {"user_101", "user_202", "image_999", "session_abc", "video_777"};
        for (String key : keys) {
            System.out.println("Key [" + key + "] maps to -> " + router.routeRequest(key));
        }

        System.out.println("\n--- Server_B Fails / Extracted ---");
        router.removeNode("Server_B");

        for (String key : keys) {
            System.out.println("Key [" + key + "] maps to -> " + router.routeRequest(key));
        }
    }
}