package com.study.algorithm.search;

/**
 * 插入查找，根据数据自适应中间值 mid = left + (right - left) *(key-arr[left]) /(arr[right]-arr[left])
 */
public class InsertSearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 89, 1000, 1000, 1234};
        int result = insertSearch(arr, 0, arr.length-1, 1000);
        if(result >= 0 ) {
            System.out.println("找到了key,索引为：" + result);
        } else {
            System.out.println("该数组中没有该key");
        }

    }

    public static int insertSearch(int[] arr, int left, int right, int key) {
        // 退出条件
        if(left > right) {
            return -1;
        }

        int mid = left + (right - left) * (key - arr[left])/(arr[right] - arr[left]);

        if(key < arr[mid]) {
            return insertSearch(arr, left, mid - 1, key);
        } else if (key > arr[mid]) {
            return insertSearch(arr, mid + 1, right, key);
        } else {
            return mid;
        }

    }
}
