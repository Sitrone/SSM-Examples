package com.sununiq.snippet.jianzhioffer;

/**
 * 二维数组查找
 */
public class Question3 {

    public static void main(String[] args) {
        int[][] array = {
                {1, 2, 8, 9},
                {2, 4, 9, 12},
                {4, 7, 10, 13},
                {6, 8, 11, 15}
        };

        for (int i = 0; i < 10; i++) {
            System.out.print(containsWithBF(i, array) + " ");

            System.out.print(contains(i, array) + " ");

            System.out.println();
        }
    }

    /**
     * 暴力解法，复杂度O(n^2)
     */
    public static boolean containsWithBF(int key, int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if(key == array[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 从右下角搜索
     */
    public static boolean contains(int key, int[][] array) {
        int x = array.length - 1;
        int y = 0;

        while (x >= 0 && y < array[x].length - 1) {
            if(key == array[x][y]) {
                return true;
            } else if(key > array[x][y]) {
                y += 1;
            } else {
                x -= 1;
            }
        }

        return false;
    }
}
