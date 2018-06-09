package com.sununiq.snippet.jianzhioffer;

import com.sununiq.snippet.jianzhioffer.domain.Node;

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

        Node last = revert(first);

        System.out.println(last.value);
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
        if(head != null) {
            printFromTail(head.next);
            System.out.println(head.value);
        }
    }

    /**
     * 翻转单链表
     */
    public static Node revert(Node head) {
        if(head == null || head.next == null) {
            return head;
        }

//        Node cur = head;
//        Node next = cur.next;
//        while (next != null) {
//            Node temp = next.next;
//            next.next = cur;
//            cur = next;
//            next = temp;
//        }
//        head.next = null;
//        return cur;

        Node pre = head;
        Node cur = head.next;
        Node temp;
        while (cur != null) {
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }

        head.next = null;
        return pre;
    }


}

