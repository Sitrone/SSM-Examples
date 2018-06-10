package com.sununiq.snippet.jianzhioffer;

import com.sununiq.snippet.jianzhioffer.domain.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 从尾到头打印链表,
 * 1. stack
 * 2. 递归解决
 */
public class Question5 {
    public static void main(String[] args) {
        ListNode first = new ListNode(1);
        first.next(2).next(3).next(4).next(5);

        printFromTail(first);

        printFromTailWithStack(first);

        ListNode last = revert(first);

        System.out.println(last.value);
    }

    public static void printFromTailWithStack(ListNode head) {
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
    public static void printFromTail(ListNode head) {
        if(head != null) {
            printFromTail(head.next);
            System.out.println(head.value);
        }
    }

    /**
     * 翻转单链表
     */
    public static ListNode revert(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }

//        ListNode cur = head;
//        ListNode next = cur.next;
//        while (next != null) {
//            ListNode temp = next.next;
//            next.next = cur;
//            cur = next;
//            next = temp;
//        }
//        head.next = null;
//        return cur;

        ListNode pre = head;
        ListNode cur = head.next;
        ListNode temp;
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

