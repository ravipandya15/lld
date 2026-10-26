package com.example.lld.practise.Coding_Questions.IE.Synechron;

public class Main {

/*
    String is given. abbabba
    prefixes are: a, ab, abb, abba, abbab, abbabb, abbabba
    suffixes are: a, ba, bba, abba, babba, bbabba, abbabba

    proper prefixes are: a, ab, abb, abba, abbab, abbabb
    proper suffixes are: a, ba, bba, abba, babba, bbabba

    return the length of the longest proper prefix which is also proper suffix

    need to use KMP algorithm.
    I think lps array will give the ans.

    Also learn and code  Rabin Karp as well as KMP algorithm

    ========================================================================================
    My approach - Date - 21/09/2026
    ========================================================================================

    if str is palindrome
        str1 = s.substring(0, n-1);
        str2 = s.substring(1, n);

        ans1 = LIS(str1, reverse(str1))
        ans2 = LIS(str2, reverse(str2))
        return Math.max(ans1, ans2);
    else
        return LIS(str, reverse(str))
 */

}
