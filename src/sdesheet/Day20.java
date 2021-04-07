package sdesheet;

import java.awt.image.BandedSampleModel;

public class Day20 {

    private static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    TreeNode GetSuccessor(TreeNode root, TreeNode curr) {


        if (curr == null)
            return null;


        // Case 1: Node has right subtree

        if (curr.right != null) {
            return FindMin(root.right);
        }

        // Case 2: No right subtree
        TreeNode successor = null;

        TreeNode ancestor = root;

        while(ancestor != curr) {
            if (curr.val < ancestor.val) {
                successor = ancestor;
                ancestor = ancestor.left;
            } else {
                ancestor = ancestor.right;
            }
        }

        return ancestor;

    }

    TreeNode FindMin(TreeNode root) {
        if (root == null)
            return null;

        while(root.left != null) {
            root = root.left;
        }

        return root;
    }



}
