package sdesheet;

import graph.Node;
import tree.TreeNode;

public class Day21 {

    public static int largestBST(TreeNode root) {
        return getLargestBST(root).ans;
    }

    private static NodeInfo getLargestBST(TreeNode root) {
        if (root == null) {
            return new NodeInfo(true, Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
        }

        if (root.left == null && root.right == null) {
            return new NodeInfo(true, root.val, root.val, 1);
        }

        NodeInfo leftInfo = getLargestBST(root.left);
        NodeInfo rightInfo = getLargestBST(root.right);

        NodeInfo currInfo = new NodeInfo();

        if (leftInfo.isBST && rightInfo.isBST && leftInfo.max < root.val && rightInfo.min > root.val) {
            currInfo.min = Math.min(leftInfo.min, root.val);
            currInfo.max = Math.max(rightInfo.max, root.val);

            currInfo.ans = leftInfo.ans + 1 + rightInfo.ans;
            currInfo.isBST = true;
            return currInfo;
        }

        currInfo.ans = Math.max(leftInfo.ans, rightInfo.ans);
        currInfo.isBST = false;
        return currInfo;
    }

    private static class NodeInfo {
        public boolean isBST;
        public int max;
        public int min;
        public int ans;

        public NodeInfo() {}

        public NodeInfo(boolean isBST, int max, int min, int ans) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
            this.ans = ans;
        }
    }

}
