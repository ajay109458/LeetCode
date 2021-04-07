package sdesheet;

public class DynamicProgramming {

    public static int longestIncreasingSubsequence(int[] arr) {

        int n = arr.length;
        int[] dp = new int[n];

        for (int i = 0; i < arr.length; i++) {
            dp[i] = 1;

            for(int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        return dp[n-1];
    }

    public static int countPalindromicSubsequence(String input) {

        int n = input.length();
        boolean[][] dp = new boolean[n][n];

        int count = 0;

        for(int len =1 ; len <= n; len++) {
            for(int i = 0; i < n - len +1; i++) {
                int j = i + len;

                if (i == j) {
                    dp[i][j] = true;
                    count++;
                } else if (j == i + 1 && input.charAt(i) == input.charAt(j)) {
                    dp[i][j] = true;
                    count++;
                } else if (input.charAt(i) == input.charAt(j) && dp[i+1][j-1]) {
                    dp[i][j] = true;
                    count++;
                }
            }
        }

        return count;
    }



}
