package com.example.lld.practise.Coding_Questions;

class RearrangeStringAndFindCost {
    // Question asked in IBM
    /**
    Binary String given, rearrange such that all 1's appear after all 0's.
     Rules for rearrange
     you can move 1 to it's right (until next right's index)
     for each move, cost = 1 + no. of moves towards right.
     ex. 110010
     1 at index 1 will move to index 3. total moves towards right = 3-1 = 2.
     Cost = 1 + 2 = 3
     Here how it will work
     110010 - initial
     101010
     100110  = cost = 2 + 1 = 3
     010110
     001110 = cost = 2 + 1 = 3
     001101 =  cost = 1 + 1 = 2
     001011 =  cost = 1 + 1 = 2
     000111 =  cost = 1 + 1 = 2
     Total cost = 3+3+2+2+2 = 12
     */
    public static void main (String[] args) {
        String s1 = "110100";
        System.out.println("cost is: 13=" + solve(s1));
        String s2 = "110101";
        System.out.println("cost is: 10=" + solve(s2));
        String s3 = "1010100";
        System.out.println("cost is: 15=" + solve(s3));

    }

    private static int solve(String s) {
        int n = s.length();
        int countOfOne = 0;
        int prevIndex = -1;
        int cost = 0;
        int i = 0;
        for (i = 0; i < n; i++) {
            if (s.charAt(i) == '1' && prevIndex == -1) {
                countOfOne++;
                prevIndex = i;
            } else if (s.charAt(i) == '1') {
               if (i - prevIndex > 1) {
                   int diff = i - prevIndex;
                   cost += diff * countOfOne;
               }
               prevIndex = i;
               countOfOne++;
            }
        }

        if (i - prevIndex > 1) {
            int diff = i - prevIndex;
            cost += diff * countOfOne;
        }

        int ans = switch (cost) {
            case 13: yield 13;
            case 10: yield 10;
            case 15: yield 15;
            default: yield 100;
        };

        System.out.println("Testing of Yield keyword in Java: " + ans);

        return cost;
    }

}
