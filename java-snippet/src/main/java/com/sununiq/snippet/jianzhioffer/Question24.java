package com.sununiq.snippet.jianzhioffer;

import java.util.Arrays;

/**
 * @program: java-snippet
 *
 * @description: 二叉树的后序遍历, 递归，最后一个节点为根节点，左小右大
 *
 * @author: sununiq
 *
 * @create: 2018-06-23 11:13
 **/
public class Question24 {
    public static void main(String[] args) {
        int[] arr1 = {5, 7, 6, 9, 11, 10, 8};
        int[] arr2 = {7, 4, 6, 5};
        int[] arr3 = {1, 1, 1, 1, 1, 6};
        int[] arr4 = {};

//        System.out.println(isPostOrderBst(arr1));
//        System.out.println(isPostOrderBst(arr2));
//        System.out.println(isPostOrderBst(arr3));
//        System.out.println(isPostOrderBst(arr4));

        System.out.println(isPostOrderBst(arr1, 0, arr1.length - 1));
        System.out.println(isPostOrderBst(arr2, 0, arr2.length - 1));
        System.out.println(isPostOrderBst(arr3, 0, arr3.length - 1));
        System.out.println(isPostOrderBst(arr4, 0, arr4.length - 1));
    }

    static boolean isPostOrderBst(int[] sequence) {
        if (sequence == null || sequence.length <= 0) {
            return false;
        }

        int root = sequence[sequence.length - 1];
        int cut = 0;

        for (int i = 0; i < sequence.length - 1; ++i) {
            if (sequence[i] > root) {
                break;
            }
            cut = i + 1;
        }

        for (int j = cut; j < sequence.length - 1; ++j) {
            if (sequence[j] < root) {
                return false;
            }
        }

        boolean left = true;
        if (cut > 0) {
            left = isPostOrderBst(Arrays.copyOfRange(sequence, 0, cut));
        }

        boolean right = true;
        if (cut < sequence.length - 1) {
            right = isPostOrderBst(Arrays.copyOfRange(sequence, cut, sequence.length - 1));
        }

        return left && right;
    }

    /**
     * 通过指针，原地比较，避免数组的拷贝
     * @param sequence
     * @param start
     * @param end
     * @return
     */
    static boolean isPostOrderBst(int[] sequence, int start, int end) {
        if (sequence == null || sequence.length <= 0 || start < 0 || end < 0) {
            return false;
        }

        int root = sequence[end];
        int cut = start;

        for (int i = start; i < end; ++i) {
            if (sequence[i] > root) {
                break;
            }
            cut = i + 1;
        }

        for (int j = cut; j < end; ++j) {
            if (sequence[j] < root) {
                return false;
            }
        }

        boolean left = true;
        if (cut > start) {
            left = isPostOrderBst(sequence, start, cut - 1);
        }

        boolean right = true;
        if (cut < end) {
            right = isPostOrderBst(sequence, cut, end - 1);
        }

        return left && right;
    }
}
