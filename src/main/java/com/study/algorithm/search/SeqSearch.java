package com.study.algorithm.search;

/**
 * 顺序查找，数组不需要顺序
 */
public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        int result = seqSearch(arr, 10);
        if(result >= 0 ) {
            System.out.println("找到了key,索引为：" + result);
        } else {
            System.out.println("该数组中没有该key");
        }

    }
    public static int seqSearch(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == key) {
                return i;
            }
        }
        return -1;

    }
}
