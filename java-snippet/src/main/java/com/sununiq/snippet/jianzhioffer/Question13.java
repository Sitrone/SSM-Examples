package com.sununiq.snippet.jianzhioffer;

import com.sununiq.snippet.jianzhioffer.domain.Node;

/**
 * 在O(1)时间删除链表节点, 前提，链表中确实存在节点
 */
public class Question13 {
    public static void main(String[] args) {
        Node first = new Node(1);
        Node s = new Node(2);
        Node t = new Node(3);
        Node f = new Node(4);
        Node fi = new Node(5);

        first.next = s;
        s.next = t;
        t.next = f;
        f.next = fi;

        deleteNode(first, fi);

        System.out.println(first);
    }


    static void deleteNode(Node head, Node toBeDeleted) {
        if(head == null || toBeDeleted == null) {
            return;
        }

        Node next = toBeDeleted.next;

        // 链表有多个节点，要删除的不是尾节点:O(1)时间
        if(next != null) {
            toBeDeleted.value = next.value;
            toBeDeleted.next = next.next;
            toBeDeleted = null;

            // 链表只有一个结点，删除头结点（也是尾结点）:O(1)时间
        } else if(head == toBeDeleted) {
            head = null;
            toBeDeleted = null;

            // 链表有多个节点，要删除的是尾节点:O(n)时间
        } else {
            Node temp = head;
            while (temp.next != toBeDeleted) {
                temp = temp.next;
            }

            temp.next = null;
            toBeDeleted = null;
        }
    }
}
