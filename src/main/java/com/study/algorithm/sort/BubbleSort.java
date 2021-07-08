package com.study.algorithm.sort;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {3, 12, 9, -1, 10};
        bubbleSort(arr);

    }

    public static void bubbleSort(int[] arr) {
        int temp;
        boolean flag = false;
        for (int i = 0; i < arr.length -1; i++) {
            for (int j = 0; j < arr.length -1; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            if (!flag) {
                break;
            } else {
                flag = false;
            }
            System.out.println("第" + i + "次排序： " + Arrays.toString(arr));
        }
    }
}
