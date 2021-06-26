package grokking;

import tree.TreeNode;

import java.lang.reflect.Array;
import java.util.*;

public class BFS {

    public static List<List<Integer>> bfs(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);

        List<Integer> list = new ArrayList<>();

        while(!queue.isEmpty()) {

            TreeNode rem = queue.remove();
            list.add(rem.val);

            if (rem == null) {

                result.add(list);

                if (queue.isEmpty()) {
                    break;
                }

                list = new ArrayList<>();
                queue.add(null);
            } else {
                if (rem.left != null) {
                    queue.add(rem);
                }

                if (rem.right != null) {
                    queue.add(rem);
                }
            }

        }

        return result;

    }

    public static void zigZag(TreeNode root) {

        boolean isLeftToRight = true;

        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> childStack = new Stack<>();

        stack.add(root);

        while(true) {
            while (!stack.isEmpty()) {
                TreeNode rem = stack.pop();
                System.out.println(rem.val);

                if (isLeftToRight) {
                    if (rem.left != null) {
                        childStack.add(rem.left);
                    }

                    if (rem.right != null) {
                        childStack.add(rem.right);
                    }
                } else {
                    if (rem.right != null) {
                        childStack.add(rem.right);
                    }

                    if (rem.left != null) {
                        childStack.add(rem.left);
                    }
                }
            }

            isLeftToRight = !isLeftToRight;

            if (childStack.isEmpty())
                break;

            stack = childStack;
            childStack = new Stack<>();
        }

    }

}
