package GeeksForGeeks;

public class DynamicProgramming {
    public static int longestCommonSubsequence(String input1, String input2) {
        int m = input1.length();
        int n = input2.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (input1.charAt(i - 1) == input2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }

    public static int longestIncreasingSubsequence(int[] arr) {
        int m = arr.length;

        int[] dp = new int[m];

        for(int i = 0; i < m; i++) {
            dp[i] = 1;
        }

        int max = 0;
        for (int i = 1; i < m; i++) {
            for (int j = i-1; j >= 0; j--) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            max = Math.max(max, dp[i]);
        }

        return max;
    }

    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m + 1][n + 1];

        for(int i = 0; i < word1.length(); i++) {
            for(int j = 0; j < word2.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                }
            }
        }

        return dp[m][n];
    }
}
