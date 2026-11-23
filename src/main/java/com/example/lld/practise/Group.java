package com.example.lld.practise;

import java.util.*;

/*
Given an array of words, group the words that contain the same characters in a different order.
Example 1:
Input: ["eat", "tea", "tan", "ate", "nat", "bat"]
Output: [["eat", "tea", "ate"], ["tan", "nat"], ["bat"]]
Example 2:
Input: ["abc", "bca", "cab", "dog", "god"]
Output: [["abc", "bca", "cab"], ["dog", "god"]]
Example 3:
Input: ["hello", "world", "cat", "act"]
Output: [["hello"], ["world"], ["cat", "act"]]
Example 4:
Input: ["a", "a", "b", "b"]
Output: [["a", "a"], ["b", "b"]]
 */
public class Group {
    // ["eat", "tea", "tan", "ate", "nat", "bat"]
    // aet, aet, ant, aet, ant, abt

//    String -> List<String>
//    aet -> eat, tea, ate
//    ant -> tan, nat
//    abt -> bat

    public static void main() {
        List<String> list = List.of("eat", "tea", "tan", "ate", "nat", "bat");
        List<String> list2 = List.of("abc", "bca", "cab", "dog", "god");
        List<String> list3 = List.of("hello", "world", "cat", "act");
        List<String> list4 = List.of("a", "a", "b", "b");


        List<List<String>> result = groupAnagrams(list);
        List<List<String>> result2 = groupAnagrams(list2);
        List<List<String>> result3 = groupAnagrams(list3);
        List<List<String>> result4 = groupAnagrams(list4);

        System.out.println(result);
        System.out.println(result2);
        System.out.println(result3);
        System.out.println(result4);
    }

    private static List<List<String>> groupAnagrams(List<String> list) {
        Map<String, List<String>> map = new HashMap<>();

        List<List<String>> ansList = new ArrayList<>();

        for (String str: list) {
            char[] StringInChars = str.toCharArray();
            Arrays.sort(StringInChars);
            String sortedString = new String(StringInChars);

            map.computeIfAbsent(sortedString, k -> new ArrayList<>()).add(str);
        }

        for (Map.Entry<String, List<String>> entry: map.entrySet()) {
            ansList.add(entry.getValue());
        }

        return ansList;
    }


}
