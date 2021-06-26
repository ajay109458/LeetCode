package grokking;

import tree.TreeNode;
import tree.TreeProblems;
import utils.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DFS {

    public static boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return (sum == 0);
        }

        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    public static int countAllPaths(TreeNode root, int sum) {
        if (root == null || sum <= 0) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            if (root.val == sum) {
                return 1;
            }
        }

        return countAllPaths(root.left, sum - root.val) + countAllPaths(root.right, sum - root.val);
    }


    public static List<List<Integer>> getAllPath(TreeNode root, int S) {
        List<List<Integer>> paths = new ArrayList<>();

        populateAllPaths(root, S, paths, new ArrayList<>());

        return paths;
    }

    private static  void populateAllPaths(TreeNode root, int S, List<List<Integer>> paths, List<Integer> currPath) {
        if (root == null) {
            return;
        }

        currPath.add(root.val);

        if (root.left == null && root.right == null) {
            List<Integer> clone = new ArrayList<>();
            for(int val : currPath) {
                clone.add(val);
            }

            paths.add(clone);
            return;
        }

        populateAllPaths(root.left, S, paths, currPath);
        populateAllPaths(root.right, S, paths, currPath);
    }

    public static int sumOfPaths(TreeNode root) {
        return sumOfPathsInternal(root, 0);
    }

    private static int sumOfPathsInternal(TreeNode root, int numTillNow) {
        if (root == null) {
            return 0;
        }

        numTillNow = numTillNow * 10 + root.val;

        if (root.left == null && root.right == null) {
            return numTillNow;
        }

        return sumOfPathsInternal(root.left, numTillNow) + sumOfPathsInternal(root.right, numTillNow);
    }

    public static boolean isPathSequenceExists(TreeNode root, int[] pathSequence) {
        return isPathSeqExistInternal(root, pathSequence, 0);
    }

    private static boolean isPathSeqExistInternal(TreeNode root, int[] pathSequence, int index) {
        if (root == null) {
            return pathSequence.length == index;
        }

        if (pathSequence[index] != root.val) {
            return false;
        }

        return isPathSeqExistInternal(root.left, pathSequence, index + 1) || isPathSeqExistInternal(root.right, pathSequence, index + 1);
    }



    private static boolean isAnyPathContainsSum(TreeNode root, int K) {
        return isPathContainsSum(root, 0, K, new HashSet<>());
    }

    private static boolean isPathContainsSum(TreeNode root, int sum, int K, HashSet<Integer> set) {
        if (root == null) {
            return false;
        }

        sum += root.val;
        if (set.contains(sum - K)) {
            return true;
        }
        set.add(root.val);

        return isPathContainsSum(root.left, sum, K, set) || isPathContainsSum(root.right, sum, K, set);
    }


    public static int diameterOfTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = TreeProblems.getMaxDepth(root.left);
        int rightHeight = TreeProblems.getMaxDepth(root.right);

        return Math.max(1 + leftHeight + rightHeight, Math.max(diameterOfTree(root.left), diameterOfTree(root.right)));
    }

    public static int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftSum = TreeProblems.maxRootToLeafSum(root.left);
        int rightSum = TreeProblems.maxRootToLeafSum(root.right);

        return Math.max(root.val + leftSum + rightSum, Math.max(maxPathSum(root.left), maxPathSum(root.right)));
    }
}
