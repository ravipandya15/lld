package com.example.lld.practise.Coding_Questions;

import java.util.Arrays;

public class Floyd_Warshall_Algorithm {
    static final long INF = Long.MAX_VALUE / 4;

    static void addUndirectedEdge(
            long[][] dist, int u, int v, int weight) {

        dist[u][v] = Math.min(dist[u][v], weight);
        dist[v][u] = Math.min(dist[v][u], weight);
    }

    static void floydWarshall(long[][] dist) {

        int n = dist.length;

        // Intermediate vertex
        for (int k = 0; k < n; k++) {

            // Source vertex
            for (int i = 0; i < n; i++) {

                // Destination vertex
                for (int j = 0; j < n; j++) {

                    if (dist[i][k] == INF ||
                            dist[k][j] == INF) {
                        continue;
                    }

                    dist[i][j] = Math.min(
                            dist[i][j],
                            dist[i][k] + dist[k][j]
                    );
                }
            }
        }

        // Negative cycle detection
        for (int i = 0; i < n; i++) {
            if (dist[i][i] < 0) {
                throw new IllegalStateException(
                        "Negative weight cycle detected");
            }
        }
    }

    public static void main(String[] args) {

        int vertices = 5;

        long[][] dist = new long[vertices][vertices];

        // Initialize matrix
        for (int i = 0; i < vertices; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        // Undirected edges
        addUndirectedEdge(dist, 0, 1, 4);
        addUndirectedEdge(dist, 0, 2, 2);
        addUndirectedEdge(dist, 1, 2, 1);
        addUndirectedEdge(dist, 1, 3, 5);
        addUndirectedEdge(dist, 2, 3, 8);
        addUndirectedEdge(dist, 3, 4, 3);

        floydWarshall(dist);

        // Print all-pairs shortest distances
        for (long[] row : dist) {
            System.out.println(Arrays.toString(row));
        }
    }
}
