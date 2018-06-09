package com.sununiq.snippet.jianzhioffer;

/**
 * 重建二叉树
 */
public class Question6 {
    public static void main(String[] args) {

    }

    static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        TreeNode(int value) {
            this.value = value;
        }

        TreeNode left(int value) {
            TreeNode treeNode = new TreeNode(value);
            this.left = treeNode;
            return treeNode;
        }

        TreeNode right(int value) {
            TreeNode treeNode = new TreeNode(value);
            this.right = treeNode;
            return treeNode;
        }
    }
}

