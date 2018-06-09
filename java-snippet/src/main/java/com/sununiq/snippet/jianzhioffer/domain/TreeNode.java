package com.sununiq.snippet.jianzhioffer.domain;

public class TreeNode {
    public int value;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int value) {
        this.value = value;
    }

    public TreeNode left(int value) {
        TreeNode treeNode = new TreeNode(value);
        this.left = treeNode;
        return treeNode;
    }

    public TreeNode right(int value) {
        TreeNode treeNode = new TreeNode(value);
        this.right = treeNode;
        return treeNode;
    }
}
