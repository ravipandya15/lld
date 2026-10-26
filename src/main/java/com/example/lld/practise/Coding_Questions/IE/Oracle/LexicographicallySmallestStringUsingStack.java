package com.example.lld.practise.Coding_Questions.IE.Oracle;

import java.util.Stack;

class Data {
    int index;
    char value;

    public Data(int index, char value) {
        this.index = index;
        this.value = value;
    }
}

// Oracle Interview

/*
I have originalString and from that I need to convert it to encrypted String with the help off temparary string.
Any one of the below rule can be done:

1. take first character from originalString and put it last in tempararyString.
2. take last character from tempararyString and put it last in encryptedString.

result string should be lexicographically smallest.

ex. "dby"
ans: "bdy"

ex: "zyxw"
ans: "wxyz"

ex: "vgxgpu"
ans: "ggpuvx"

 */

public class LexicographicallySmallestStringUsingStack {
    public static void main(String[] args) {
        String str = "vgxgpu";
//        String str = "dby";
//        String str = "zyxw";
        int n = str.length();
        char min = str.charAt(0);
        int[] nextSmaller = nextSmallerElement(str, n);
        for (int i = 0; i < n; i++) {
            System.out.print(nextSmaller[i] + ", ");
        }

        StringBuilder enc = new StringBuilder();

        Stack<Data> stack = new Stack<>();
        int i = 0;
        while (i < n) {
            if (str.charAt(i) < min) {
                min = str.charAt(i);
            }
            if (i < n && ( stack.isEmpty() || ( str.charAt(i) <= stack.peek().value ) ) ) {
                while (i < n && ( stack.isEmpty() || ( str.charAt(i) <= stack.peek().value ) )) {
                    stack.push(new Data(i, str.charAt(i)) );
                    if (str.charAt(i) < min) {
                        min = str.charAt(i);
                    }
                    i++;
                }
                continue;
            }

            // means stack.peek() > str.charAt(i)
            // 2 cases

            if (i < n) {
                if (nextSmaller[i] == -1 || min == stack.peek().value) {
                    enc.append(stack.peek().value);
                    stack.pop();
                } else {
                    stack.push(new Data(i, str.charAt(i)) );
                    i++;
                }
            }

//            if (nextSmaller[stack.peek().index] == -1
//            || nextSmaller[stack.peek().index] < i) {
//                enc.append(stack.peek().value);
//                stack.pop();
//            } else {
//                stack.push(new Data(i, str.charAt(i)) );
//                i++;
//            }
        }

        while (!stack.isEmpty()) {
            enc.append(stack.peek().value);
            stack.pop();
        }

        System.out.println(enc.toString());
    }

    private static int[] nextSmallerElement(String str, int n) {
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = n-1; i >= 0; i--) {

            while (!stack.isEmpty() && str.charAt(stack.peek()) >= str.charAt(i)) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                res[i] = -1;
            } else {
                res[i] = stack.peek();
            }
            stack.push(i);
        }

        return res;
    }
}
