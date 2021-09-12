package com.study.algorithm.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 二分法查找，数组需要是顺序的
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 89, 1000, 1000, 1234};
//        int result = binarySearch(arr, 0, arr.length-1, 11);
//        if(result >0 ) {
//            System.out.println("找到了key,索引为：" + result);
//        } else {
//            System.out.println("该数组中没有该key");
//        }

        List<Integer> resultArr = binarySearch2(arr, 0, arr.length - 1, 1000);
        if(!resultArr.isEmpty()) {
            System.out.println("找到了key,索引为：" + Arrays.toString(resultArr.toArray()));
        } else {
            System.out.println("该数组中没有该key");
        }



    }

    public static int binarySearch(int[] arr, int left, int right, int key) {
        // 终止条件
        if(left > right) {
            return -1;
        }

        int mid = (left + right) / 2;
        if (key < arr[mid]) {
            return binarySearch(arr, left, mid - 1, key);
        } else if (key > arr[mid]) {
            return binarySearch(arr, mid + 1, right, key);
        } else {
            // 找到对应值
            return mid;
        }

    }

    public static List<Integer> binarySearch2(int[] arr, int left, int right, int key) {
        // 终止条件
        if(left > right) {
            return new ArrayList<Integer>();
        }

        int mid = (left + right) / 2;
        if (key < arr[mid]) {
            return binarySearch2(arr, left, mid - 1, key);
        } else if (key > arr[mid]) {
            return binarySearch2(arr, mid + 1, right, key);
        } else {
            ArrayList<Integer> resultArr = new ArrayList<>();
            // 往左查找
            int l = mid - 1;
            while (true) {
                if(l < 0 || arr[l] != key) {
                    break;
                }
                resultArr.add(l);
                l --;
            }

            // 将中间的插入
            resultArr.add(mid);

            // 往右查找

            int r = mid + 1;
            while (true) {
                if(r > arr.length - 1 || arr[r] != key) {
                    break;
                }
                resultArr.add(r);
                r ++;
            }

            // 返回结果



            // 找到对应值
            return resultArr;
        }

    }

}
