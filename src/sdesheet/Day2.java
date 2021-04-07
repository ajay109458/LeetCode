package sdesheet;

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
}
