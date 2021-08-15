package com.study.algorithm.sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 23, -74, 70};
        quickSort(arr, 0, arr.length -1);
        System.out.println("arr: " + Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        int pivot = (left + right) / 2;
        int temp;
        while (l < r) {
            while (arr[l] < arr[pivot]) {
                l += 1;
            }

            while (arr[r] > arr[pivot]) {
                r -= 1;
            }

            if (l >= r) {
                break;
            }
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            if (arr[l] == arr[pivot]) {
                r -= 1;
            }

            if (arr[r] == arr[pivot]) {
                l += 1;
            }
        }

        if (l == r) {
            l += 1;
            r -= 1;
        }
        if (left < r) {
            quickSort(arr, left, r);
        }

        if (right > l) {
            quickSort(arr, l, right);
        }


    }
}
