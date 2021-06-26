package sdesheet;

import java.util.*;

public class DynamicProgramming {

    public static int longestIncreasingSubsequence(int[] arr) {

        int n = arr.length;
        int[] dp = new int[n];

        for (int i = 0; i < arr.length; i++) {
            dp[i] = 1;

            for(int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        return dp[n-1];
    }

    public static int countPalindromicSubsequence(String input) {

        int n = input.length();
        boolean[][] dp = new boolean[n][n];

        int count = 0;

        for(int len =1 ; len <= n; len++) {
            for(int i = 0; i < n - len +1; i++) {
                int j = i + len;

                if (i == j) {
                    dp[i][j] = true;
                    count++;
                } else if (j == i + 1 && input.charAt(i) == input.charAt(j)) {
                    dp[i][j] = true;
                    count++;
                } else if (input.charAt(i) == input.charAt(j) && dp[i+1][j-1]) {
                    dp[i][j] = true;
                    count++;
                }
            }
        }

        return count;
    }

    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n];
        for(int  i = 0; i < n; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        dp[0] = 0;

        for(int i = 1; i < n; i++) {

            dp[i] = Math.min(dp[i], Math.min(dp[i-1], (i > 1) ? dp[i-2] : Integer.MAX_VALUE));

        }

        return dp[n-1];
    }


    public int openLock(String[] deadends, String target) {

        HashSet<String> deadEnds = new HashSet<>(Arrays.asList(deadends));

        HashSet<String> visited = new HashSet<>();
        visited.add("0000");

        Queue<String> queue = new LinkedList<>();
        queue.add("0000");

        int level = 0;

        while(!queue.isEmpty()) {

            int size = queue.size();

            while(size > 0) {
                size--;

                String lockPosition = queue.poll();

                if (deadEnds.contains(lockPosition)) {
                    continue;
                }

                if (lockPosition.equals(target)) {
                    return level;
                }

                StringBuilder builder = new StringBuilder(lockPosition);
                for(int i = 0; i < 4; i ++) {
                    char currCh = builder.charAt(i);
                    String s1 = builder.substring(0, i) + ((currCh == '9') ? 0 : currCh - '0' + 1) + builder.substring(i+1);
                    String s2 = builder.substring(0, i) + ((currCh == '0') ? 9 : currCh - '0' - 1) + builder.substring(i+1);

                    if (!visited.contains(s1) && !deadEnds.contains(s1)) {
                        queue.add(s1);
                        visited.add(s1);
                    }

                    if (!visited.contains(s2) && !deadEnds.contains(s2)) {
                        queue.add(s2);
                        visited.add(s2);
                    }
                }
            }
            level++;
        }

        return -1;

    }

    public int longestOnes(int[] nums, int k) {
        int currZeroCount = 0;

        int s = 0;
        int e = 0;

        int maxLen = 0;

        while(e < nums.length) {

            while (e < nums.length && currZeroCount <= k) {
                maxLen = Math.max(maxLen, e - s);

                if (nums[e] == 0) {
                    currZeroCount++;
                }

                e++;
            }

            while (s < nums.length && currZeroCount > k) {
                if(nums[s] == 0) {
                    currZeroCount--;
                }
                s++;
            }

        }

        return maxLen;
    }


    public int characterReplacement(String s, int k) {
        int n = s.length();
        int start = 0;
        int maxLength = 0;

        for(int e = 0; e < n; e++) {

        }

        return maxLength;

    }


    public String getPermutation(int n, int k) {
        List<Character> charList = new ArrayList<>();

        for(int i = 1; i <= n; i++) {
            charList.add((char) (i + '0'));
        }

        return getKthPermutation(charList, k, "");
    }

    public static int factorial(int n) {
        if (n == 1)
            return n;

        return n * factorial(n-1);
    }

    private static String getKthPermutation(List<Character> charList, int k, String result) {

        if (charList.isEmpty()) {
            return result;
        }

        int n = charList.size();

        int count = factorial(n-1);
        int selected = (k-1)/count;

        result += charList.remove(selected);

        k = k - selected * count;
        return getKthPermutation(charList, k, result);
    }

    public static String longestPalindromicSubstring(String input) {

        int n = input.length();

        boolean[][] dp = new boolean[n][n];

        int maxLen = 0;
        int startIndex = -1;

        for(int len = 1; len <= n; len++) {
            for(int i = 0; i <= n -len; i++) {
                int j = i + len - 1;

                if (i == j) {
                    dp[i][j] = true;
                } else if (i + 1 == j && input.charAt(i) == input.charAt(j)) {
                    dp[i][j] = true;
                } else if (input.charAt(i) == input.charAt(j) && dp[i+1][j-1]) {
                    dp[i][j] = true;
                }

                System.out.println(j);

                if (dp[i][j] == true && len > maxLen) {
                    maxLen = len;
                    startIndex = i;
                }
            }
        }

        return input.substring(startIndex, startIndex + maxLen);

    }

    public int numDecodings(String s) {
        if (s == null || "".equals(s))
            return 0;

        int n = s.length();
        int[] dp = new int[n+1];

        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;

        for (int i =2 ; i <= s.length(); i++) {
            int oneDigit = Integer.valueOf(s.substring(i-1, i));
            int twoDigits = Integer.valueOf(s.substring(i-2, i));

            if (oneDigit >= 1) {
                dp[i] += dp[i-1];
            }

            if (twoDigits >= 10 && twoDigits <= 26) {
                dp[i] += dp[i-2];
            }
        }

        return dp[n];
    }

    public static boolean editDistance(String s, String t) {
        // base case
        if (s == null || "".equals(s)) {
            return t.length() == 1;
        }

        if (t == null || "".equals(t)) {
            return s.length() == 1;
        }

        int[][] dp = new int[s.length() + 1][t.length() + 1];

        for(int i = 0; i < s.length(); i++) {
            for(int j = 0; j < t.length(); j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = 0;
                } else if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    if (s.charAt(i-1) == t.charAt(j-1)) {
                        dp[i][j] = dp[i-1][j-1];
                    } else {
                        dp[i][j] = 1 + Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j]));
                    }
                }
            }
        }

        return dp[s.length()][t.length()] == 1;
    }
}
