package prepcoding;

import tree.TreeNode;

public class BSTProblems {

    public static TreeNode min(TreeNode root) {
        if (root == null) {
            return null;
        }

        if (root.left == null && root.right == null) {
            return root;
        }

        return min(root.left);
    }

    public static TreeNode max(TreeNode root) {
        if (root == null) {
            return null;
        }

        if (root.left == null && root.right == null) {
            return root;
        }

        return max(root.right);
    }

    public static boolean find(TreeNode root, TreeNode node) {

        if (root == null) {
            return false;
        }

        if (root.val == node.val) {
            return true;
        } else if (node.val < root.val) {
            return find(root.left, node);
        } else {
            return find(root.right, node);
        }
    }

    public static int size(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return 1 + Math.max(size(root.left), size(root.right));
    }

    public static TreeNode addNode(TreeNode root, int val) {

        if (root == null) {
            return new TreeNode(val);
        }

        if (root.val <= val) {
            root.left = addNode(root.left, val);
        } else if (root.val > val) {
            root.right = addNode(root.right, val);
        }

        return root;
    }

    public static TreeNode removeNode(TreeNode root, int val) {
        if (root == null) {
            return null;
        }

        if (root.val == val) {
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                TreeNode max = max(root.left);
                root.val = max.val;
                root.left = removeNode(root.left, max.val);
                return root;
            }
        } else if (val < root.val) {
            return removeNode(root.left, val);
        } else if (val > root.val) {
            return removeNode(root.right, val);
        }

        return root;
    }

    static int sum = 0;
    public static void replaceSumOfTheLarger(TreeNode root) {
        if (root == null) {
            return;
        }

        replaceSumOfTheLarger(root.right);
        int temp = root.val;
        root.val = sum;
        sum += temp;
        replaceSumOfTheLarger(root.left);
    }

    public static TreeNode findLCAofBST(TreeNode root, int val1, int val2) {

        if (root == null) {
            return null;
        }

        if (val1 < root.val && val2 < root.val) {
            return findLCAofBST(root.left, val1, val2);
        } else if (val1 > root.val && val2 > root.val) {
            return findLCAofBST(root.right, val1, val2);
        }

        return null;
    }

    public static void printInRange(TreeNode root, int val1, int val2) {
        if (root == null) {
            return;
        }

        if (val1 < root.val && val2 < root.val) {
            printInRange(root.left, val1, val2);
        } else if (val1 > root.val && val2 > root.val) {
            printInRange(root.right, val1, val2);
        } else {
            printInRange(root.left, val1, val2);
            System.out.println(root.val);
            printInRange(root.right, val1, val2);
        }
    }


    public static boolean travelAndFind(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        boolean isFound = travelAndFind(root.left, sum);
        if (isFound) {
            return true;
        }

        if (find(root, new TreeNode(sum - root.val))) {
            return true;
        }

        return travelAndFind(root.right, sum);
    }

    public static boolean targetSumInBST(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }


        boolean isFind = targetSumInBST(root.left, sum);

        int rSum  = sum - root.val;
        if (find(root, new TreeNode(rSum))) {
            return true;
        }

        return isFind || targetSumInBST(root.right, sum);
    }
}
