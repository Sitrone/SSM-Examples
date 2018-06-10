package com.sununiq.snippet.jianzhioffer;

import com.sununiq.snippet.jianzhioffer.domain.TreeNode;

/**
 * 树的子结构
 * TODO 加强理解递归判断
 * 思路:1. 先考虑特殊情况，怎么判断两棵树是否相等
 *     2. 分解成第一棵树，如果节点和第二棵树节点相等，调用1中的判断，否则继续往下比较
 */
public class Question18 {
    public static void main(String[] args) {

    }

    boolean hasSubTree(TreeNode t1, TreeNode t2) {
        boolean hasSubTree = false;
        if (t1 == null || t2 == null) {
            return false;
        }

        if (t1.value == t2.value) {
            hasSubTree = true;
        }

        if (hasSubTree) {
            hasSubTree = doHasSubTree(t1, t2);
        }

        if (!hasSubTree) {
            hasSubTree = hasSubTree(t1.left, t2);
        }

        if (!hasSubTree) {
            hasSubTree = hasSubTree(t1.right, t2);
        }

        return hasSubTree;
    }

    /**
     * 相同起点开始，判断t2是否是t1的子树
     * @param t1
     * @param t2
     * @return
     */
    boolean doHasSubTree(TreeNode t1, TreeNode t2) {
        if (t2 == null) {
            return true;
        }
        if (t1 == null) {
            return false;
        }

        if (t1.value != t2.value) {
            return false;
        }

        return doHasSubTree(t1.left, t2.left) && doHasSubTree(t1.right, t2.right);
    }
}
