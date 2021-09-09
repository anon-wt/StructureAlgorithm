package com.study.algorithm.sort;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int arr[] = {8, 4, 5, 7, 1, 3, 6, 2};
        int temp[] = new int[arr.length]; // 归并排序需要额外的空间
        mergeSort(arr, 0 , arr.length -1, temp);
        System.out.println("归并排序后=" + Arrays.toString(arr));

    }
    // 分+和的方法
    public static void  mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2; //中间索引
            // 向左递归
            mergeSort(arr, left, mid, temp);
            // 向右递归
            mergeSort(arr, mid + 1, right, temp);

            // 到合并
            merge(arr, left, right, mid, temp);

        }
    }



    public static void merge(int[] arr, int left, int right, int mid, int[] temp) {
        System.out.println("ssss");
        int i = left; // 初始化i, 左边有序序列的初始索引
        int j = mid + 1; // 初始化j, 右边有序序列的初始化索引
        int t = 0; // 指向temp数组的当前索引

        // 先把左右两边的（有序）的数组按照规则填充到temp数组中
        // 直到左右两边的有序序列，有一边处理完毕为止
        while (i <= mid && j <= right) {
            // 如果发现左边的有序序列当前元素，小于等于右边的有序序列的当前元素
            // 即将左边的当前元素，拷贝到temp数组,反之将右边有序序列的当前元素，填充到temp数组中
            // 然后t++ ,i++
            if(arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t += 1;
                i += 1;
            } else {
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }

        // 把剩余的数据的一边数据依次全部填充到temp中
        while( i <= mid) { // 左边有序序列有剩余元素吗就全部填充到temp
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }

        // 把剩余的数据的一边数据依次全部填充到temp中
        while( j <= right) { // 右边有序序列有剩余元素吗就全部填充到temp
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }

        // 将temp数组的元素拷贝到arr
        // 注意，并不是每次都copy所有
        t = 0;
        int tempLeft = left;
        System.out.println("tempLeft =" + tempLeft + ", right = " + right);
        while (tempLeft <= right) {
            // 第一次合并 templeft = 0 right = 1// 第二次合并tempLeft = 2 right = 3 // 第三次 tl = 0 rt =3
            // 最后依次 templeft = 0 right = 7
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }
    }

}
