package com.sununiq.snippet.jianzhioffer;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @program: java-snippet
 *
 * @description: 包含min函数的栈, 利用辅助栈来保存最小的元素的列表
 *
 * @author: sununiq
 *
 * @create: 2018-06-23 10:17
 **/
public class Question21 {
    public static void main(String[] args) {
        MStack<Integer> stack = new MStack<>();
        stack.push(3);
        stack.push(4);
        stack.push(2);
        stack.push(1);

        Integer pop1 = stack.pop();
        System.out.println(pop1);
        System.out.println(stack.min());

        Integer pop2 = stack.pop();
        System.out.println(pop2);
        System.out.println(stack.min());

        stack.push(0);
    }

    static class MStack<T extends Comparable<T>> {
        private Deque<T> stack = new ArrayDeque<>();
        private Deque<T> aux = new ArrayDeque<>();

        void push(T t) {
            if (stack.isEmpty()) {
                aux.push(t);
            } else {
                T temp = aux.peek();

                // 辅助栈放入较小的元素，辅助栈的元素和原栈一样多
                if (t.compareTo(temp) < 0) {
                    aux.push(t);
                } else {
                    aux.push(temp);
                }
            }

            stack.push(t);
        }

        T pop() {
            aux.pop();
            return stack.pop();
        }

        T min() {
            return aux.peek();
        }
    }

}
