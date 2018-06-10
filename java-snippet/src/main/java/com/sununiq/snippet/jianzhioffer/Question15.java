package com.sununiq.snippet.jianzhioffer;

import com.sununiq.snippet.jianzhioffer.domain.ListNode;

/**
 * 链表中第K个节点，双指针法
 * Java 中不需要考虑有符号整形的情况
 */
public class Question15 {
    public static void main(String[] args) {
        ListNode first = new ListNode(1);
        ListNode s = new ListNode(2);
        ListNode t = new ListNode(3);
        ListNode f = new ListNode(4);
        ListNode fi = new ListNode(5);

        first.next = s;
        s.next = t;
        t.next = f;
        f.next = fi;

        System.out.println(findKthToTail(first, 6));
        System.out.println(findKthToTail(first, 1));
        System.out.println(findKthToTail(first, 5));
    }

    static ListNode findKthToTail(ListNode head, int k) {
        if(head == null || k == 0) {
            return null;
        }

        ListNode p1 = head;
        ListNode p2 = head;

        // 注意边界值的判断, p1 先走k-1个节点
        for (int i = 0; i < k - 1; i++) {
            if(p1.next != null) {
                p1 = p1.next;
            } else {
                return null;
            }
        }

        while (p1.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
    }
}
