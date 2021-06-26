package grokking;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class SlidingWindow {

    public static int computeMaxSumOfWindow(int[] nums, int k) {

        int sum = 0;

        int s = 0;
        int e = 0;

        int maxSum = 0;

        while(e < nums.length) {
            if (e-s+1 <= k) {
                sum += nums[e++];

                maxSum = Math.max(maxSum, sum);
            } else {
                sum -= nums[s++];
            }
        }

        return maxSum;
    }

    public static int smallestSubarray(int[] nums, int sum) {
        int minLen = Integer.MAX_VALUE;

        int s = 0;
        int e = 0;

        int currSum = 0;

        while(e < nums.length) {


            if (currSum < sum) {
                currSum += nums[e++];
            } else {
                minLen = Math.min(minLen, e-s);
                currSum -= nums[s++];
            }

        }

        while(s < nums.length && currSum >= sum) {
            minLen = Math.min(minLen, e-s);
            currSum -= nums[s++];
        }

        return minLen;
    }

    public static int longestSubstringKDistinctChar(String input, int k) {

        // Base cases

        HashSet<Character> set = new HashSet<>();

        int s = 0;
        int e = 0;

        int maxSize = 0;

        while(e < input.length()) {

            if (set.size() <= k) {
                maxSize = Math.max(maxSize, e-s);
                set.add(input.charAt(e++));
            } else {
                set.remove(input.charAt(s++));
            }
        }

        if (set.size() <= k) {
            maxSize = Math.max(maxSize, e-s);
        }

        return maxSize;
    }

    public static int numberOfFruitsInBasket(char[] fruits, int k) {

        int maxFruits = 0;

        int e = 0;
        int s = 0;

        HashSet<Character> currFruitsInBasket = new HashSet<>();

        while (e < fruits.length) {
            if (currFruitsInBasket.size() <= k) {
                maxFruits = Math.max(maxFruits, e-s);
                currFruitsInBasket.add(fruits[e++]);
            } else {
                currFruitsInBasket.remove(fruits[s++]);
            }
        }

        if (currFruitsInBasket.size() <= k) {
            maxFruits = Math.max(maxFruits, e-s);
        }

        return maxFruits;
    }

    public static int getMaxLengthNoRepeatChar(String input) {

        if (null == input || "".equals(input)) {
            return 0;
        }

        int e = 0;
        int s = 0;

        HashSet<Character> set = new HashSet<>();

        int maxLen = 0;

        while(e < input.length()) {

            if (set.contains(input.charAt(e))) {
                set.remove(input.charAt(s++));
            } else {
                maxLen = Math.max(maxLen, e - s + 1);
                set.add(input.charAt(e++));
            }

        }

        return maxLen;
    }

    public static int longestSubStringWithReplacement(String input, int k) {

        if (null == input || "".equals(input)) {
            return 0;
        }

        Map<Character, Integer> charFreqMap = new HashMap<>();

        int s = 0;
        int e = 0;

        while(e < input.length()) {

            int windowStart = 0;
            int maxLength = 0;
            int maxRepeatLetterCount = 0;

        }

        // TOOD: At end do we need to check

        return 0;

    }

}
