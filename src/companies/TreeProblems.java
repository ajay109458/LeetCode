package companies;

import tree.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class TreeProblems {

    public static TreeNode treeToDoublyList(TreeNode root) {

        inorderTraversal(root);

        return prev;

    }


    public static TreeNode prev;

    private static void inorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }

        inorderTraversal(root.left);
        if (prev != null) {
            prev.right =  root;
            root.left = prev;
        }

        prev = root;
        inorderTraversal(root.right);
    }

    private static class SerializerDeserializer {

        public String serialize(TreeNode root) {
            if (root == null) {
                return "X,";
            }

            return root.val + "," + serialize(root.left) + serialize(root.right);
        }

        public void deserialize(String input) {
            if (input == null || "".equals(input)) {
                return;
            }

            Queue<String> queue = new LinkedList(Arrays.asList(input.split(",")));
        }

        private TreeNode deserializeHelper(Queue<String> queue) {
            String valForNode = queue.poll();

            if (valForNode.equals("X"))
                return null;

            TreeNode root = new TreeNode(Integer.parseInt(valForNode));
            root.left = deserializeHelper(queue);
            root.right = deserializeHelper(queue);

            return root;
        }

    }

}
