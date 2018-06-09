package com.sununiq.snippet.jianzhioffer;

import com.sununiq.snippet.jianzhioffer.domain.TreeNode;

/**
 * 重建二叉树，给定一个前序，中序，重建二叉树，假设节点中，没有重复的值。
 *
 * FIXME: 不熟练
 */
public class Question6 {
    public static void main(String[] args) {
        int[] first = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] middle = {4, 7, 2, 1, 5, 3, 8, 6};

        TreeNode treeNode = constructTree(first, middle);
        printPreOrder(treeNode);
    }

    public static TreeNode constructTree(int[] pre, int[] in) {
        return reConstructBinaryTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    private static TreeNode reConstructBinaryTree(int[] pre, int startPre, int endPre, int[] in, int startIn, int endIn) {

        if(startPre > endPre || startIn > endIn) {
            return null;
        }
        TreeNode root = new TreeNode(pre[startPre]);

        for (int i = startIn; i <= endIn; i++)
            if(in[i] == pre[startPre]) {
                root.left = reConstructBinaryTree(pre, startPre + 1, startPre + i - startIn, in, startIn, i - 1);
                root.right = reConstructBinaryTree(pre, i - startIn + startPre + 1, endPre, in, i + 1, endIn);
            }

        return root;
    }

    public static void printPreOrder(TreeNode node) {
        if(node != null) {
            System.out.println(node.value);
            printPreOrder(node.left);
            printPreOrder(node.right);
        }
    }


}

