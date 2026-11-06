package com.example.lld.practise.Coding_Questions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class DSU {

    int[] rank;
    int[] parent;

    public DSU(int n) {
        rank = new int[n];
        parent = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int find(int value) {
        while (parent[value] != value) {
            value = parent[value];
        }

        return value;
    }

    public void union(int v1, int v2) {

        int p1 = find(v1);
        int p2 = find(v2);

        if (p1 == p2) return;

        if (rank[p1] > rank[p2]) {
            parent[p2] = p1;
        }
        else if (rank[p2] > rank[p1]) {
            parent[p1] = parent[p2];
        }
        else {
            parent[p2] = p1;
            rank[p1]++;
        }
    }


}

public class Min_Operation_Needed_To_Make_String_Palindrom {
    static void main() {
        int[] arr = new int[] {2,4,5,7,4,5,5};
        int ans = minOperation(arr);

        System.out.println("min operations: " + ans);
    }

    public static int minOperation(int[] arr) {
        int n = arr.length;
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.putIfAbsent(arr[i], i);
        }

        int distinctValues = map.size();

        int left = 0;
        int right = n-1;

        DSU dsu = new DSU(distinctValues);

        while (left < right) {
            int x = map.get(arr[left]);
            int y = map.get(arr[right]);

            dsu.union(x, y);
            left++;
            right--;
        }

        Set<Integer> connectedComponents = new HashSet<>();
        for (int i = 0; i < map.size(); i++) {
            connectedComponents.add(dsu.find(i));
        }

        return distinctValues - connectedComponents.size();
    }


}
