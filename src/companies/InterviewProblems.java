package companies;

import array.ArrayHelper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class InterviewProblems {

    public int maxProfit(int[] prices) {

        int maxProfit  =0;

        int minPrice = Integer.MAX_VALUE;

        for(int price : prices) {
            minPrice = Math.min(price, minPrice);

            maxProfit = Math.max(maxProfit, price - minPrice);
        }

        return maxProfit;
    }

    public int maxProfitTwoTransaction(int[] prices) {

        int n = prices.length;

        int[] sellProfit = new int[n];
        int[] buyProfit = new int[n];

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n ; i++) {
            min = Math.min(min, prices[i]);

            int profit = prices[i] - min;
            sellProfit[i] = (i == 0) ? profit : Math.max(sellProfit[i-1], profit);
        }

        int max = 0;
        for(int i = n - 1; i >= 0; i--) {
            max = Math.max(max, prices[i]);

            int profit = max - prices[i];
            buyProfit[i] = (i == n-1) ? profit : Math.max(buyProfit[i+1], profit);
        }


        int maxProfit = 0;

        for(int i = 0; i < n; i++) {
            maxProfit = Math.max(maxProfit, sellProfit[i] + buyProfit[i]);
        }

        return maxProfit;
    }

    public int maxProfitKTransaction(int k, int[] prices) {

        int days = prices.length;

        int[][] dp = new int[k+1][days+1];

        for(int i = 0; i <= k; i++) {
            for (int j = 0; j <= days; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i][j-1];

                    for(int m = j-1; m >= 0; m--) {
                        dp[i][j] = Math.max(dp[i][j], dp[i-1][m] + prices[j] - prices[m]);
                    }
                }
            }
        }

        return dp[k][days + 1];
    }

    public void sortColors(int[] nums) {
        int i = -1;
        int j = 0;
        int k = nums.length;

        while(j < k) {
            if (nums[j] == 0) {
                i++;
                ArrayHelper.swap(nums, i, j);
                j++;
            } else if (nums[j] == 1) {
                j++;
            } else {
                k--;
                ArrayHelper.swap(nums, j, k);
            }
        }
    }

    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        int i = 0;
        int j = n -1;

        int k = n-1;

        while(i <= j) {
            int product1 = nums[i] * nums[i];
            int product2 = nums[j] * nums[j];

            if (product1 <= product2) {
                result[k--] = product2;
                j--;
            } else {
                result[k--] = product1;
                i--;
            }
        }

        return result;
    }

    public String customSortString(String order, String str) {
        Map<Character, Integer> map = new HashMap<>();

        for(char ch : str.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        StringBuilder result = new StringBuilder();
        for(char ch : order.toCharArray()) {
            Integer count = map.get(ch);
            if (count != null) {
                while(count-- > 0) {
                    result.append(ch);
                }

                map.remove(ch);
            }
        }

        for(Map.Entry<Character, Integer> entry : map.entrySet()) {
            int count = entry.getValue();

            while(count-- > 0) {
                result.append(entry.getKey());
            }
        }

        return result.toString();
    }

}
