package com.study.algorithm.sort;

import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
        int arr[] = { 53, 3, 542, 748, 14, 214};

        radixSort(arr);
    }
    // 基数排序方法
    public static void radixSort(int[] arr) {

        // 第一轮排序（针对元素的各位进行排序处理）
        // 定义一个二维数组表示10个桶，每个桶就是要给一维数组
        // 1.二维数组包含10个一维数组
        // 2.为了防止在放入数的时候，数据溢出，则每个一维数组（桶），大小定义为arr.lenght
        // 3.很明显基数排序是空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];

        // 为了记录每个桶中，实际存放了多少个数据，我们定义一个一维数组，来记录各个桶每次放入数据的个数
        // bucketElementCounts[0] 记录的就是bucket[0]的桶放入数据个数
        int[] bucketElementCounts = new int[10];


        //取得最大的值
        int max = arr[0];
        for(int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //取得最大的位数
        int maxLength = (max + "").length();
        for (int i = 0, n = 1; i < maxLength; i ++, n *= 10) {
            // 第i轮（针对每个元素的个位进行排序处理）
            for(int j = 0; j < arr.length; j ++) {
                // 取出每个元素的个位的值
                int digitOfElenment = arr[j] / n % 10;
                bucket[digitOfElenment][bucketElementCounts[digitOfElenment]] = arr[j];
                bucketElementCounts[digitOfElenment] ++;
            }

            // 按照桶的顺序放入桶中
            int index = 0;
            // 遍历每个桶，将同种的数据放入数组中
            for(int k = 0; k < bucketElementCounts.length; k ++) {
                //如果桶中有数据, 我们才放入到原数组中
                if(bucketElementCounts[k] != 0) {
                    //循环该桶第k个桶，即第k个一维数组
                    for(int l = 0; l < bucketElementCounts[k]; l ++){
                        //取出元素中放入arr
                        arr[index] = bucket[k][l];
                        index ++;
                    }
                }
                bucketElementCounts[k] = 0;
            }
            System.out.println("第i轮：" + Arrays.toString(arr));
        }

    }

}
