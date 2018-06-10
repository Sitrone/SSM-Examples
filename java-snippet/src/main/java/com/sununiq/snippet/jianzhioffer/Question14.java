package com.sununiq.snippet.jianzhioffer;

import java.util.Arrays;

/**
 * 调整顺序使得奇数位于偶数前面, 更通用的，改为策略模式
 * 双指针法，注意conner case
 */
public class Question14 {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        int[] arr_1 = new int[]{1};
        reorderOldEven(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arr_1));


        int[] arr_2 = new int[]{1, 2, 3, 4, 5};
        reorderOldEven_1(arr_2, EvenOldStrategy.instance);
        System.out.println(Arrays.toString(arr_2));
    }

    static void reorderOldEven(int[] arr) {
        if(arr == null || arr.length == 0) {
            return;
        }

        int p1 = 0;
        int p2 = arr.length - 1;
        while (p1 < p2) {
            while (isOld(arr[p1]) && p1 < p2) {
                p1++;
            }
            while (!isOld(arr[p2]) && p1 < p2) {
                p2--;
            }

            if(p1 < p2) {
                swap(arr, p1, p2);
            }
        }
    }

    static void reorderOldEven_1(int[] arr, Strategy strategy) {
        if(arr == null || arr.length == 0) {
            return;
        }

        int p1 = 0;
        int p2 = arr.length - 1;
        while (p1 < p2) {
            while (strategy.isSupport(arr[p1]) && p1 < p2) {
                p1++;
            }
            while (!strategy.isSupport(arr[p2]) && p1 < p2) {
                p2--;
            }

            // 最后一次判断，避免p1 == p2的情况下，多交换一次
            if(p1 < p2) {
                swap(arr, p1, p2);
            }
        }
    }

    interface Strategy {
        boolean isSupport(int x);
    }

    static class EvenOldStrategy implements Strategy {
        static Strategy instance = new EvenOldStrategy();

        @Override
        public boolean isSupport(int x) {
            return isOld(x);
        }
    }


    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static boolean isOld(int x) {
        return (x & 0x1) != 0;
    }

    private static boolean isEven(int x) {
        return (x & 0x1) == 0;
    }
}
