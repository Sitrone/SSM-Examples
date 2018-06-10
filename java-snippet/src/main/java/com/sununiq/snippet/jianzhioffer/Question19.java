package com.sununiq.snippet.jianzhioffer;

import com.sununiq.snippet.jianzhioffer.domain.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: java-snippet jianzhi offer
 *
 * @description: 二叉树的镜像, 递归，bfs（queue），dfs（stack）
 *
 * @author: sununiq
 *
 * @create: 2018-06-10 11:35
 **/
public class Question19 {
    public static void main(String[] args) {
        TreeNode t = new TreeNode(8);

        TreeNode left = t.left(6);

        TreeNode left1 = left.left(5);
        TreeNode right1 = left.right(7);

        TreeNode right = t.right(10);
        TreeNode left2 = right.left(9);
        TreeNode right2 = right.right(11);

        //        mirror(t);
        mirror_1(t);
        System.out.println(t);
    }

    /**
     * 递归方式
     * @param head
     */
    static void mirror(TreeNode head) {
        if (head == null) {
            return;
        }

        if (head.left == null && head.right == null) {
            return;
        }

        TreeNode temp = head.left;
        head.left = head.right;
        head.right = temp;

        if (head.left != null) {
            mirror(head.left);
        }

        if (head.right != null) {
            mirror(head.right);
        }
    }

    /**
     * bfs
     * @param head
     */
    static void mirror_1(TreeNode head) {
        if (head == null) {
            return;
        }

        if (head.left == null && head.right == null) {
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(head);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();

            if (cur.left != null || cur.right != null) {
                TreeNode temp = cur.left;
                cur.left = cur.right;
                cur.right = temp;
            }

            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }
    }

    /**
     * dfs
     * @param head
     */
    static void mirror_2(TreeNode head) {
        if (head == null) {
            return;
        }

        if (head.left == null && head.right == null) {
            return;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offer(head);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();

            if (cur.left != null || cur.right != null) {
                TreeNode temp = cur.left;
                cur.left = cur.right;
                cur.right = temp;
            }

            if (cur.left != null) {
                stack.push(cur.left);
            }
            if (cur.right != null) {
                stack.push(cur.right);
            }
        }
    }
}