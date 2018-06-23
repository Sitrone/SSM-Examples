package com.sununiq.snippet.jianzhioffer;

import com.sununiq.snippet.jianzhioffer.domain.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: java-snippet
 *
 * @description: 从上到下打印二叉树, 广度优先，queue
 *
 * @author: sununiq
 *
 * @create: 2018-06-23 11:05
 **/
public class Question23 {
    public static void main(String[] args) {
        TreeNode head = new TreeNode(8);
        TreeNode left = head.left(6);
        TreeNode right = head.right(10);

        left.left(5);
        left.right(7);

        right.left(9);
        right.right(11);

        printFromTop2Bottom(head);
    }

    static void printFromTop2Bottom(TreeNode head) {
        if (head == null) {
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(head);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            System.out.println(cur.value);

            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }
    }
}
