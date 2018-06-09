package com.sununiq.snippet.jianzhioffer;

/**
 * 递归和循环
 */
public class Question9 {
    public static void main(String[] args) {
        System.out.println(fib(10));
        System.out.println(fib1(100));
        System.out.println(fib3(100));
        System.out.println(downToTopResolve(100));
    }

    /**
     * 递归
     */
    static long fib(int n) {
        if(n == 0) {
            return 0;
        }
        if(n == 1) {
            return 1;
        }

        return fib(n - 1) + fib(n - 2);
    }

    /**
     * 尾递归
     */
    static long fib1(int n) {
        return helper(1, 0, n);
    }

    private static long helper(long a, long b, int n) {
        if(n == 0) {
            return b;
        }

        return helper(a + b, a, n - 1);
    }

    /**
     * 循环
     */
    static long fib3(int n) {
        if(0 == n) {
            return 0;
        }
        if(1 == n) {
            return 1;
        }

        long a = 1, b = 0;
        while (n-- > 0) {
            long temp = a;
            a = a + b;
            b = temp;
        }
        return b;
    }

    /**
     * 自底向上包含"动态规划"思想的解法
     */
    static long downToTopResolve(int n) {
        if(n == 0) {
            return 0;
        } else if(n == 1) {
            return 1;
        } else {
            long[] fibonacciArray = new long[n + 1]; // fibonacciArray[i]表示第i个斐波那契数
            fibonacciArray[0] = 0;
            fibonacciArray[1] = 1;
            for (int i = 2; i <= n; i++) {
                fibonacciArray[i] = fibonacciArray[i - 1] + fibonacciArray[i - 2];
            }

            return fibonacciArray[fibonacciArray.length - 1];
        }

    }
}
