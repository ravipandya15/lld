package com.example.lld.practise.Coding_Questions;

import java.util.HashMap;
import java.util.Map;

/**

 createPath "/a-> 1" -> return true if possible to create path, of parent path does not exist, return false
 getPath -> if path exists, return value else return -1.

 Solution1: -> Using Map
 Solution2 : Using Trie

 */

// Remember, no value for root. Root is just a starting point.
class TrieNode {
    Map<String, TrieNode> childs;
    int value;

    public TrieNode() {
        childs = new HashMap<>();
        value = -1;
    }
}
public class FileSystem {
    TrieNode root;
    public FileSystem() {
        root = new TrieNode();
    }

    public boolean createPath(String path, int value) {
        TrieNode node = root;
        String[] chunks = path.split("/");
        int n = chunks.length;
        for (int i = 1; i < n; i++) {

        }

        return false;
    }

    public int get(String path) {
        return -1;
    }
}
