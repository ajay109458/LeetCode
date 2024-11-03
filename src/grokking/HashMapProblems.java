package grokking;

import java.util.HashMap;
import java.util.Map;

public class HashMapProblems {

    public int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();

        for(char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        for(int i = 0; i < s.length(); i++) {
            if(map.get(s.charAt(i)) == 1) return i;
        }

        return -1;
    }

    public int maxNumberOfBalloons(String text) {
        int[] counts = new int[26];

        for(char ch : text.toCharArray()) {
            counts[ch - 'a']++;
        }

        String ballon = "ballon";

        int min = counts['b' - 'a'];
        for(char ch : ballon.toCharArray()) {
            if(counts[ch - 'a'] < min) {
                min = counts[ch - 'a'];
            }

            if (ch == 'l') {
                if (counts['l' - 'a'] / 2 < min) {
                    min = counts['l' - 'a']/2;
                }
            }

        }

        return min;
    }

    public int longestPalindrome(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for(char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        int maxLength = 0;
        boolean hasOdd = false;
        for(Map.Entry<Character, Integer> entry : map.entrySet()) {
            if(entry.getValue() % 2 != 0) {
                hasOdd = true;
            } else {
                maxLength += entry.getValue();
            }
        }

        return maxLength + ((hasOdd) ? 1 : 0);
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> map = new HashMap<>();
        for(char ch : magazine.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        for(char ch : ransomNote.toCharArray()) {
            int count = map.getOrDefault(ch, 0);
            if (count == 0) return false;
            map.replace(ch, count - 1);
        }

        return true;
    }

}
