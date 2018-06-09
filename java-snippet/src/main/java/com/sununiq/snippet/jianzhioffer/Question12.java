package com.sununiq.snippet.jianzhioffer;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * 打印1到n的最大n位数，注意大数的场景
 * 1. 字符串模拟大数
 * 2. 全排列
 */
public class Question12 {
    public static void main(String[] args) {
//        print1ToMaxOfDigits(3);

        print1ToMaxOfDigits_1(3);

        System.out.println(list2NumString(Arrays.asList(0, 0, 1)));
        System.out.println(list2NumString(Arrays.asList(1, 0, 1)));
        System.out.println(list2NumString(Arrays.asList(1, 1, 0)));
    }

    /**
     * 使用大数加法, 字符串模拟
     */
    static void print1ToMaxOfDigits(int n) {
        if(n <= 0) {
            return;
        }

        String max = max(n);
        String cur = "0";
        do {
            System.out.println(cur);
            cur = inc(cur);
        } while (!max.equals(cur));
    }

    /**
     * dfs，全排列
     */
    static void print1ToMaxOfDigits_1(int n) {
        List<String> result = new ArrayList<>();
        int[] nums = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        Deque<List<Integer>> stack = new ArrayDeque<>();
        for (int i = 0; i < 10; i++) {
            stack.push(Arrays.asList(i));
        }

        while (!stack.isEmpty()) {
            List<Integer> per = stack.pop();

            // 终止条件
            if(per.size() == n) {
                result.add(list2NumString(per));
                continue;
            }

            for (int num : nums) {
                List<Integer> toSearch = new ArrayList<>(per);
                toSearch.add(num);

                stack.push(toSearch);
            }
        }

        System.out.println(result);
    }

    /**
     * list 转换为字符串形式的数字，去掉首部的 0
     * @param list
     * @return
     */
    private static String list2NumString(List<Integer> list) {
        StringBuilder builder = new StringBuilder(list.size());
        boolean first = true;
        for (Integer v : list) {
            if(v == 0 && first) {
                continue;
            }

            first = false;
            builder.append(v);
        }
        return builder.toString();
    }

    /**
     * n位数的最大值
     */
    private static String max(int n) {
        StringBuilder builder = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            builder.append('9');
        }
        return builder.toString();
    }

    private static String inc(String input) {
        StringBuilder builder = new StringBuilder(input.length() + 1);

        // 先单独计算最后一位
        char last = (char) (input.charAt(input.length() - 1) + 1);
        int carry = 0;
        if(last - '9' > 0) {
            carry = 1;
            builder.append('0');
        } else {
            builder.append(last);
        }

        // 计算前面的进位
        for (int i = input.length() - 2; i >= 0; i--) {
            int v = input.charAt(i) - '0' + carry;
            if(v >= 10) {
                builder.append('0');
                carry = 1;
            } else {
                builder.append(v);
                carry = 0;
            }
        }
        if(carry == 1) {
            builder.append(carry);
        }
        return builder.reverse().toString();
    }
}