package Algoexpert;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class EasyProblems {


    public static boolean isValidSubsequence(List<Integer> array, List<Integer> sequence) {
        int m = array.size();
        int n = sequence.size();

        int[][] dp = new int[m+1][n+1];

        for(int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else {
                    if (array.get(i) == sequence.get(j)) {
                        dp[i][j] = 1 + dp[i-1][j-1];
                    } else {
                        dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                    }
                }
            }
        }

        return dp[m][n] == sequence.size();
    }

    public int[] sortedSquaredArray(int[] array) {

        int n = array.length;

        if ( n == 0)
            return array;

        int left = 0;
        int right = n - 1;

        int index = n- 1;

        int[] result = new int[n];

        while(left <= right) {

            int leftProduct = array[left] * array[left];
            int rightProduct = array[right] * array[right];

            if (leftProduct <= rightProduct) {
                result[index--] = rightProduct;
                right--;
            } else {
                result[index--] = leftProduct;
                left++;
            }
        }

        // Write your code here.
        return result;
    }

    public static int findClosestValueInBst(TreeNode root, int target) {

        if (root == null) {
            return -1;
        }

        if (target < root.val) {

            int closest = findClosestValueInBst(root.left, target);
            if (closest == -1) {
                return root.val;
            }

            return (Math.abs(closest - target) < Math.abs(root.val - target)) ? closest : root.val;

        } else if (target > root.val) {
            int closest = findClosestValueInBst(root.right, target);
            if (closest == -1) {
                return root.val;
            }

            return (Math.abs(closest - target) < Math.abs(root.val - target)) ? closest : root.val;
        } else {
            return root.val;
        }
    }


    public static List<Integer> branchSums(TreeNode root) {
        // Write your code here.
        List<Integer> branches = new ArrayList<>();

        populateBranchSum(root, 0, branches);

        return branches;
    }

    private static void populateBranchSum(TreeNode root, int sum, List<Integer> list) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            sum += root.val;
            list.add(sum);
            return;
        }

        populateBranchSum(root.left, sum + root.val, list);
        populateBranchSum(root.right, sum + root.val, list);
    }

    public static int nodeDepths(TreeNode root) {
        return nodeDepths(root, 0);
    }

    private static int nodeDepths(TreeNode root, int level) {
        if (root == null)
            return 0;

        return level + nodeDepths(root.left, level + 1) + nodeDepths(root.right, level + 1);
    }
}
