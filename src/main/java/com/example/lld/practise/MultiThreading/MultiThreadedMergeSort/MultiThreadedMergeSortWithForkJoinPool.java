package com.example.lld.practise.MultiThreading.MultiThreadedMergeSort;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class MultiThreadedMergeSortWithForkJoinPool {

    static class MergeSortTask extends RecursiveAction {

        private final int[] arr;
        private final int left;
        private final int right;

        // Small threshold so our example actually forks
        private static final int THRESHOLD = 2;

        MergeSortTask(int[] arr, int left, int right) {
            this.arr = arr;
            this.left = left;
            this.right = right;
        }

        @Override
        protected void compute() {

            // Base case
            if (right - left <= THRESHOLD) {
                Arrays.sort(arr, left, right + 1);
                return;
            }

            int mid = left + (right - left) / 2;

            MergeSortTask leftTask =
                    new MergeSortTask(arr, left, mid);

            MergeSortTask rightTask =
                    new MergeSortTask(arr, mid + 1, right);

            // Fork left task
            leftTask.fork();

            // Execute right task in current worker
            rightTask.compute();

            // Wait for left task
            leftTask.join();

            // Both halves are now sorted
            merge(arr, left, mid, right);
        }

        private void merge(int[] arr, int left, int mid, int right) {

            int[] temp = new int[right - left + 1];

            int i = left;
            int j = mid + 1;
            int k = 0;

            while (i <= mid && j <= right) {

                if (arr[i] <= arr[j]) {
                    temp[k++] = arr[i++];
                } else {
                    temp[k++] = arr[j++];
                }
            }

            while (i <= mid) {
                temp[k++] = arr[i++];
            }

            while (j <= right) {
                temp[k++] = arr[j++];
            }

            System.arraycopy(temp, 0, arr, left, temp.length);
        }
    }

    public static void main(String[] args) {

        int[] arr = {
                38, 27, 43, 3,
                9, 82, 10, 5
        };

        ForkJoinPool pool = new ForkJoinPool();

        pool.invoke(new MergeSortTask(arr, 0, arr.length - 1));

        System.out.println(Arrays.toString(arr));

        pool.shutdown();
    }
}

