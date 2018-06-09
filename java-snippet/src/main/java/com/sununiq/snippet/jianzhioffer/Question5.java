package com.sununiq.snippet.jianzhioffer;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 从尾到头打印链表,
 * 1. stack
 * 2. 递归解决
 */
public class Question5 {
    public static void main(String[] args) {
        Node first = new Node(1);
        first.next(2).next(3).next(4).next(5);

        printFromTail(first);

        printFromTailWithStack(first);
    }

    public static void printFromTailWithStack(Node head) {
        Deque<Integer> stack = new ArrayDeque<>();
        while (head != null) {
            stack.push(head.value);
            head = head.next;
        }

        System.out.println(stack);
    }

    /**
     * 递归的本质就是个栈结构
     */
    public static void printFromTail(Node head) {
        if (head != null) {
            printFromTail(head.next);
            System.out.println(head.value);
        }
    }


    static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }

        Node next(int value) {
            Node node = new Node(value);
            this.next = node;
            return node;
        }
    }
}

