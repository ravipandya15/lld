package com.example.lld.practise;

import java.util.ArrayList;
import java.util.List;


class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }
}
public class RootToLeaf {

    public static void dfs(Node node, int[] res, List<Integer> ans) {
        // base case
        if (node.left == null && node.right == null) {
            res[0] += node.value;
            ans.add(res[0]);
            res[0] -= node.value;
            return;
        }

        res[0] += node.value;

        if (node.left != null) {
            dfs(node.left, res, ans);
        }
        if (node.right != null) {
            dfs(node.right, res, ans);
        }

        res[0] -= node.value;
    }

    static void main() {
        Node root = new Node(1);
        Node left1 = new Node(2);
        Node right1 = new Node(3);

        Node left2 = new Node(5);

        root.left = left1;
        root.right = right1;

        left1.left = left2;

        List<Integer> res = new ArrayList<>();
        int[] ans = new int[] {0};
        dfs(root, ans, res);

        for (int val: res) {
            System.out.println(val + " ");
        }
    }
}
