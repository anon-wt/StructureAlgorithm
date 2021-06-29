package com.study.algorithm.recursion;

/**
 * 八皇后问题
 */
public class Queue8 {
    private static int MAX = 8;
    private static int RESULT_COUNT = 0;
    private static int JUDGE_COUNT = 0;
    private static int[] RESULT = new int[MAX];


    public static void main(String[] args) {
        check(0);
        System.out.printf("一个有%d种解法 \n", RESULT_COUNT);
        System.out.printf("一个判断了%d次", JUDGE_COUNT);
    }


    /**
     * 判断
     * @param n 第n个皇后
     * @return
     */
    public static void check(int n) {
        // 当n = 8, 则说明已经进入第9个皇后了，说明前面的8个皇后没问题
        if(n == MAX ) {
            print();
            return;
        }
        for (int i = 0; i < MAX; i++) {
            RESULT[n] = i;

            if (judge(n)) {
                check(n + 1);
            }
        }
    }


    /**
     * 判断
     * @param n 第n个皇后
     * @return
     */
    public static boolean judge(int n) {
        JUDGE_COUNT ++;
        //跟之前的皇后进行比较
        for (int i = 0; i < n; i++) {
            // 如果在同一列或者在同一斜线则冲突
            if (RESULT[i] == RESULT[n] || Math.abs(n - i) == Math.abs(RESULT[n] - RESULT[i])) {
                return false;
            }
        }

        return true;
    }

    public static void print() {
        RESULT_COUNT ++ ;
        for (int i = 0; i < RESULT.length; i++) {
            System.out.print(RESULT[i] + " ");
        }
        System.out.println("");
    }
}
