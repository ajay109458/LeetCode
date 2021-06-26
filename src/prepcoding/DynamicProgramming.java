package prepcoding;

import java.util.Arrays;

public class DynamicProgramming {

    /**
     * Fibonacci with recursion
     */

    public static int fib(int n) {
        if (n == 0 || n == 1) {
            return n;
        }

        return fib(n -1) + fib(n - 2);
    }

    /**
     * Fibonacci with memoization
     */

    public static int fibMemoization(int n, int[] dp) {
        if (n == 0 || n == 1) {
            return 0;
        }

        if (dp[n] == 0) {
            dp[n] = fib(n-1) + fib(n-2);
        }

        return dp[n];
    }

    public static int fibTabulation(int n) {
        if (n == 0 || n == 1) {
            return n;
        }

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;

        for(int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }

    public static int countAllStairPaths(int n) {
        if (n < 0) {
            return 0;
        }

        if (n == 0) {
            return 1;
        }

        int[] dp = new int[n + 1];
        dp[0] = 1;

        for(int i = 1; i <= n; i++) {
            dp[i] = dp[i-1];

            if (i >= 2) {
                dp[i] += dp[i-2];
            }

            if (i >= 3) {
                dp[i] += dp[i-3];
            }
        }

        return dp[n];
    }

    public static int climbStairsWithVariableJumps(int[] arr) {

        if (arr.length == 0) {
            return 0;
        }

        /**
         * Tabulation steps
         * - Storage and Meaning ( ith element solution )
         */

        int[] dp = new int[arr.length + 1];
        dp[0] = 1;

        for(int i = 1; i <= arr.length; i++) {
            for(int j = 0; j < i; j++) {
                if (j + arr[j] >= i) {
                    dp[i] += dp[j];
                }
            }
        }

        return dp[arr.length];
    }

    public static int minNumberOfJumps(int[] arr) {

        if (arr.length == 0) {
            return 0;
        }

        /**
         * Tabulation steps
         * - Storage and Meaning ( ith element solution )
         */

        int[] dp = new int[arr.length + 1];
        Arrays.fill(arr, Integer.MAX_VALUE);
        dp[0] = 0;

        for(int i = 1; i <= arr.length; i++) {
            for(int j = 0; j < i; j++) {
                if (j + arr[j] >= i && dp[j] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[j] +1);
                }
            }
        }

        return dp[arr.length];
    }

    public static int minCost(int[][] costs) {
        int m = costs.length;
        int n = costs[0].length;

        int[][] dp = new int[m][n];

        for(int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = costs[i][j];
                } else {
                    int topCost = (i > 0 && i < m) ? dp[i-1][j] : Integer.MAX_VALUE;
                    int leftCost = (j > 0 && j < n) ? dp[i][j-1] : Integer.MAX_VALUE;

                    dp[i][j] = costs[i][j] + Math.min(leftCost, topCost);
                }
            }
        }

        return dp[m-1][n-1];
    }

    /**
     *
     * Storage and Meaning :  If I dig from here, how much gold I can collect
     * Direction
     *
     * @param goldMine
     * @return
     */
    public static int goldMine(int[][] goldMine) {

        int rowCount = goldMine.length;
        int colCount = goldMine[0].length;

        int[][] dp = new int[rowCount][colCount];

        for(int col = colCount - 1; col >= 0; col--) {
            for (int row = 0; row < rowCount; row++) {
                dp[row][col] = goldMine[row][col];

                if (col + 1 <  colCount) {

                    int maxValue = goldMine[row][col+1];

                    if (row-1 >=0) {
                        maxValue = Math.max(maxValue, goldMine[row-1][col+1]);
                    }

                    if (row + 1 < rowCount) {
                        maxValue = Math.max(maxValue, goldMine[row+1][col + 1]);
                    }

                    dp[row][col] += maxValue;
                }
            }
        }

        int maxValue = 0;

        for(int i = 0 ; i < rowCount; i++) {
            maxValue = Math.max(maxValue, goldMine[i][0]);
        }

        return maxValue;
    }

    /**
     * Meaning - Kya is array ka koi subset aisa han wo 5 banae
     *
     * @param arr
     * @param targetSum
     * @return
     */
    public static boolean isTargetSumPossibleInSet(int[] arr, int targetSum) {

        int n = arr.length;

        boolean[][] dp = new boolean[n + 1][targetSum + 1];

        for (int i = 0; i <= n; i++) {

            for(int j =0 ; j <= n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if (i == 0) {
                    dp[i][j] = false;
                } else if (j == 0) {
                    dp[i][j] = true;
                } else {
                    dp[i][j] = dp[i-1][j];
                    if (j >= arr[i-1]) {
                        dp[i][j] = dp[i][j] || dp[i][j-arr[i-1]];
                    }
                }
            }
        }

        return dp[arr.length][targetSum];
    }

    public static int numberOfCombinationWithCoins(int[] coins, int amount) {

        if (coins.length == 0) {
            return 0;
        }

        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for(int coin : coins) {
            for (int amt = 1; amt <= amount; amt++) {
                if (amt >= coin) {
                    dp[amt] += dp[amt-coin];
                }
            }
        }

        return dp[amount];

    }

    public static int getCoinSumPermutation(int[] coins, int amount) {
        if (coins.length == 0) {
            return 0;
        }

        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for(int amt = 1; amt <= amount; amt++) {
            for(int coin : coins) {
                if (amt >= coin) {
                    dp[amt] += dp[amt - coin];
                }
            }
        }

        return dp[amount];
    }

    public static int maxProfitKnapsack01(int[] wts, int[] vals, int W) {
        int n = wts.length;

        int[][] dp = new int[n + 1][W + 1];

        for(int i = 0; i < n; i++) {
            for (int j = 0; j <= W; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i-1][j];

                    if (j >= wts[i-1]) {
                        dp[i][j] = Math.max(dp[i][j], vals[i-1] + dp[i-1][j-wts[i-1]]);
                    }
                }
            }
        }

        return dp[n][W];
    }


    public static int computeProfitUnboundedKnapsack(int[] wts, int[] vals, int W) {
        int n = wts.length;

        int[] dp = new int[W+1];
        dp[0] = 0;

        for(int w = 1; w <= W; w++) {
            for (int j = 0; j < wts.length; j++) {
                if (wts[j] <= w) {
                    dp[w] = Math.max(dp[w], dp[w-wts[j]] + vals[j]);
                }
            }
        }

        return dp[W];
    }

    public static int maxProfitUnboundedKnapsack(int[] wts, int[] vals, int W) {

        int[] dp = new int[W + 1];
        dp[0] = 0;

        for(int w = 1; w <= W; w++) {
            for(int j = 0; j < wts.length; j++) {
                if (wts[j] <= w) {
                    dp[w] = Math.max(dp[w], vals[j] + dp[w - wts[j]]);
                }
            }
        }


        return dp[W];
    }

    public static int countOfBinaryStringNoConsecutiveZero(int n) {

        int stringEndingAt0 = 1;
        int stringEndingAt1 = 1;

        for (int i = 2; i <= n; i++) {
            int temp = stringEndingAt0;
            stringEndingAt0 = stringEndingAt1;
            stringEndingAt1 = temp + stringEndingAt1;
        }

        return stringEndingAt0 + stringEndingAt1;
    }

    /**
     * Given N number of plots.
     *
     * At each plot - you can have a building or Space at any plot
     *
     * But no two building should be togther
     *
     */
    public static int arrangeBuildings(int n) {

        int spaceCount = 1;
        int buildingCount = 1;

        for(int i = 2; i <= n; i++) {

            int prevSpace =  spaceCount;
            int prevBuildings = buildingCount;

            spaceCount = prevSpace + prevBuildings;
            buildingCount = prevSpace;
        }


        int buildingsOneSize = spaceCount + buildingCount;

        return buildingsOneSize * buildingsOneSize;
    }

    public static int decodeWayCounts(String input) {
        int n = input.length();

        int[] dp = new int[n+1];
        dp[0] = 1;

        for(int i = 1; i <= n; i++) {
            int firstDigit = input.charAt(i-1) - '0';
            if (firstDigit > 0 && firstDigit <= 9) {
                dp[i] += dp[i-1];
            }

            int second = Integer.parseInt(input.substring(i-2, i));
            if (second >= 10 && second <= 26) {
                dp[i] += dp[i-2];
            }
        }

        return dp[n];
    }

    public static int decodeWaysCount(String input) {
        int n = input.length();
        int[] dp = new int[n+1];
        dp[0] = 0;

        for(int i = 1; i < input.length(); i++) {

            int first = input.charAt(i) - '0';
            if (first > 0 && first < 9) {
                dp[i] = dp[i-1];
            }

            if (i > 1) {
                int second = Integer.parseInt(input.substring(i-2, i));

                if (second >= 10 && second <= 26) {
                    dp[i] += dp[i-2];
                }
            }
        }

        return dp[n];
    }

    public static int minCostPaintHouse(int[][] arr) {

        int colorCount = arr.length;
        if (colorCount == 0) {
            return 0;
        }

        int houseCount = arr[0].length;

        int[][] dp = new int[colorCount][houseCount];

        for(int hIndex = 0; hIndex < houseCount; hIndex++) {
            for(int colIndex = 0; colIndex < colorCount; colIndex++) {

                dp[colIndex][hIndex] = arr[colIndex][hIndex];

                if (hIndex != 0){
                    int minValue = Integer.MAX_VALUE;
                    for(int j =  0; j < colorCount; j++) {
                        if (j !=colIndex) {
                            minValue = Math.min(minValue, arr[j][hIndex]);
                        }
                    }

                    dp[colIndex][hIndex] = minValue;
                }
            }
        }

        int minValue = Integer.MAX_VALUE;
        for(int i = 0; i < colorCount; i++) {
            minValue = Integer.min(minValue, dp[i][arr.length - 1]);
        }

        return minValue;
    }

    /**
     * Wrong solution
     */

    public static int minNumberOfPartitions(String input) {

        int n = input.length();

        boolean[][] dp = new boolean[n][n];



        for(int len = 1; len <= n; len++) {
            for(int i = 0; i <= n - len + 1; i++) {
                int j = i + len - 1;


                if (i == j) {
                    dp[i][j] = true;
                } else if (j == i+ 1) {
                    if (input.charAt(i) == input.charAt(j)) {
                        dp[i][j] = true;
                    }
                } else {
                    if (input.charAt(i) == input.charAt(j) && dp[i+1][j-1]) {
                        dp[i][j] = true;
                    }
                }

            }
        }

        int[][] dpPartition = new int[n][n];
        for(int len = 1; len <= n; len++) {
            for(int i = 0; i <= n - len + 1; i++) {
                int j = i + len - 1;


                if (i == j) {
                    dp[i][j] = true;
                    dpPartition[i][j] = 0;
                } else if (j == i+ 1) {
                    if (input.charAt(i) == input.charAt(j)) {
                        dp[i][j] = true;
                        dpPartition[i][j] = 0;
                    } else {
                        dpPartition[i][j] = 1;
                    }
                } else {
                    if (dp[i][j]) {
                        dpPartition[i][j] = 0;
                    } else {
                        int min = Integer.MAX_VALUE;
                        for(int k = i; k <= j; k++) {
                            int leftCount = dpPartition[i][k];
                            int rightCount = dpPartition[k][j];

                            min = Math.min(min, leftCount + rightCount + 1);
                        }

                        dpPartition[i][j] = min;
                    }
                }

            }

            return dpPartition[0][input.length() - 1];
        }


        return dpPartition[0][input.length() - 1];
    }


    public static int minNumberOfPartitions2(String input) {
        int n = input.length();

        boolean[][] dp = new boolean[n][n];



        for(int len = 1; len <= n; len++) {
            for(int i = 0; i <= n - len + 1; i++) {
                int j = i + len - 1;


                if (i == j) {
                    dp[i][j] = true;
                } else if (j == i+ 1) {
                    if (input.charAt(i) == input.charAt(j)) {
                        dp[i][j] = true;
                    }
                } else {
                    if (input.charAt(i) == input.charAt(j) && dp[i+1][j-1]) {
                        dp[i][j] = true;
                    }
                }

            }
        }

        int[] lenDp = new int[n];

        for(int i = 1; i < n ; i++) {

            if (dp[0][i]) {
                lenDp[i] = 0;
                continue;
            }

            int minLen = Integer.MAX_VALUE;
            for(int j = i; j >= 1; j--) {
                if (dp[i][j]) {
                    minLen = Math.min(minLen, lenDp[i-1]);
                }
            }

            lenDp[i] = minLen + 1;
        }


        return lenDp[dp.length-1];
    }

    public static int fenceColoring(int n, int k) {

        int lastTwoSameColor = k * 1;
        int lastTwoDiffColor = k * (k-1);

        int total = lastTwoDiffColor + lastTwoSameColor;

        for(int i = 3; i <= n; i++) {
            lastTwoSameColor = lastTwoDiffColor;

            lastTwoDiffColor = total * (k-1);

            total = (lastTwoDiffColor + lastTwoSameColor);
        }

        return total;
    }

    public static int tileWithM1(int n, int m) {
        return tileWithM1(n-1, m) + tileWithM1(n-m, m);
    }

    /**
     * Solve using 1D
     * @param n
     * @return
     */
    public static int friendPairing(int n) {
        if (n == 0 || n == 1) {
            return n;
        }

        return friendPairing(n-1) + (n-1) * friendPairing(n-2);
    }

    public static int partitionIntoSets(int n, int k) {

        int[][] dp = new int[n+1][k+1];

        for(int i = 0; i <=n; i++) {
            for(int j=0; j <=k; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (j == 1 || i == j) {
                    dp[i][j] = 1;
                } else if (i < j) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i-1][j-1] + j * dp[i-1][j];
                }
            }
        }

        return dp[n][k];
    }

    public static int catalan(int n) {
        int[] dp = new int[n+1];

        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2; i <= n; i++) {

            int val = 0;
            for(int j = 0; j < 2; j++) {
                val += dp[j] * dp[i-j];
            }

            dp[i] = val;
        }

        return dp[n];
    }

    public static int minFloors(int n, int k) {

        int[][] dp = new int[k+1][n+1];

        for(int i = 0; i <=k ; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {

                } else if (i == 1) {
                    dp[i][j] = j;
                } else if (j == 0 || j ==1) {
                    dp[i][j] = j;
                } else {
                    int min = Integer.MAX_VALUE;

                    for(int x = 0 ; x <= j; x++) {
                        min = Math.min(min, 1 + Math.max(dp[i-1][j-1], dp[i][j-1]));
                    }

                    dp[i][j] = min;
                }
            }
        }

        return dp[k][n];

    }

    public static boolean regexExpMatch(String s, String p) {
        boolean[][] dp = new boolean[p.length() + 1][s.length() + 1];

        for(int i = 0; i < dp.length; i++) {
            for(int j = 0; j < dp[0].length; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if (i == 0) {
                    dp[i][j] = false;
                } else if (j == 0) {
                    if (p.charAt(i-1) == '*') {
                        dp[i][j] =  dp[i-2][j];
                    } else {
                        dp[i][j] = false;
                    }
                } else {
                    char pc = p.charAt(i-1);
                    char sc = s.charAt(j-1);

                    if (pc == sc || pc == '.') {
                        dp[i][j] = dp[i-1][j-1];
                    } else if (pc == '*') {
                        dp[i][j] = dp[i-2][j];

                        char psl = p.charAt(i-2);
                        if (psl == sc || psl == '.') {
                            dp[i][j] = dp[i][j] || dp[i][j-1];
                        }
                    }
                }
            }
        }

        return dp[p.length()][s.length()];
    }


    public static int paintFence(int n, int k) {
        int[][] dp = new int[2][n];

        int lastTwoSame = k;
        int lastTwoDifferent = k * (k-1);

        int total = lastTwoDifferent + lastTwoSame;


        for(int i = 3; i <= n; i++) {

            lastTwoSame = lastTwoDifferent;
            lastTwoDifferent = total * (k-1);

            total = lastTwoDifferent + lastTwoSame;
        }

        return total;
    }

}
