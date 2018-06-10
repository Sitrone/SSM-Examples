package com.sununiq.snippet.jianzhioffer;

import com.sununiq.snippet.jianzhioffer.domain.ListNode;

/**
 * 合并两个排序的链表
 */
public class Question17 {
    public static void main(String[] args) {
        ListNode first = new ListNode(1);
        first.next(3).next(5).next(7);

        ListNode second = new ListNode(2);
        second.next(4).next(6).next(8);

//        System.out.println(mergeList(first, second));

        System.out.println(mergeList_1(first, second));
    }

    /**
     * 循环方式
     *
     * @param first
     * @param second
     * @return
     */
    static ListNode mergeList(ListNode first, ListNode second) {
        if(first == null && second == null) {
            return null;
        }
        if(first == null) {
            return second;
        }
        if(second == null) {
            return first;
        }

        // 建立一个哨兵节点
        ListNode dummyListNode = new ListNode(-1);
        ListNode p = dummyListNode;
        while (first != null || second != null) {
            if(first != null && second != null) {
                if(first.value > second.value) {
                    p.next = second;
                    second = second.next;
                } else {
                    p.next = first;
                    first = first.next;
                }
            } else if(first != null) {
                p.next = first;
                first = first.next;
            } else {
                p.next = second;
                second = second.next;
            }

            p = p.next;
        }
//        while (first != null && second != null) {
//            if(first.value > second.value) {
//                p.next = second;
//                second = second.next;
//            } else {
//                p.next = first;
//                first = first.next;
//            }
//
//            p = p.next;
//        }
//
//        if(first != null) {
//            p.next = first;
//        } else {
//            p.next = second;
//        }

        return dummyListNode.next;
    }

    /**
     * 递归方式解决
     * @param first
     * @param second
     * @return
     */
    static ListNode mergeList_1(ListNode first, ListNode second) {
        if(first == null) {
            return second;
        }
        if(second == null) {
            return first;
        }

        ListNode head = null;
        if(first.value < second.value) {
            head = first;
            head.next = mergeList_1(first.next, second);
        } else {
            head = second;
            head.next = mergeList_1(first, second.next);
        }

        return head;
    }
}
