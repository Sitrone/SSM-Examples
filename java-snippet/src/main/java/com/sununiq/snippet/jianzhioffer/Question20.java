package com.sununiq.snippet.jianzhioffer;

/**
 * @program: java-snippet
 *
 * @description: 顺时针打印矩阵, 一圈一圈的打印
 *
 * @author: sununiq
 *
 * @create: 2018-06-10 11:56
 **/
public class Question20 {
    public static void main(String[] args) {
        int[][] array = {
            {1, 2, 3, 4, 7, 3},
            {1, 2, 3, 4, 10, 23},
            {1, 2, 3, 4, 23, 43},
            {1, 2, 3, 4, 23, 43},
            {1, 2, 3, 4, 23, 43},
            {1, 2, 3, 4, 23, 43},
            {1, 2, 3, 4, 23, 43},
            {1, 2, 3, 4, 9, 56}
        };

        printMatrixClockwise(array);
    }

    static void printMatrixClockwise(int[][] matrix) {
        if (matrix == null) {
            return;
        }
        printMatrixClockwise(matrix, 1);
    }

    private static void printMatrixClockwise(int[][] array, int start) {
        int row = 0;
        int xLength = array.length;
        int yLength = array[0].length;

        if (array.length >= array[0].length) {
            int temp = array[0].length % 2;
            row = array[0].length / 2 + temp;
        } else {
            int temp = array.length % 2;
            row = array.length / 2 + temp;
        }

        if (start > row) {
            return;
        }

        // 从左向右打印一行，到行尾以后从上往下打印一列
        for (int y = start-1; y <= yLength - start; y++) {
            int value = array[start-1][y];
            System.out.println(value);
        }

        // 从上往下打印
        for (int x = start; x <= xLength - start; x++) {
            int value = array[x][yLength-start];
            System.out.println(value);
        }

        // 从右往左打印
        for (int y = yLength - start -1; y >= start-1; y--) {
            int value = array[xLength-start][y];
            System.out.println(value);
        }

        // 从下往上打印
        for (int x = xLength-start-1; x >= start; x--) {
            int value = array[x][start-1];
            System.out.println(value);
        }

        start++;

        printMatrixClockwise(array, start);
    }
}
