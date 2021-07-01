package com.study.algorithm.sort;

import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {3, 12,  9, -1, 10};
        selectSort(arr);
    }

    public static void selectSort(int[] arr) {
        int index;
        int min;
        for (int i = 0; i < arr.length - 1; i++) {
            index = i;
            min = arr[i];
            for (int j = i; j < arr.length; j++) {
                if (min > arr[j]) {
                    index = j;
                    min = arr[j];
                }
            }

            if (index != i) { // 如果当前下标就是最小值，则不需要交换
                arr[index] = arr[i];
                arr[i] = min;
                System.out.println("第 "+ i +"次，交换" + Arrays.toString(arr));
            }
        }
    }
}
