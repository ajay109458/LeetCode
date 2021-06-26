package prepcoding;

import utils.Pair;

import java.util.Arrays;
import java.util.HashSet;

public class DynamicProgramming2 {

    public static int longestIncreasingSubsequence(int[] arr) {

        int n = arr.length;

        int[] dp = new int[n];

        int max = 0;

        for(int i = 0; i < n; i++) {
            dp[i] = 1;

            for(int j = i -1 ; j >= 0; j--) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            max = Math.max(max, dp[i]);
        }

        return max;
    }

    public static int maximumIncreasingSubsequence(int[] arr) {
        int n = arr.length;


        int[] dp = new int[n];

        int max = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = arr[i];

            for(int j = i -1 ; j >= 0; j--) {
                dp[i] = Math.max(dp[i], dp[j] + arr[i]);
            }

            max = Math.max(max, dp[i]);
        }

        return max;
    }


    public static int longestBitonicSubsequence(int[] arr) {
        int n = arr.length;

        int[] lis = new int[n];

        for(int i = 0; i < n; i++) {
            lis[i] = 1;

            for(int j =  i-1; j >= 0; j--) {
                if (arr[i] > arr[j]) {
                    lis[i] = Math.max(lis[i], lis[j] + 1);
                }
            }
        }

        int[] dis = new int[n];

        for(int i = n - 1; i >= 0; i--) {
            dis[i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (arr[i] > arr[j]) {
                    dis[i] = Math.max(dis[i], dis[j] + 1);
                }
            }
        }

        int max = 0;

        for(int i = 0; i < n; i++) {
            max = Math.max(max, lis[i] + dis[i] -1);
        }

        return max;
    }

    public static int maxNonOverlappingBridges(Pair[] pairs) {

        Arrays.sort(pairs, (a, b) -> Integer.compare(a.x, b.x));

        int n = pairs.length;

        int[] dp = new int[n];

        int max = 0;

        for(int i = 0; i < n; i++ ) {
            dp[i] = 1;

            for(int j = i -1; j >= 0; j--) {
                if (pairs[i].y > pairs[j].y) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            max = Math.max(max, dp[i]);
        }

        return max;
    }

    public static int russianDollProblem(Pair[] pairs) {
        int n = pairs.length;

        Arrays.sort(pairs, (a, b) -> Integer.compare(a.x, b.x));

        int[] dp = new int[n];

        int max = 0;

        for(int i = 0; i < n; i++) {
            dp[i] = 1;

            for(int j = i-1; j >= 0; j--) {
                if (pairs[i].y > pairs[j].y) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            max = Math.max(max, dp[i]);
        }

        return max;
    }

    public static int countPalindromicSubstrings(String input) {
        int n = input.length();

        boolean[][] dp = new boolean[n][n];

        int count = 0;

        for(int len = 1; len <= n; len++) {
            for(int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1;

                if (i == j ) {
                    dp[i][j] = true;
                } else if (i + 1 == j) {
                    dp[i][j] = input.charAt(i) == input.charAt(j);
                } else {
                    if (input.charAt(i) == input.charAt(j)) {
                        dp[i][j] = dp[i+1][j-1];
                    }  else {
                        dp[i][j] = dp[i+1][j] || dp[i][j-1];
                    }
                }

                if (dp[i][j]) {
                    count++;
                }
            }
        }

        return count;
    }

    public static int longestPalindromicSubseq(String input) {
        int n = input.length();

        boolean[][] dp = new boolean[n][n];

        int max = 0;

        for(int len = 1; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1;

                if (i == j) {
                    dp[i][j] = true;
                } else if (i+1 == j) {
                    dp[i][j] = input.charAt(i) == input.charAt(j);
                } else {
                    if (input.charAt(i) == input.charAt(j)) {
                        dp[i][j] = dp[i+1][j-1];
                    } else {
                        dp[i][j] = false;
                    }
                }

                if(dp[i][j]) {
                    max = Math.max(len, max);
                }
            }
        }

        return max;
    }

    public static int wordCounts(String input, HashSet<String> words) {
        int n = input.length();

        int[] dp = new int[n];

        int max = 0;
        for(int i = 0; i < n; i++) {
            StringBuilder builder = new StringBuilder();

            for(int j = i; j >= 0; j--) {
                char ch = input.charAt(j);
                builder.insert(0, j);

                if (words.contains(builder.toString())) {

                    if (j > 0) {
                        dp[i] = Math.max(dp[i], dp[j-i] + 1);
                    } else {
                        dp[i] = 1;
                    }
                }
            }

            max = Math.max(dp[i], max);
        }

        return max;
    }

    public static int largestSquareMatrix(int[][] matrix) {
        int m = matrix.length;
        int n= matrix[0].length;

        int[][] dp = new int[m][n];

        int len = 0;

        for(int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = matrix[i][j];
                } else {
                    if (matrix[i][j] == 1) {
                        dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                    }
                }

                len = Math.max(len, dp[i][j]);
            }
        }

        return len;
    }

    public static int maxNumbersOfAPs(int[] arr) {
        int n = arr.length;

        int[] dp = new int[n];

        int ans = 0;

        for(int i = 2; i < n; i++) {
            if (arr[i] - arr[i-1] == arr[i-1] - arr[i-2]) {
                dp[i] = dp[i-1]  +1;
            }

            ans += dp[i];
        }

        return ans;
    }

    public static int eggDropping(int eggCounts, int floorCounts) {


        int[][] dp = new int[eggCounts+1][floorCounts+1];

        for(int e = 0; e <= eggCounts; e++) {
            for(int f = 0; f <= floorCounts; f++) {
                if (e == 0) {
                    dp[e][f] = Integer.MAX_VALUE;
                } else if (e == 1) {
                    dp[e][f] = f;
                } else if (f == 0 || f == 1) {
                    dp[e][f] = f;
                } else {

                    Integer min = Integer.MAX_VALUE;

                    for(int i = 0; i <= f; i++) {
                        min = 1 + Math.max(dp[e-1][i-1], dp[e][f-i]);
                    }

                    dp[e][f] = min;
                }
            }
        }

        return dp[eggCounts][floorCounts];
    }

    public static int subStringMaxDiffZeroOne(String input) {
        int ans = 0;

        int cSum = 0;
        for(char ch : input.toCharArray()) {
            int val = (ch == '0') ? -1 : 1;
            cSum = val  + ((val > 0) ? cSum : 0);

        }

        return ans;
    }

}
