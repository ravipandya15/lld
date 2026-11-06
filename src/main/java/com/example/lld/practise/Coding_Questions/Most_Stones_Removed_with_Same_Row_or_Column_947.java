package com.example.lld.practise.Coding_Questions;

public class Most_Stones_Removed_with_Same_Row_or_Column_947 {

    public static int removeStones(int[][] stones) {
        int n = stones.length;
        int rowCount = 0;
        int colCount = 0;
        int res = 0;

        for (int i = 0; i < n; i++) {
            rowCount = Math.max(rowCount, stones[i][0]);
            colCount = Math.max(colCount, stones[i][1]);
            System.out.println("rowCount: " + rowCount);
            System.out.println("colCount: " + colCount);
        }

        int[] row = new int[rowCount+1];
        int[] col = new int[colCount+1];

        for (int i = 0; i < n; i++) {
            row[stones[i][0]]++;
            col[stones[i][1]]++;
        }


        System.out.println(" ");

        for (int i = 0; i < n; i++) {
            if (row[stones[i][0]] > 1 || col[stones[i][1]] > 1) {
                res++;
                row[stones[i][0]]--;
                col[stones[i][1]]--;
            }
        }

        return res;
    }

    static void main() {
        int[][] stones = new int[][] {
                {0, 1},
                {1, 2},
                {1, 3},
                {3, 3},
                {2, 3},
                {0, 2}
        };

        int res = removeStones(stones);

        System.out.println(res);
    }
}
