package com.sununiq.snippet.jianzhioffer;

import com.sununiq.snippet.jianzhioffer.domain.ListNode;

/**
 * 翻转单链表
 */
public class Question16 {
    public static void main(String[] args) {
        ListNode first = new ListNode(1);
        first.next(2).next(3).next(4).next(5);

        System.out.println(revertList(first));
    }

    static ListNode revertList(ListNode head) {
        if(head == null || head.next == null) {
            return null;
        }

        ListNode pre = head;
        ListNode cur = pre.next;
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
