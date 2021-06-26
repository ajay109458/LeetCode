package prepcoding;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class HashMapProblems {

    public static char getHighestFrequencyCharacter(String input) {
        Map<Character, Integer> freqByCharMap = new java.util.HashMap<>();

        int maxFreq = 0;
        Character finalChar = null;
        for(char ch : input.toCharArray()) {
            freqByCharMap.put(ch, freqByCharMap.getOrDefault(ch, 0));

            if (freqByCharMap.get(ch) > maxFreq) {
                maxFreq = freqByCharMap.get(ch);
                finalChar = ch;
            }

        }

        return finalChar;
    }

    public static void printCommonChar(int[] arr1, int[] arr2) {
        HashSet<Integer> set = new HashSet<>();

        for(int val : arr1) {
            set.add(val);
        }

        for(int val : arr2) {
            if (set.contains(val)) {
                System.out.print(val);
                set.remove(val);
            }
        }
    }

    public static int maxLengthConsequtiveElements(int[] arr) {

        Map<Integer, Boolean> map = new HashMap<>();

        for(int val : arr) {
            map.put(val, true);
        }

        for(int val : arr) {
            if (map.containsKey(val-1)) {
                map.put(val, false);
            }
        }

        int max = 0;
        for(int val : arr) {
            if (map.get(val)) {

                int curr = val;

                int len = 0;
                while(map.containsKey(curr)) {
                    len++;
                    curr++;
                }

                max = Math.max(curr, max);
            }
        }

        return max;

    }

    public static int getLengthOfLongestConsecutiveElements(int[] arr) {
        if (arr.length == 0)
            return 0;

        Map<Integer, Boolean> isStartMap = new java.util.HashMap<>();

        for(int val : arr) {
            isStartMap.put(val, true);
        }

        for(int val : arr) {
            if (isStartMap.containsKey(val - 1)) {
                isStartMap.put(val, false);
            }
        }

        int maxLen = 0;
        for(int val : arr) {
            if (isStartMap.get(val)){
               int len = 0;
               int temp = val;

               while(isStartMap.containsKey(temp++)) {
                   len++;
               }

               maxLen = Math.max(maxLen, len);
            }
        }

        return maxLen;
    }

}
