package com.example.lld.practise.MultiThreading.MultiThreadedMergeSort;

import java.util.Arrays;

public class MultiThreadedMergeSort {

    // Prevent thread explosion by setting a maximum recursion depth for threads
    private static final int MAX_DEPTH = 3;

    public static void parallelMergeSort(int[] array) {
        parallelMergeSort(array, 0, array.length - 1, 0);
    }

    private static void parallelMergeSort(int[] array, int left, int right, int depth) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;

        // If we haven't hit max depth, split work into two separate native threads
        if (depth < MAX_DEPTH) {
            Thread leftThread = new Thread(() -> {
                parallelMergeSort(array, left, mid, depth + 1);
            });

            Thread rightThread = new Thread(() -> {
                parallelMergeSort(array, mid + 1, right, depth + 1);
            });

            // Start both threads executing concurrently
            leftThread.start();
            rightThread.start();

            try {
                // Practise.Main thread blocks and waits for both child threads to finish sorting
                leftThread.join();
                rightThread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        } else {
            // Fallback to normal single-threaded recursion if deep enough
            parallelMergeSort(array, left, mid, depth + 1);
            parallelMergeSort(array, mid + 1, right, depth + 1);
        }

        // Merge the two sorted halves back together
        merge(array, left, mid, right);
    }

    private static void merge(int[] array, int left, int mid, int right) {
        int[] leftArray = Arrays.copyOfRange(array, left, mid + 1);
        int[] rightArray = Arrays.copyOfRange(array, mid + 1, right + 1);

        int i = 0, j = 0, k = left;

        while (i < leftArray.length && j < rightArray.length) {
            if (leftArray[i] <= rightArray[j]) {
                array[k++] = leftArray[i++];
            } else {
                array[k++] = rightArray[j++];
            }
        }

        while (i < leftArray.length) {
            array[k++] = leftArray[i++];
        }

        while (j < rightArray.length) {
            array[k++] = rightArray[j++];
        }
    }

    public static void main(String[] args) {
        int[] data = {12, 11, 13, 5, 6, 7, 50, 43, 22, 1, 3};
        System.out.println("Original Array: " + Arrays.toString(data));

        long startTime = System.currentTimeMillis();
        parallelMergeSort(data);
        long timeElapsed = System.currentTimeMillis() - startTime;
        System.out.println("Sorted Array:   " + Arrays.toString(data) + " with execution time: " + timeElapsed + " ms.");
    }
}

