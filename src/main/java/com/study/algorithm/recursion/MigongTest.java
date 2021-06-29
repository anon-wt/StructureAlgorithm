package com.study.algorithm.recursion;

/**
 * 迷宫问题 最颠路径？？？？
 */
public class MigongTest {
    public static void main(String[] args) {
        // 1. 创建一个迷宫
        int[][] ints = new int[8][7];

        // 2.设置挡板
        for (int i = 0; i < 7; i++) {
            ints[0][i] = 1;
            ints[7][i] = 1;
        }

        for (int i = 0; i < 8; i++) {
            ints[i][0] = 1;
            ints[i][6] = 1;
        }

        ints[3][1] = 1;
        ints[3][2] = 1;
//        ints[1][3] = 1;
//        ints[2][3] = 1;

        setWay(ints, 1, 1);
        for (int[] anInt : ints) {
            for (int i : anInt) {
                System.out.print(i + " ");
            }

            System.out.println("");
        }

    }

    /**
     * 查找路线
     * @param map 共享地图
     * @param i,j 当前节点
     *            规定，0 是未走过路线，1 是墙， 2 是走过路线且走通的路线， 3 是走过路线未走通
     *            路线顺序是 下 -> 右 -> 上 -> 左
     * @return 返回结果
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) { // 表示找到路线
            return true;
        }

        if (map[i][j] == 0) { // 判断当前节点有没有走过
            map[i][j] = 2;

            if (setWay(map, i + 1, j)) {
                return true;
            } else if (setWay(map, i, j + 1)) {
                return true;
            } else if (setWay(map, i - 1, j)) {
                return true;
            } else if (setWay(map, i, j - 1)) {
                return true;
            } else {
                map[i][j] = 3;
                return false;
            }
        } else {
            return false;
        }
    }
}
