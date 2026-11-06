package com.example.lld.practise.Coding_Questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Edge {
    int src, dest, weight;

    Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}

/**
 Algorithm	    Directed	Undirected	Negative edges
 Dijkstra	        ✅	        ✅	        ❌
 Bellman–Ford	    ✅	        ✅	        ✅
 Floyd–Warshall	    ✅	        ✅	        ✅
 */
public class Bellmen_Ford_Algorithm {


    static void addUndirectedEdge(
            List<Edge> edges, int u, int v, int weight) {

        edges.add(new Edge(u, v, weight));
        edges.add(new Edge(v, u, weight));
    }

    // TC -> O(V * E)
    // SC -> O(V)
    static long[] bellmanFord(
            int vertices, List<Edge> edges, int source) {

        long INF = Long.MAX_VALUE / 4;
        long[] dist = new long[vertices];

        Arrays.fill(dist, INF);
        dist[source] = 0;

        // Relax all edges V-1 times
        for (int i = 1; i < vertices; i++) {

            boolean updated = false;

            for (Edge edge : edges) {

                if (dist[edge.src] == INF) {
                    continue;
                }

                long newDist = dist[edge.src] + edge.weight;

                if (newDist < dist[edge.dest]) {
                    dist[edge.dest] = newDist;
                    updated = true;
                }
            }

            // Early termination
            if (!updated) {
                break;
            }
        }

        // Check for reachable negative cycle
        for (Edge edge : edges) {

            if (dist[edge.src] != INF &&
                    dist[edge.src] + edge.weight < dist[edge.dest]) {

                throw new IllegalStateException(
                        "Negative weight cycle detected");
            }
        }

        return dist;
    }

    public static void main(String[] args) {

        int vertices = 5;

        List<Edge> edges = new ArrayList<>();

        addUndirectedEdge(edges, 0, 1, 4);
        addUndirectedEdge(edges, 0, 2, 2);
        addUndirectedEdge(edges, 1, 2, 1);
        addUndirectedEdge(edges, 1, 3, 5);
        addUndirectedEdge(edges, 2, 3, 8);
        addUndirectedEdge(edges, 3, 4, 3);

        long[] dist = bellmanFord(vertices, edges, 0);

        System.out.println(Arrays.toString(dist));
    }
}
