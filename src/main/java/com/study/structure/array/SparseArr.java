package com.study.structure.array;

import java.io.*;

/**
 * 数组和稀疏数组的相互转换
 */
public class SparseArr {
    public static void main(String[] args) {
        // 创建一个11 * 11 的二维数组， 其中0 表示没有数字， 1表黑棋， 2 表示白棋
        int[][] chessArr = new int[11][11];
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;
        chessArr[4][5] = 3;
        printChessArr(chessArr);

        // 数组转稀疏数组
        int[][] sparseArr = toSparseArr(chessArr);
        printSparseArr(sparseArr);

        String path = "D:\\project\\study\\StructureAlgorithm\\src\\data\\sparse.txt";
        download(sparseArr, path);
        int[][] sparseArr1 = upload(path, 4, 3);
        // 稀疏数组转普通数组
        int[][] chessArr1 = toChessArr(sparseArr1);
        printChessArr(chessArr1);
    }


    /**
     * 打印原始数组
     * @param chessArr
     */
    private static void printChessArr(int[][] chessArr) {
        for (int[] row : chessArr) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }

    /**
     * 普通数组转稀疏数组
     * @param chessArr
     * @return
     */
    private static int[][] toSparseArr(int[][] chessArr) {
        // 1. 计算sum
        int sum = 0;
        for (int[] row : chessArr) {
            for (int data : row) {
                if(data != 0) {
                 sum ++;
                }
            }
        }
        // 2. 创建稀疏数组
        int[][] sparseArr = new int[sum + 1][3];

        sparseArr[0][0] = chessArr.length;
        sparseArr[0][1] = chessArr[0].length;
        sparseArr[0][2] = sum;

        int count = 1;
        for (int i = 0; i < chessArr.length; i++) {
            for (int j = 0; j < chessArr[i].length; j++) {
                if (chessArr[i][j] != 0) {
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr[i][j];
                    count ++;
                }
            }
        }
        return sparseArr;
    }

    /**
     * 打印稀疏数组
     * @param sparseArr
     */
    private static void printSparseArr(int[][] sparseArr) {
        for (int[] row : sparseArr) {
            System.out.printf("%d\t%d\t%d\n", row[0], row[1], row[2]);
        }
    }

    /**
     * 普通数组转稀疏数组
     * @param sparseArr
     * @return
     */
    private static int[][] toChessArr(int[][] sparseArr) {
        // 1. 读取第一行数据创建不同数组
        int[][] chessArr = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        return chessArr;
    }

    /**
     * 将数组保存起来
     * @param sparseArr
     * @param path
     */
    private static void download(int[][] sparseArr, String path) {
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try {
            File file = new File(path);
            fos = new FileOutputStream(file);
            osw = new OutputStreamWriter(fos, "UTF-8");
            bw = new BufferedWriter(osw);
            for (int[] row : sparseArr) {
                for (int i = 0; i < row.length; i++) {
                    if (i != row.length - 1) {
                        bw.write( row[i] + "\t");
                    } else {
                        bw.write( row[i] + "\n");
                    }
                }
            }
            bw.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (osw != null) {
                    osw.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 加载文件
     * @param path
     */
    private static int[][] upload(String path, int row, int column) {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        int[][] sparseArr = new int[row][column];
        try {
            fis = new FileInputStream(path);
            isr = new InputStreamReader(fis, "UTF-8");
            br = new BufferedReader(isr);

            String line = "";
            String[] arrs = null;
            int count = 0;
            while ((line=br.readLine()) != null) {
                arrs = line.split("\t");
                sparseArr[count][0] = Integer.parseInt(arrs[0]);
                sparseArr[count][1] = Integer.parseInt(arrs[1]);
                sparseArr[count][2] = Integer.parseInt(arrs[2]);
                count ++ ;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (isr != null) {
                    isr.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sparseArr;
    }


}
