package dp;

import java.util.Stack;

public class DPProblems {

    public static int climbPaths(int n) {
        if (n < 0) {
            return 0;
        }

        if (n == 0)
            return 1;

        return climbPaths(n-1) + climbPaths(n-2) + climbPaths(n-3);
    }

    public static int climbPath(int n) {
        int[] qb = new int[n+1];
        return climbPathsMem(n, qb);
    }

    public static int climbPathsMem(int n, int[] qb) {
        if (n < 0) {
            return 0;
        }

        if (n == 0)
            return 1;

        if (qb[n] != 0)
            return qb[n];

        qb[n] = climbPaths(n-1) + climbPaths(n-2) + climbPaths(n-3);

        return qb[n];
    }

    public static int climbPathTab(int n) {
        int[] qb = new int[n+1];

        qb[0] = 1;

        for(int i = 1; i <= n; i++) {
            qb[i] = (i-1 >=0 ? qb[i-1] : 0) + (i-2 >=0 ? qb[i-2] : 0) + (i-3 >=0 ? qb[i-3] : 0);
        }

        return qb[n];
    }

    public static int climbAndJump(int[] arr) {
        int n = arr.length;

        int[] dp = new int[n  +1];

        dp[n] = 1;

        for(int i =  n - 1; i >= 0; i--) {
            for(int j = 1; j <= arr[i] && i + j <= n; j++) {
                    dp[i] += dp[i+j];
            }
        }

        return dp[0];
    }

    public static int minJumps(int[] arr) {
        int n = arr.length;

        Integer[] dp = new Integer[n+1];

        dp[n] = 0;

        for(int i = n - 1; i >= 0; i--) {
            if (arr[i] > 0) {
                int min = Integer.MAX_VALUE;
                for(int j = 1; j <= arr[i] && j <= n; j++) {
                    if (dp[i+j] != null)
                        min = Math.min(min, dp[i+j]);
                }

                if (min != Integer.MAX_VALUE) {
                    dp[i] = min + 1;
                }
            }
        }

        return dp[0];
    }


    public static int minPathCost(int[][] mat) {

        int n = mat.length;
        int m = mat[0].length;

        int[][] dp = new int[n][n];

        for (int i = n -1; i >= 0; i--) {
            for (int j = m -1; j >= 0; j--) {
                if (i == n -1 && j == m -1) {
                    dp[i][j] = mat[i][j];
                } else if (i == n -1) {
                    dp[i][j] = mat[i][j] + dp[i][j+1];
                } else if (j == n -1) {
                    dp[i][j] = mat[i][j] + mat[i+1][j];
                } else {
                    dp[i][j] = mat[i][j] + Math.min(mat[i+1][j], mat[i][j+1]);
                }

            }
        }

        return dp[0][0];

    }


    public int longestValidParenthesis(String str) {
        Stack<Integer>  stack = new Stack<>();

        stack.push(-1);

        int max = 0;

        char[] arr = str.toCharArray();
        for(int i = 0; i < arr.length; i++) {
            if(str.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    max = Math.max(max, i - stack.peek());
                }
            }
        }

        return max;
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[][] dp = new int[m][n];

        for(int i = 0; i  < m; i ++) {
            for (int j = 0 ; j < n; j++) {

                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                    continue;
                }

                if (i == 0 && j == 0 ) {
                    dp[i][j] = 1;
                } else if (i == 0) {
                    dp[i][j] = dp[i][j-1];
                } else if (j == m) {
                    dp[i][j] = dp[i][j-1];
                } else {
                    dp[i][j] = dp[i][j-1] + dp[i-1][j];
                }
            }
        }

        return dp[m-1][n-1];
    }

    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m+1][n+1];

        for(int i = 0; i <= m; i++) {
            for(int j = 0; j <= n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = 0;
                } else if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0){
                    dp[i][j] = i;
                } else {

                    if (word1.charAt(i-1) != word2.charAt(j-1)) {
                        dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]);
                        dp[i][j] += 1;
                    } else {
                        dp[i][j] = dp[i-1][j-1];
                    }

                }
            }
        }

        return dp[m][n];
    }

    public static int knapsackUnbounded(int[] vals, int[] wts, int W) {
        int[] dp = new int[W+1];

        dp[0] = 0;

        for(int capacity = 0; capacity <= W; capacity++) {
            int max = 0;
            for(int i = 0; i < wts.length; i++) {
                if (wts[i] <= capacity) {
                    int rCapacity = capacity - wts[i];
                    int rVal = dp[rCapacity];
                    int tVal = rVal + vals[i];
                    max = Math.max(max, tVal);
                }
            }

            dp[capacity] = max;
        }

        return dp[W];
    }

    public static int countBinaryStringsWithoutConsecutive1s(int n) {
        if (n == 0)
            return 0;

        int[] dpZ = new int[n+1];
        int[] dpO = new int[n+1];

        dpZ[1] = 1;
        dpO[1] = 1;

        for(int i = 2; i <=n ; i++) {
            dpZ[i] = dpO[i-1];
            dpO[i] = dpO[i-1] + dpZ[i-1];
        }

        return dpO[n] + dpZ[n];
    }

    public static int arrangeBuilding(int n) {
        if (n == 0)
            return 0;

        int buildings = 1;
        int spaces = 1;

        for(int i = 2; i <=n ; i++) {

            int prevBuildings = buildings;

            buildings = spaces;

            spaces = prevBuildings + spaces;
        }

        int totalWays = buildings + spaces;

        return totalWays * totalWays;
    }


    public static  int countEncodings(String str) {

        int[] dp = new int[str.length()];
        dp[0] = 1;

        for(int i = 1; i < str.length(); i++) {
            if (str.charAt(i-1) == '0' && str.charAt(i) == '0') {
                dp[i] = 0;
            } else if (str.charAt(i-1) == '0' && str.charAt(i) != '0') {
                dp[i] = dp[i-1];
            } else if (str.charAt(i-1) != '0' && str.charAt(i) == '0') {
                if (str.charAt(i-1) == '1' || str.charAt(i-1) == '2') {
                    dp[i] = dp[i-2];
                } else {
                    dp[i] = 0;
                }
            } else {
                dp[i] = dp[i-1];
                if (Integer.parseInt(str.substring(i-1, i+1)) <= 26) {
                    dp[i] += dp[i-2];
                }
            }
        }


        return dp[str.length()];

    }

    public static int numberOfWaysToColor (int n, int k) {

        if (n == 0)
            return 0;

        if (k == 0)
            return 0;

        int sameColorCount = 0;
        int diffColorCounts = 0;
        int total = 0;

        for(int i = 2; i <= n; i++) {
            if (i == 2) {
                sameColorCount = k;
                diffColorCounts = k * (k - 1);
            } else {
                sameColorCount = diffColorCounts;
                diffColorCounts = total * (k-1);
            }
            total = sameColorCount + diffColorCounts;
        }

        return total;
    }

    public static int numberOfWaysToTile(int n, int m) {

        if (n < m) {
            return 1;
        }

        int[] dp = new int[n+1];

        for(int i = 1; i <= n; i++) {
            if (i < m) {
                dp[i] = 1;
            } else if (i == m){
                dp[i] = 2;
            } else {
                dp[i] = dp[i -1] + dp[i-m];
            }


        }

        return dp[n];

    }

    public static int combination(int n, int k) {
        if (k == 0 || k == n)
            return 1;

        int[][] dp = new int[n+1][k+1];

        for(int i = 0; i <= n; i++) {
            for (int j = 0; j <= Math.min(i, k); j++) {
                if (j == i) {
                    dp[i][j] = 1;
                } else if (j == 0) {
                    dp[i][j] = 1;
                }else {
                    dp[i][j] = dp[i-1][j] + dp[i-1][j-1];
                }
            }
        }

        return dp[n][k];
    }

    public static int friendsPairingCount(int n) {
        if (n == 0 || n == 1)
            return 1;

        return friendsPairingCount(n-1) + (n-1) * friendsPairingCount(n-2);
    }

    public static int friendsPairingDP(int n) {
        if (n < 2)
            return n;

        int[] dp = new int[n+1];
        dp[0] =0;
        dp[1] = 1;

        for(int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + (i-1) * dp[i-2];
        }

        return dp[n];
    }

    public static int maxProfitBuySellStocks(int[] prices) {

        if (prices.length == 0)
            return 0;

        int minTillNow = prices[0];

        int maxProfit = 0;
        for(int price : prices) {
            maxProfit = Math.max(price - minTillNow, maxProfit);
            minTillNow = Math.min(minTillNow, price);
        }

        return maxProfit;
    }

    public static String longestPalindrome(String s) {
        int n = s.length();

        boolean[][] dp = new boolean[n][n];

        int max = 0;

        int maxI = -1;

        for(int len = 1; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;



                if (i == j) {
                    dp[i][j] = true;
                } else if (i + 1 == j) {
                    if (s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = true;
                    }
                } else {
                    if (s.charAt(i) == s.charAt(j) && dp[i+1][j-1]) {
                            dp[i][j] = true;
                    }
                }

                if (len > max && dp[i][j]) {
                    max = len;
                    maxI = i;
                }
            }


        }

        return s.substring(maxI, maxI  + max );
    }

    public static int maxSumKadane(int[] arr) {

        if (arr.length == 0)
            return 0;

        int sumSoFar = arr[0];
        int maxSoFar = arr[0];

        for(int i = 1; i < arr.length; i++) {
            if (arr[i] >= 0) {
                sumSoFar += arr[i];
            } else {
                sumSoFar = arr[i];
            }

            maxSoFar = Math.max(sumSoFar, maxSoFar);
        }

        return maxSoFar;
    }

    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }

        int min = prices[0];

        int profit = 0;

        for(int i = 1; i < prices.length; i++) {
            min = Math.min(prices[i], min);

            profit = Math.max(profit, prices[i] - min);
        }

        return profit;
    }

    public int maxProduct(int[] nums) {

        if (nums.length == 0) return 0;

        int max_so_far = nums[0];
        int min_so_far = nums[0];
        int result = max_so_far;

        for (int i = 1; i < nums.length; i++) {
            int curr = nums[i];
            int temp_max = Math.max(curr, Math.max(max_so_far * curr, min_so_far * curr));
            min_so_far = Math.min(curr, Math.min(max_so_far * curr, min_so_far * curr));

            max_so_far = temp_max;

            result = Math.max(max_so_far, result);
        }

        return result;

    }

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        dp[0] = 0;

        for(int amt = 1; amt <= amount; amt++) {
            int steps = Integer.MAX_VALUE;
            for(int coin : coins) {
                if (coin <= amt && dp[amt-coin] != Integer.MAX_VALUE) {
                    steps = Math.min(steps, 1 + dp[amt-coin]);
                }
            }

            dp[amt] = steps;
        }

        return dp[amount] == Integer.MAX_VALUE ? - 1 : dp[amount];
    }

    public static int LIS(int[] arr) {

        int n = arr.length;

        int[] dp = new int[n];
        int max = 0;

        for(int i = 0; i < n ; i++) {
            dp[i] = 1;

            for(int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            max = Math.max(max, dp[i]);
        }

        return max;
    }

    public static int longestCommonSubSequence(String a, String b) {
        int[][] dp = new int[a.length()+1][b.length()+1];

        for (int i = 0; i <= a.length(); i++) {
            for(int j = 0; j <= b.length(); j++) {
                if (i == 0 || j == 0) {
                  dp[i][j] = 0 ;
                } else {
                    if (a.charAt(i-1) == b.charAt(j-1)) {
                        dp[i][j] = dp[i-1][j-1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                    }
                }
            }
        }

        return dp[a.length()][b.length()];
    }

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return 0;

        int n = matrix.length;
        int m = matrix[0].length;
        int longestPath = 0;

        int[][] cache = new int[n][m];

        for(int i = 0; i < n ; i++) {
            for(int j = 0; j < m; j++) {
                int longest = longestIncreasingPathInt(matrix, cache, m, n, 0, 0);
                longestPath = Math.max(longest, longestPath);
            }
        }

        return longestPath;
    }

    private int[][] DIRECTIONS = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
    };

    private int longestIncreasingPathInt(int[][] matrix, int[][] cache, int m, int n, int i, int j){


        if (cache[i][j] > 0) {
            return cache[i][j];
        }

        int max = 0;

        for(int[] dir : DIRECTIONS) {
            int x = dir[0] + i;
            int y = dir[1] + j;

            if (x >= 0 && x < n && y >= 0 && y < m && matrix[x][y] > matrix[i][j]) {
                int longest = longestIncreasingPathInt(matrix, cache, m, n, x, y);
                max = Math.max(longest, max);
            }
        }

        cache[i][j] = max + 1;
        return cache[i][j];
    }

    /**
     *  Think about the base case
     *
     *  Think about the data structure
     *
     *  Think about the algorithm
     *
     *  Think about the test cases
     *      - Good cases
     *      - Average cases
     *      - Worst cases
     *      - Base cases
     */
    public int minCost(int[][] costs) {

        int n = costs.length;

        int[][] dp = new int[n][3];

        for(int i = 0; i < 3; i++) {
            dp[0][i] = costs[0][i];
        }

        for(int i = 1; i < n; i++) {
            for(int j = 0; j < 3; j++)  {
                int minCost = Integer.MAX_VALUE;
                for (int k = 0; k < 3; k++) {
                    if (k != j) {
                        minCost = Math.min(minCost, dp[i-1][k] + costs[i][j]);
                    }
                }

                dp[i][j] = minCost;
            }
        }

        int result = Integer.MAX_VALUE;
        for(int i = 0; i < 3; i++) {
            result = Math.min(result, dp[n-1][i]);
        }

        return result;

    }

    private static int cutRod(int[] prices, int n) {
        if(n <= 0)
            return 0;

        int maxValue = Integer.MAX_VALUE;

        for(int i = 0; i <n; i++) {
            maxValue = Math.max(maxValue, prices[i] + cutRod(prices, n - i-1));
        }

        return maxValue;
    }

    public static int rodCutting(int[] prices, int n) {
        int[] dp = new int[n+1];

        dp[0] = prices[0];

        for(int i = 1; i < prices.length; i++) {
            dp[i] = prices[i];

            for(int j = 1; j <=i; j++){
                dp[i] = Math.max(dp[i], prices[j] + dp[i-j]);
            }
        }

        return dp[n];
    }



}
