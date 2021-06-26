package sdesheet;

import graph.Node;
import tree.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Day21 {

    public static TreeNode getCielValue(TreeNode root, int val) {
        if (root == null) {
            return null;
        }

        if (root.val == val) {
            return root;
        }

        if (val < root.val) {
            return getCielValue(root.left, val);
        }

        TreeNode floorValue = getCielValue(root.right, val);

        return floorValue == null ? root : floorValue;
    }

    /**
     *
     * Method 1 :
     *  Inorder traversal, Return K-1 th index element
     *  Time Complexity : O(N)
     *
     * Method 2 :
     *  Recursion and count the Kth element during the inorder traversal
     *  Return the element when K = 0
     *  Time Complexity : O(N)
     *
     * Method 3 :
     *
     *
     */
    private static class TreeNodeWrapper {
        int leftChildCounts = 0;
        TreeNode root;

        public TreeNodeWrapper(TreeNode root) {
            this.root = root;
        }
    }

    public static TreeNode getKthSmallestBST(TreeNodeWrapper root, int k) {
        if (root == null) {
            return null;
        }

        int count = root.leftChildCounts + 1;
        if (count == k) {
            return root.root;
        }

        if (count > k) {
            return getKthSmallestBST(new TreeNodeWrapper(root.root.left), k);
        }

        return getKthSmallestBST(new TreeNodeWrapper(root.root.left), k - count);

    }


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

    public static class SerializerAndDeserializer {

        public static String serialize(TreeNode root) {
            if (root == null) {
                return "X,";
            }

            String leftSerialize = serialize(root.left);
            String rightSerialize = serialize(root.right);

            return root.val + "," + leftSerialize + rightSerialize;
        }

        public static TreeNode deserialize(String s) {
            Queue<String> queue = new LinkedList<>();
            queue.addAll(Arrays.asList(s.split(",")));
            return deserializerHelper(queue);
        }

        public static TreeNode deserializerHelper(Queue<String> queue) {
            String value = queue.poll();

            if (value.equals("X"))
                return null;

            TreeNode node = new TreeNode(Integer.parseInt(value));
            node.left = deserializerHelper(queue);
            node.right = deserializerHelper(queue);

            return node;
        }

    }

}
