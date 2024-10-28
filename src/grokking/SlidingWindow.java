package grokking;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SlidingWindow {

    public static int maxSumOfWindow(int[] nums, int k) {
        int n = nums.length;
        int currSum = 0;
        int maxSum = 0;

        int start = 0;
        int end = 0;

        while(end < n) {
            if (end - start + 1 > k) {
                currSum -= nums[start++];
            }

            currSum += nums[end++];
            maxSum = Math.max(maxSum, currSum);
        }

        return maxSum;
    }

    public static int findSmallestSubArray(int[] nums, int S) {
        int n = nums.length;

        int start = 0;
        int end = 0;

        int currSum = 0;
        int minLen = nums.length;
        boolean isSolution = false;


        while(end < n) {
            currSum += nums[end];

            while(currSum >= S) {
                isSolution = true;
                minLen = Math.min(minLen, end - start + 1);
                currSum -= nums[start++];
            }

            end++;
        }

        return (isSolution) ? minLen : 0;
    }

    public static int getLongestSubStrKDistinct(String s, int k) {
        Set<Character> set = new HashSet<>();

        int start = 0;
        int end = 0;
        int maxLen =  0;

        while(end < s.length()) {
            set.add(s.charAt(end));

            while(set.size() > k) {
                set.remove(s.charAt(start++));
            }

            maxLen = Math.max(maxLen, end - start+1);
            end++;
        }

        return maxLen;
    }

    public static int maxFruitsInBasket(char[] fruits, int k) {
        int maxFruits = 0;

        Set<Character> set = new HashSet<>();

        int start = 0;
        int end = 0;
        int maxLen =  0;

        while(end < fruits.length) {
            set.add(fruits[end]);
            while(set.size() > k) {
                set.remove(fruits[start++]);
            }

            maxLen = Math.max(maxLen, end - start+1);
            end++;
        }

        return maxLen;
    }

    public static int maxLengthNoRepeat(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int start = 0;
        int end = 0;
        int maxLen = 0;

        Set<Character> set = new HashSet<>();

        while (end < s.length()) {
            while (set.contains(s.charAt(end))) {
                set.remove(s.charAt(start++));
            }

            set.add(s.charAt(end));
            maxLen = Math.max(maxLen, end - start+1);
            end++;
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

    public int characterReplacement(String s, int k) {
        if (null == s || s.isEmpty()) {
            return 0;
        }

        int start = 0;
        int end = 0;
        int maxLen = 0;

        // Storing character and it's frequency in window which starts at start and ends at end.
        Map<Character, Integer> charFreqMap = new HashMap<>();

        int maxCharCount = 0;

        while(end < s.length()) {

            char ch = s.charAt(end);

            charFreqMap.put(ch, charFreqMap.getOrDefault(ch, 0) + 1);
            maxCharCount = Math.max(maxCharCount, charFreqMap.get(ch));

            int currLen = end - start + 1;

            int remainingCharFreq = (currLen - maxCharCount);

            while (remainingCharFreq > k) {
                // remove the character
            }

            end++;
        }

        return maxLen;
    }

}
