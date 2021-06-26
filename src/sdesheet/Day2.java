package sdesheet;

import array.ArrayHelper;

import java.util.Arrays;

public class Day2 {
    public int maxProfit(int[] prices) {

        if (prices.length == 0)
            return 0;

        int buy = prices[0];

        int maxProfit = 0;

        int sell = prices[0];

        int i = 0;
        while(i < prices.length) {

            if ( i > 0 && prices[i] > prices[i-1]) {
                sell = prices[i];
            } else if (i > 0 && prices[i] < prices[i-1]){
                maxProfit += (sell - buy);

                sell = prices[i];
                buy = prices[i];
            }

            i++;
        }

        return maxProfit;

    }

    public int maxProfit2Trans(int[] arr) {

        int mpist = 0;
        int leastsf = arr[0];

        int[] dp = new int[arr.length];

        for(int i = 1; i < arr.length; i++) {
            leastsf = Math.min(leastsf, arr[i]);

            mpist = arr[i] - leastsf;
            if (mpist > dp[i-1]) {
                dp[i] = mpist;
            } else {
                dp[i] = dp[i-1];
            }
        }

        int mpibt = 0;
        int maxat = arr[arr.length - 1];

        int[] dpRight = new int[arr.length];

        for (int i = arr.length - 2; i >=0; i--) {
            if (arr[i] > maxat) {
                maxat = arr[i];
            }

            mpibt = maxat - arr[i];

            if (mpibt > dpRight[i+1]) {
                dpRight[i] = mpibt;
            } else {
                dpRight[i] = dpRight[i+1];
            }
        }

        int op = 0;
        for(int i = 0; i < arr.length; i++) {
            op = Math.max(op, dp[i] + dpRight[i]);
        }

        return op;

    }

    public void setMatrixOs(int[][] matrix) {

        int m = matrix.length;

        if (m == 0)
            return;

        int n = matrix[0].length;
        if (n == 0)
            return;

        int[] rows = new int[m];
        int[] cols = new int[n];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    rows[i] = 0;
                    cols[j] = 0;
                }
            }
        }

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if (rows[i] == 0 || cols[j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public static void printPascalTriangle(int n) {

        if (n == 0)
            return;

        int[][] dp = new int[n][n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j <= i; j++) {
                if (i == 0) {
                    dp[i][j] = 1;
                } else if (i == j) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i-1][j] + dp[i-1][j-1];
                }
            }

            System.out.println(Arrays.toString(dp[i]));
        }
    }

    public static String nextPermutation(String number) {

        char[] array = number.toCharArray();

        if (array.length == 0)
            return null;

        if (array.length == 1) {
            return number;
        }

        int i = array.length-2;

        while(i >= 0 && array[i] > array[i+1]) {
            i--;
        }

        if (i < 0) {
            Arrays.sort(array);
            return array.toString();
        }

        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for(int j = i+1; j < array.length; j++) {
            if (array[j] < min && array[j] > array[i]) {
                min = array[j];
                minIndex = j;
            }
        }

        ArrayHelper.swap(array, minIndex, i);
        Arrays.sort(array);

        return Arrays.toString(array);
    }

}
