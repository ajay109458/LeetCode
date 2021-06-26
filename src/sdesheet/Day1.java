package sdesheet;

import array.ArrayHelper;
import utils.Pair;

import java.util.Arrays;
import java.util.Stack;

public class Day1 {

    public static void sortO12(int[] arr) {
        if (arr.length == 0)
            return;

        int i = -1;
        int j = 0;
        int k = arr.length;

        while(j < arr.length) {
            if (arr[j] == 0) {
                ArrayHelper.swap(arr, ++i, j++);
            } else if(arr[j] == 1) {
                j++;
            } else {
                ArrayHelper.swap(arr, --k, j);
            }
        }
    }

    public static int kadaneSum(int[] nums) {

        if (nums.length == 0)
            return 0;

        int maxSoFar = Integer.MIN_VALUE;
        int sumSoFar = nums[0];

        for(int i = 1; i < nums.length; i++) {
            sumSoFar += nums[i];

            if (maxSoFar < sumSoFar) {
                maxSoFar = sumSoFar;
            }

            if (sumSoFar < nums[i]) {
                sumSoFar = nums[i];
            }
        }

        return maxSoFar;
    }

    public static void mergeOverlappingIntervals(int[][] intervals) {
        Stack<Pair> stack = new Stack<>();

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        for(int[] interval : intervals) {
            if (stack.isEmpty() || interval[0] > stack.peek().y) {
                stack.push(new Pair(interval[0], interval[1]));
            } else {
                stack.peek().y = Math.max(stack.peek().y, interval[1]);
            }
        }
    }

}
