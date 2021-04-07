package sdesheet;

import java.util.Arrays;

public class Day25 {

    public static int maxValueKnapsack(int[] wts, int[] profits, int W) {

        if (W == 0) {
            return 0;
        }

        if (wts.length == 0) {
            return 0;
        }

        int[][] dp = new int[wts.length + 1][W + 1];

        for(int i = 0; i <= wts.length; i++) {
            for(int j = 0; j <= W; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else {

                    if (j < wts[i]) {
                        dp[i][j] = dp[i-1][j];
                    } else {
                        dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-wts[i]] + profits[i]);
                    }

                }
            }
        }


        return dp[wts.length][W];
    }


    public static int numberOfWaysCoinInfinite(int[] coins, int amount) {
        if (coins.length == 0)
            return 0;

        int[] dp = new int[amount+1];
        dp[0] = 1;

        for(int amt = 1; amt <= amount; amt++) {
            for(int coin : coins) {
                if (amt >= coin) {
                    dp[amt] += dp[amt-coin];
                }
            }
        }

        return dp[amount];
    }

    public static int numberOfWaysCoin(int[] coins, int amount) {
        if (coins.length == 0)
            return 0;

        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for(int coin : coins) {
            for(int amt = 0; amt <= amount; amt++) {
                dp[amt] += dp[amt-coin];
            }
        }

        return dp[amount];
    }

    public static int rodCutting(int[] prices) {
        int[] np = new int[prices.length + 1];

        for (int i = 0; i < prices.length; i++) {
            np[i+1] = prices[i];
        }

        int[] dp = new int[np.length];

        dp[0] = 0;
        dp[1] = np[1];

        for(int i = 2; i < dp.length; i++) {
            dp[i] = np[i];

           for(int j = 1; j <= i; j++) {
               dp[i] = Math.max(dp[i], np[j] + dp[i-j]);
           }
        }

        return dp[prices.length];
    }

    public int superEggDrop(int k, int n) {

        if (n == 0)
            return Integer.MAX_VALUE;

        int[][] dp = new int[n+1][k+1];

        for(int e = 0; e <= n; e++) {
            for(int f = 0; f <= k; f++) {

                if (e == 0) {
                    dp[e][f] = Integer.MAX_VALUE;
                } else if (f == 0 || f == 1) {
                    dp[e][f] = f;
                } else if (e == 1) {
                    dp[e][f] = f;
                } else {

                    int min = Integer.MAX_VALUE;

                    for(int j = 1; j <= f; j++) {
                        min = Math.min(min, 1+ Math.max(dp[e-1][j-1], dp[e][f-j]));
                    }

                    dp[e][f] = min;
                }
            }

            System.out.println(dp[e]);
        }

        return dp[n][k];
    }

    private class JobInfo {
        int startTime;
        int endTime;
        int profit;
    }


    public static int jobScheduling(JobInfo[] jobs) {

        Arrays.sort(jobs, (j1, j2) -> Integer.compare(j1.profit, j2.profit));

        int[] dp = new int[jobs.length];

        for(int i = 0; i < jobs.length; i++) {
            dp[i] = jobs[i].profit;
        }

        int max = 0;
        for(int i = 1; i < jobs.length; i++) {
            for (int j = i -1; j >= 0; j--) {
                if (jobs[j].endTime <= jobs[i].startTime) {
                    dp[i] = Math.max(dp[i], dp[j] + jobs[i].profit);
                }


            }
            max = Math.max(max, dp[i]);
        }

        return max;

    }


    public static int maxProductSubArray(int[] arr) {

        int maxSoFar = 1;
        int minSoFar = 1;
        int max = 0;

        for(int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) {
                maxSoFar = maxSoFar * arr[i];
                minSoFar = Math.min(1, minSoFar * arr[i]);
            } else if (arr[i] == 0) {
                maxSoFar = 1;
                minSoFar = 1;
            } else if (arr[i] < 0) {

                int temp = maxSoFar;
                maxSoFar = Math.max(1, minSoFar * arr[i]);
                minSoFar = Math.min(temp * arr[i], minSoFar);

            }

            max = Math.max(max, maxSoFar);
        }

        return max;

    }

    public static int matrixChainMultiplication(int[] p) {
        int n = p.length;

        int[][] dp = new int[n][n];

        for(int i = 1; i < n; i++) {
            dp[i][i] = 1;
        }

        for(int L = 2; L < n; L++) {
            for(int i = 1; i < n - L + 1; i++) {
                int j = i + L -1;

                if (j == n)
                    continue;

                dp[i][j] = Integer.MAX_VALUE;

                for(int k = i; k <= j - 1; k++) {

                }

            }
        }

        return dp[n][n];
    }
}
