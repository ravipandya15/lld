package com.example.lld.practise.Coding_Questions.Paypal;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MinOperations {
    // Give minimum operations to make string palindrome.

    /**
    Integer array given, convert array into palindrome sequence
    Operation for manipulating array

    Choose 2 integers x and y
    Replace every occurrence of x in the array with y.

    Return minimum operations required.

    Give java code.

    example
    [2,4,5,7,4,5,5] -> ans 2
     */

    static void main() {
        int[] arr = new int[] {2,4,5,7,4,5,5};

        int  minimumOperations = minimumOperations(arr);

        System.out.println("Minimum operations to make string palindrom: " + minimumOperations) ;
    }

    private static int minimumOperations(int[] arr) {

        Map<Integer, Integer> valuesToIndex = new HashMap<>();

        for (int val: arr) {
            valuesToIndex.putIfAbsent(val, valuesToIndex.size());
        }

        int distinctValues = valuesToIndex.size();;

        DSU dsu = new DSU(distinctValues);

        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int x = valuesToIndex.get(arr[left]);
            int y = valuesToIndex.get(arr[right]);
            if (x != y) {
                dsu.union(x, y);
            }
            left++;
            right--;
        }

        Set<Integer> connectedComponents = new HashSet<>();

        for (int i = 0; i < distinctValues; i++) {
            connectedComponents.add(dsu.findParent(i));
        }

        return distinctValues - connectedComponents.size();
    }
}

class DSU {
    int[] parent;
    int[] rank;

    public DSU(int n) {
        this.parent = new int[n];
        this.rank = new int[n];

        for (int i = 0; i< n; i++) {
            this.parent[i] = i;
        }
    }

    public int findParent(int x) {
        if (parent[x] == x) {
            return parent[x];
        }

        return parent[x] = findParent(parent[x]);
    }

    public void union(int x, int y) {
        int rootX = findParent(x);
        int rootY = findParent(y);

        if (rootX == rootY) return;

        if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        }
        else if (rank[rootY] > rank[rootX]) {
            parent[rootX] = rootY;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }
}
