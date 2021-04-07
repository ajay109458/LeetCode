package sdesheet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Day10Backtracking {

    public static void printAllPermutation(String s) {
        //printAllPermutation(s.toCharArray(), 0);

        List<Character> list  = new ArrayList<>();
        for(char ch : s.toCharArray())
            list.add(ch);

        printAllPermutation(list, "");
    }

    private static void printAllPermutation(char[] s, int index) {

        if (index == s.length) {
            System.out.println(Arrays.toString(s));
            return;
        }

        for(int i = index; i < s.length; i++) {
            swap(s, index, i);
            printAllPermutation(s, i+1);
            swap(s, index, i);
        }

    }

    private static void printAllPermutation(List<Character> characterList, String asf) {

        if (characterList.size() == 0) {
            System.out.println(asf);
            return;
        }

        for (int i = 0; i < characterList.size(); i++) {
            char ch = characterList.remove(i);
            printAllPermutation(characterList, asf + ch);
            characterList.add(i, ch);
        }

    }

    private static void swap(char[] s, int i, int j) {
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }

    public static void nQueen(int n) {
        int[][] board = new int[n][n];

        boolean[] cols = new boolean[n];
        boolean[] nDiag = new boolean[n+n];
        boolean[] rDiag = new boolean[n+n];

        populateNQueen(board, 0, cols, nDiag, rDiag);
    }

    private static void populateNQueen(int[][] board, int row, boolean[] cols, boolean[] nDiag, boolean[] rDiag) {

        if (row >= board.length) {
            for(int i = 0 ; i < board.length; i++) {
                System.out.println(Arrays.toString(board[i]));
            }

            System.out.println();
        }

        for(int i = 0; i < board[0].length; i++) {
            if (!cols[i] && !nDiag[row + i] && !rDiag[board.length + row - i - 1]) {
                cols[i] = true;
                nDiag[row + i] = true;
                rDiag[board.length + row - i - 1] = true;

                board[row][i] = 1;

                populateNQueen(board, row + 1, cols, nDiag, rDiag);
                board[row][i] = 0;

                cols[i] = false;
                nDiag[row + i] = false;
                rDiag[board.length + row - i - 1] = false;

            }
        }

    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>(wordDict);
        return workBreak(s, 0, set);
    }

    private static boolean workBreak(String s, int index, HashSet<String> words) {

        if (index == s.length()) {
            return true;
        }

        StringBuilder builder = new StringBuilder();
        boolean result = false;
        for(int i = index; i < s.length(); i++) {
            builder.append(s.charAt(i));

            if (words.contains(builder.toString())) {
                result = result || workBreak(s, i + 1, words);
            }
        }

        return result;
    }

    public static void mGraphColoring(List<Integer>[] graph, int m) {

    }

    private static void colorGraph(List<Integer>[] graph, int[] colors, int v, int m) {
        if (v == graph.length) {
            // all vertex all colored
        }


        for(int i = 1; i <= m; i++) {
            for(Integer child : graph[v]) {
                if (colors[child] == i) {
                    continue;
                }
            }

            colors[v] = i;
            colorGraph(graph, colors, v + 1, m);
            colors[v] = 0;
        }
    }






}
