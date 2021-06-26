package companies;


import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DPProblem {

    public int maxProduct(int[] nums) {

        int max_overall = nums[0];
        int max_ending_here = nums[0], min_ending_here = nums[0];

        for(int i = 1; i < nums.length; ++i){
            int tmp = max_ending_here;
            max_ending_here = Math.max(nums[i], Math.max(nums[i]*max_ending_here, nums[i]*min_ending_here));
            min_ending_here = Math.min(nums[i], Math.min(nums[i]*tmp, nums[i]*min_ending_here));
            max_overall = Math.max(max_overall, max_ending_here);
        }
        return max_overall;

    }

    public boolean wordBreak(String s, List<String> wordDict) {

        int[] dp = new int[s.length()];

        HashSet<String> words = new HashSet<>(wordDict);

        for(int end = 0; end < s.length(); end++) {
            StringBuilder builder = new StringBuilder();
            for(int start = end; start >= 0; start--) {

                builder.insert(0, s.charAt(start));

                if (words.contains(builder.toString())) {
                    if (start > 0) {
                        dp[end] += dp[start-1];
                    } else {
                        dp[end] = 1;
                    }
                }
            }
        }

        return dp[s.length() - 1] != 0;

    }


    public int longestPalindromeSubseq(String s) {
        int n = s.length();

        int[][] dp = new int[n][n];

        for(int len = 1; len <= n; len++) {
            for (int i = 0; i < n - len; i++) {
                int j = i + len - 1;

                if (i == j) {
                    dp[i][j] = 1;
                } else if (j == i + 1) {
                    if (s.charAt(i) == s.charAt(j))
                        dp[i][j] = 2;
                } else {
                    if (s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = 2 + dp[i+1][j-1];
                    } else {
                        dp[i][j] = Math.max(dp[i][j-1], dp[i+1][j]);
                    }
                }
            }
        }

        return dp[n-1][n-1];
    }


    public int connectSticks(int[] sticks) {

        if (sticks == null || sticks.length == 0) {
            return 0;
        }

        if (sticks.length == 1) {
            return sticks[0];
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int ele : sticks) {
            pq.add(ele);
        }

        int totalCost = 0;

        while(pq.size() > 1) {

            int a = pq.remove();
            int b = pq.remove();

            int cost = a + b;

            totalCost += cost;

            pq.add(totalCost);
        }

        return totalCost;
    }

    public int totalNQueens(int n) {
        int[][] board = new int[n][n];

        boolean[] cols = new boolean[n];
        boolean[] diags = new boolean[2 * n];
        boolean[] revDiags = new boolean[2 * n];

        return populateQueen(board, 0, 0, cols, diags, revDiags);
    }

    private int populateQueen(int[][] board, int row, int col, boolean[] cols, boolean[] diags, boolean[] revDiags) {
        if (row < 0 || row >= board.length) {
            return 0;
        }

        if (col < 0 || col >= board[0].length) {
            return 0;
        }

        int diagIndex = row + col;
        int revDiagIndex = (row - col + board.length) % board.length;

        if (cols[col] || diags[diagIndex] || revDiags[revDiagIndex]) {
            return 0;
        }

        if (row == board.length) {
            return 1;
        }

        cols[col] = true;
        diags[diagIndex] = true;
        revDiags[revDiagIndex] = true;

        int result = 0;

        for(int i = 0; i < board[0].length; i++) {
            result += populateQueen(board, row + 1, i, cols, diags, revDiags);
        }

        cols[col] = false;
        diags[diagIndex] = false;
        revDiags[revDiagIndex] = false;

        return result;
    }

    public int mySqrt(int x) {
        int left = 0;
        int right = x;
        
        int mid = 0;

        while (left <= right) {
            mid = (left + right)/2;

            if ( mid * mid == x) {
                return mid;
            } else if (mid * mid < x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return mid;
    }

}
