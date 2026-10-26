package com.example.lld.practise.MultiThreading.MultiThreadedMergeSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ParallelMergeSort {

    private final ExecutorService executor;
    private final int threshold;

    public ParallelMergeSort(int threadCount, int threshold) {
        this.executor = Executors.newFixedThreadPool(threadCount);
        this.threshold = threshold;
    }

    public void parallelMergeSort(int[] arr, int left, int right) throws ExecutionException, InterruptedException {
        if (left >= right) return;

        int mid = left + (right - left)/2;

        if (right - left <= this.threshold) {
            // do sequential merge sort
            sequentialMerge(arr, left, right);
        } else {
            // do parallel merge sort
            Future<?> leftTask = executor.submit(() -> runSort(arr, left, mid));
            Future<?> rightTask = executor.submit(() -> runSort(arr, mid+1, right));

            // wait fpr both the portion
            leftTask.get();
            rightTask.get();

            merge(arr, left, mid, right);
            System.out.println("merge done" + Arrays.toString(arr));
        }
    }

    private void runSort(int[] arr, int left, int right) {
        try {
            parallelMergeSort(arr, left, right);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void sequentialMerge(int[] arr, int left, int right) {
        if (left >= right) return;

        int mid = left + (right - left)/2;

        sequentialMerge(arr, left, mid);
        sequentialMerge(arr, mid + 1, right);

        merge(arr, left, mid, right);
        System.out.println("merge done" + Arrays.toString(arr));
    }

    private void merge(int[] arr, int left, int mid, int right) {
        // left -> mid is sorted
        // mid + 1 -> right is sorted

        int i = left;
        int j = mid + 1;
        List<Integer> list = new ArrayList<>();

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                list.add(arr[i]);
                i++;
            } else {
                list.add(arr[j]);
                j++;
            }
        }

        while (i <= mid) {
            list.add(arr[i]);
            i++;
        }

        while (j <= right) {
            list.add(arr[j]);
            j++;
        }

        int id = left;
        for (Integer integer : list) {
            arr[id] = integer;
            id++;
        }
    }

    public void shutDown() {
        executor.shutdown();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[] data = {12, 11, 13, 5, 6, 7, 50, 43, 22, 1, 3, 12, 11, 13, 5, 6, 7, 50, 43, 22, 1, 3, 12, 11, 13, 5, 6, 7, 50, 43, 22, 1, 3, 12, 11, 13, 5, 6, 7, 50, 43, 22, 1, 3, 12, 11, 13, 5, 6, 7, 50, 43, 22, 1, 3, 12, 11, 13, 5, 6, 7, 50, 43, 22, 1, 3, 12, 11, 13, 5, 6, 7, 50, 43, 22, 1, 3, 12, 11, 13, 5, 6, 7, 50, 43, 22, 1, 3, 12, 11, 13, 5, 6, 7, 50, 43, 22, 1, 3};
        System.out.println("Original Array: " + Arrays.toString(data));

        ParallelMergeSort parallelMergeSort = new ParallelMergeSort(20, 100);
        long startTime = System.currentTimeMillis();

        parallelMergeSort.parallelMergeSort(data, 0, data.length - 1);
        parallelMergeSort.shutDown();

        long timeElapsed = System.currentTimeMillis() - startTime;
        System.out.println("Sorted Array:   " + Arrays.toString(data) + " with execution time: " + timeElapsed + " ms.");
    }
}
