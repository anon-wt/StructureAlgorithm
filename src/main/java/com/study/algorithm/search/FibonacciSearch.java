package com.study.algorithm.search;

import java.util.Arrays;

/**
 * fibonacci查找，需要 mid = left + F[k-1] -1
 */
public class FibonacciSearch {
    public static int MAX = 20;
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 89, 1000, 1000, 1234};
        int result = fibonacciSearch(arr, 10);
        if(result >= 0 ) {
            System.out.println("找到了key,索引为：" + result);
        } else {
            System.out.println("该数组中没有该key");
        }
    }

    /**
     * 采用非递归方法
     * @param arr
     * @param key
     * @return
     */
    public static int fibonacciSearch(int[] arr, int key) {
        // 1.获得fibonacci数列
        int low = 0;
        int high = arr.length - 1;
        int k = 0;
        int mid = 0;

        int[] fibonacci = fibonacci();
        // 2.获得k
        while (high > fibonacci[k] - 1) {
            k++;
        }
        // 3.补充值
        int[] newArr = Arrays.copyOf(arr, fibonacci[k]);
        for (int i = high + 1; i < newArr.length; i++) {
            newArr[i] = arr[high];
        }


        // 4. mid
        while (low <= high) {
            mid = low + fibonacci[k - 1] - 1;
            if (key < newArr[mid]) {
                high = mid - 1;
                k--;
            } else if (key > newArr[mid]) {
                low = mid + 1;
                k -= 2;
            } else {
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }

    /**
     * 获得feibonaq数组
     * @return
     */
    public static int[] fibonacci() {
        int[] arr = new int[MAX];
        arr[0] = 1;
        arr[1] = 1;
        for (int i = 2; i < MAX; i++) {
            arr[i] = arr[i-1] + arr[i-2];
        }
        return arr;

    }


}
