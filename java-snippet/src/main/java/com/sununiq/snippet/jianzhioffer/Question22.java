package com.sununiq.snippet.jianzhioffer;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @program: java-snippet
 *
 * @description: 栈的压入，弹出序列
 *
 * @author: sununiq FIXME
 *
 * @create: 2018-06-23 10:38
 **/
public class Question22 {
    public static void main(String[] args) {

    }

    static boolean isPopOrder(int[] push, int[] pop) {
        if (push == null || pop == null) {
            return false;
        }

        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0, popIndex = 0; i < pop.length; i++) {
            stack.push(push[i]);

            // 验证出栈是否合理的方法就是先入栈再出栈
            // 如果栈不为空，且栈顶元素等于弹出序列
            while (popIndex < pop.length && stack.peek() == pop[popIndex]) {
                stack.pop();  // 出栈
                popIndex++;  // 弹出序列向后一位
            }
        }

        return stack.isEmpty();
    }
}
