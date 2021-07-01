package com.study.algorithm.sort;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {3, 12,  9, -1, 10};
        insertSort(arr);
    }

    public static void insertSort(int[] arr) {
        int index; // 要插入的值的前一个位置
        int insertVal; // 要查入的值
        for (int i = 1; i < arr.length; i++) {
            insertVal = arr[i];
            index = i - 1;
            while (index >= 0 && insertVal < arr[index]) {
                arr[index+1] = arr[index];
                index --;
            }

            if(arr[index+1] != insertVal) {
                arr[index+1] = insertVal;
                System.out.println("第" + i + "次处理" + Arrays.toString(arr));
            }
        }

    }
}
