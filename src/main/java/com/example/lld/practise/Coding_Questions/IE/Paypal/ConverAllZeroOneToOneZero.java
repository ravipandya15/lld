package com.example.lld.practise.Coding_Questions.IE.Paypal;

import java.util.List;

public class ConverAllZeroOneToOneZero {

    public static void main(String[] args) {

//        https://leetcode.com/discuss/interview-question/2115231/paypal-hackerrank-oa
//        Basically ans = Math.max(countOfZeroesBeforeLastOne, countOfOnesAfterFirstZero);

        String a = "001100101";
        String b = "010101";
        String c = "111000";

        List<String> list = List.of("001100101", "010101", "111000", "011010101001010", "011011");

        for (String s: list) {
            System.out.println("ans for " + s + " is: " + solve(s));
        }
    }

    private static int solve(String s) {
        int n = s.length();
        int i = 0, j = 0;
        int count1 = 0;
        int maxi1 = 0;
        int count2 = 0;
        int maxi2 = 0;
        while (j <= n) {
            if (s.charAt(j) == '0') break; // if (s.charAt(j) == '1') j++; else break;
            j++;
        }
        i = j;

        while (j < n) {
            if (s.charAt(j) == '1') {
                count1 += j - i;
                maxi1 = Math.max(maxi1, j - i);
                i++;
            }
            j++;
        }
        
        i = n-1;
        j = n-1;

        while (j >= 0) {
            if (s.charAt(j) == '1') break; // if (s.charAt(j) == '0') j--; else break;
            j--;
        }

        i = j;

        while (j >= 0) {
            if (s.charAt(j) == '0') {
                count2 += i - j;
                maxi2 = Math.max(maxi2, i - j);
                i--;
            }
            j--;
        }

        return Math.max(maxi1, maxi2);
    }

    // Not giving correct answer for 011011
    private static int solve2(String s) {
        int n = s.length();
        int ones = 0;
        int ans = 0;
        int i = n-1;
        while(i >= 0 && s.charAt(i) == '0'){
            i--;
        }

        for(; i>=0; i--){
            if(s.charAt(i)=='0'){
                ans=Math.max(ans, ones-1);
                ans++;
                ones=0;
            }
            else{
                ones++;
            }
        }
        return ans;
    }
}
