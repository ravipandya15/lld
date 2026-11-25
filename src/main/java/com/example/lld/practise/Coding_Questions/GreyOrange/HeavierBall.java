package com.example.lld.practise.Coding_Questions.GreyOrange;

/*
Problem statement: I have n balls. Out of these n-1 balls are with same weight and 1 ball is heavier. In minimum attempts, find the heavier ball
 */

/**
 Divide into 3 groups
 Since exactly one ball is heavier, divide the balls into three approximately equal groups.
 1. Divide the n balls into three groups: A, B, and C.
 2. Weigh group A against group B.
 3. There are three possibilities:
 - If A is heavier, the heavier ball is in A.
 - If B is heavier, the heavier ball is in B.
 - If both are equal, the heavier ball is in C.
 4. Repeat the same process with the remaining group until only one ball remains.
 */
public class HeavierBall {

    private static int minAttempts(int n) {
        int attempts = 0;

        while (n > 1) {
            n = (n + 2)/ 3;
//            n = (int) Math.ceil(n / 3.0);
            attempts++;
        }

        return attempts;
    }

    private static int minAttemptsWithDP(int n) {
        int[] dp = new int[n+1];

        dp[1] = 0;

        for (int i = 2; i <=n; i++) {
            dp[i] = 1 + dp[(i + 2) / 3];
        }
        return dp[n];
    }
    static void main() {
        System.out.println(minAttempts(27)); // 3
        System.out.println(minAttempts(10)); // 3
        System.out.println(minAttempts(100)); // 5
        System.out.println(minAttemptsWithDP(100)); // 5
    }
}
