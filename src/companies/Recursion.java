package companies;

import java.util.*;

public class Recursion {

    public boolean exist(char[][] board, String word) {

        int m = board.length;
        int n = board[0].length;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0) && existDFS(board, i, j, 0, word)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean existDFS(char[][] board, int i, int j, int index, String word) {
        if (index == word.length()) {
            return true;
        }

        if (i < 0 || i >= board.length || j < 0 || j > board[0].length || board[i][j] != word.charAt(index)) {
            return false;
        }

        char val = board[i][j];
        board[i][j] = '#';

        boolean isFound = existDFS(board, i + 1, j, index + 1, word)
                || existDFS(board, i - 1, j, index + 1, word)
                || existDFS(board, i , j - 1, index + 1, word)
                || existDFS(board, i , j + 1, index + 1, word);

        board[i][j] = val;

        return isFound;
    }

    public static boolean isWildCardMatching(String input, String pattern) {
        int m = input.length();
        int n = pattern.length();

        boolean[][] dp = new boolean[m+1][n+1];

        for(int i = 0; i <= m; i++) {
            for(int j = 0; j <= n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if (i == 0 || j == 0) {
                    dp[i][j] = false;
                } else {
                    if (input.charAt(i-1) == pattern.charAt(j-1) || pattern.charAt(j-1) == '?') {
                        dp[i][j] = dp[i-1][j-1];
                    } else if (pattern.charAt(j-1) == '*') {
                        dp[i][j] = dp[i-1][j] || dp[i][j-1];
                    }
                }
            }
        }

        return dp[m][n];
    }

    public static boolean isRegularExpressionMatching(String text, String pattern) {

        int m = text.length();
        int n = pattern.length();

        boolean[][] dp = new boolean[n+1][m+1];

        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <=m; j++){
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if (i == 0) {
                    if (pattern.charAt(i-1) == '*') {
                        dp[i][j] = dp[i-2][j];
                    }
                } else if (j == 0) {
                    dp[i][j] = false;
                }else {

                    char pc = pattern.charAt(i-1);
                    char tc = text.charAt(j-1);

                    if (pc == tc || pc == '.') {
                        dp[i][j] = dp[i-1][j-1];
                    } else if (pc == '*') {
                        dp[i][j] = dp[i-2][j];

                        if (pattern.charAt(i-2) == '.' || pattern.charAt(i-2) == text.charAt(j-2)) {
                            dp[i][j] = dp[i][j] || dp[i][j-1];
                        }
                    }
                }
            }
        }

        return dp[pattern.length()][text.length()];
    }


    Map<String, List<String>> map = new HashMap<>();
    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> result = new ArrayList<>();

        createMap(words);

        for(int i = 0; i < words.length; i++) {
            LinkedList<String> list = new LinkedList<>();

            list.add(words[i]);
            backTrack(1, list, result, words.length);
        }

        return result;
    }

    private void backTrack(int step, LinkedList<String> list, List<List<String>> result, int n) {

        if (list.size() == n) {
            result.add(new ArrayList<>(list));
            return;
        }

        StringBuilder sb = new StringBuilder();

        for(String word : list) {
            sb.append(word.charAt(step));
        }

        String prefix = sb.toString();

        List<String> wordList = map.getOrDefault(prefix, new ArrayList<>());

        for(String word : wordList) {
            list.add(word);
            backTrack(step + 1, list, result, n);
            list.remove(word);
        }
    }

    /**
     *
     * Digits that can be rotated - 0, 1, 6, 9, 8
     *
     * @param num
     * @return
     */
    public boolean isStrobogrammatic(String num) {

        Map<Character, Character> map = new HashMap<>();
        map.put('0', '0');
        map.put('1', '1');
        map.put('6', '9');
        map.put('9', '6');
        map.put('8', '8');

        int left = 0;
        int right = num.length() -1;

        while(left <= right) {

            char lc = num.charAt(left);
            char rc = num.charAt(right);

            if (!map.containsKey(lc) || !map.containsKey(rc)) {
                return false;
            }

            if (lc != map.get(rc)) {
                return false;
            }

        }

        return true;
    }

    private void createMap(String[] words) {
        for(String word : words) {
            StringBuilder prefix = new StringBuilder();

            for(int i =0; i < word.length(); i++) {
                prefix.append(word.charAt(i));
                map.putIfAbsent(prefix.toString(), new ArrayList<>());

                List<String> list = map.get(prefix.toString());
                list.add(word);
            }
        }
    }


    public List<String> findStrobogrammatic(int n) {
        Map<Character, Character> map = new HashMap<>();
        map.put('0', '0');
        map.put('1', '1');
        map.put('6', '9');
        map.put('9', '6');
        map.put('8', '8');

        List<String> result = new ArrayList<>();
        populateStrobogrammaticNumber(0, n-1, new char[n], result, map);
        return result;
    }

    private void populateStrobogrammaticNumber(int low, int high, char[] rChars, List<String> result, Map<Character, Character> map) {

        if (low > high) {
            result.add(String.valueOf(rChars));
        }

        for(Map.Entry<Character, Character> entry : map.entrySet()) {
            rChars[low] = entry.getKey();
            rChars[high] = entry.getValue();

            populateStrobogrammaticNumber(low+1, high-1, rChars, result, map);
        }
    }

    public static int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        int m = matrix.length;
        int n = matrix[0].length;

        int[][] cache = new int[m][n];

        int longestPath = 0;
        for(int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int computedPath = computePath(matrix, i, j, cache);
                longestPath = Math.max(longestPath, computedPath);
            }
        }

        return longestPath;
    }

    static int[][] DIRS = {
            {0,1}, {0,-1}, {1,0}, {-1,0}};

    public static int computePath(int[][] matrix, int i, int j, int[][] cache) {
        if (cache[i][j] > 0) {
            return cache[i][j];
        }

        int maxLen = 0;
        for(int[] dir : DIRS) {
            int x = dir[0];
            int y = dir[1];

            if (x >= 0 && x < matrix.length && y >=0 && y < matrix[0].length && matrix[x][y] > matrix[i][j]) {
                maxLen = Math.max(maxLen, computePath(matrix, x, y, cache));
            }
        }

        cache[i][j] = maxLen;

        return cache[i][j];
     }

     public static List<String> generateParantheses(int n) {

         List<String> result = new ArrayList<>();
         populateParanthesis(result, "", 0, 0, n);
         return result;
     }

     public static void populateParanthesis(List<String> list, String curr, int open, int close, int max) {
        if (curr.length() == max * 2) {
            list.add(curr);
            return;
        }

        if (open < max) {
            populateParanthesis(list, curr + "(", open + 1, close, max);
        }

        if (close < open) {
            populateParanthesis(list, curr + ")", open, close + 1, max);
        }
     }

}
