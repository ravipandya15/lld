package com.example.lld.practise.Coding_Questions.IE.YellowAI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        int[][] input = new int[][] {{1, 4}, {0, 4}};
        List<int[]> result = mergeIntervals(input);
        for (int[] i: result) {
            System.out.print(i[0] + ", " + i[1] + "   ");
        }
        System.out.println("result: " + result);

        int[][] input2 = new int[][] {{1,2,3,4}, {5,6,7,8}, {9,10,11,12}};
        int target = 6;
        System.out.println("target is present? " + findElement(input2, target));
    }

    public static List<int[]> mergeIntervals(int[][] intervals) {
        List<int[]> result = new ArrayList<>();
        int n = intervals.length;
        if (n == 1) return result;
        Arrays.sort(intervals, (a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);

        int[] temp = intervals[0];

        for (int i = 1; i < n; i++) {
            if (intervals[i][0] <= temp[1]) {
                temp[1] = Math.max(temp[1], intervals[i][1]);
            } else {
                result.add(temp);
                temp = intervals[i];
            }
        }
        result.add(temp);

        return result;
    }

    private static boolean isPresentInRow(int[] row, int target) {
        int l = 0, r = row.length - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (row[mid] == target) {
                return true;
            }
            else if (target < row[mid]) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return false;
    }

    public static boolean findElement(int[][] arr, int target) {
        int n = arr.length;
        int m = arr[0].length;

        for (int i = 0; i < n; i++) {
            if (isPresentInRow(arr[i], target)) {
                return true;
            }
        }

        return false;
    }
}
