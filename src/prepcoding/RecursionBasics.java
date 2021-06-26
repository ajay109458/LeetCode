package prepcoding;

import array.ArrayHelper;

import java.util.ArrayList;
import java.util.List;

public class RecursionBasics {

    public static void printIncreasing(int n) {

        if (n == 0) {
            return;
        }

        printIncreasing(n-1);

        System.out.println(n);
    }

    public static void printDecreasingIncreasing(int n) {
        if (n == 0)
            return;

        System.out.print(n);
        printDecreasingIncreasing(n-1);
        System.out.println(n);
    }

    public static int factorial(int n) {

        if (n == 0 || n == 1) {
            return 1;
        }

        return n * factorial(n - 1);
    }

    public static int power(int x, int n) {
        if (n == 0) {
            return 1;
        }

        return x * power(x, n - 1);
    }

    public static int power1(int x, int n) {
        if (n == 0) {
            return 1;
        }

        return power1(x, n/2) * power1(x, n/2) * ((n%2 == 0) ? 1 : x);
    }

    public static void printArrayUsingRecursion(int[] arr, int idx) {
        if (idx == arr.length) {
            return;
        }

        System.out.print(arr[idx]);
        printArrayUsingRecursion(arr, idx-1);
    }

    public static void printArrayInReverse(int[] arr, int idx) {
        if (idx == arr.length) {
            return;
        }

        printArrayUsingRecursion(arr, idx + 1);
        System.out.print(arr[idx]);
    }

    public static int maxArray(int[] arr, int index) {
        if (index == arr.length) {
            return Integer.MIN_VALUE;
        }

        return Math.max(arr[index], maxArray(arr, index + 1));
    }

    public static int findFirstIndex(int[] arr, int index, int val) {
        if (index == arr.length) {
            return -1;
        }

        if (arr[index] == val) {
            return index;
        }

        return findFirstIndex(arr, index + 1, val);
    }

    public static int findLastIndex(int[] arr, int index, int val) {
        if (index == arr.length) {
            return -1;
        }

        int nextIndex = findLastIndex(arr, index + 1, val);

        if (nextIndex != -1) {
            return nextIndex;
        }

        if (arr[index] == val) {
            return index;
        }

        return nextIndex;
    }

    public static List<String> allWords(String number) {

        String[] codes = {"./", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        List<String> result = new ArrayList<>();
        populateWords(number, 0, codes, "", result);

        return result;
    }

    private static void populateWords(String number, int index, String[] codes, String currWord, List<String> result) {
        if (index == number.length()) {
            result.add(currWord);
            return;
        }

        int digit = number.charAt(index) - '0';

        for(char ch : codes[digit].toCharArray()) {
            populateWords(number, index +1, codes, currWord + ch, result);
        }
    }

    public static List<String> getAllStairPaths(int n) {
        List<String> allPaths = new ArrayList<>();
        populateAllStairPath(n, "", allPaths);
        return allPaths;
    }

    private static void populateAllStairPath(int n, String currPath, List<String> result) {
        if (n < 0) {
            // Invalid path
            return;
        }

        if (n == 0) {
            result.add(currPath + "0");
            return;
        }

        populateAllStairPath(n -1, currPath + n, result);
        populateAllStairPath(n -2, currPath + n, result);
        populateAllStairPath(n -3,  currPath + n, result);
    }

    public static List<String> printMazePaths(int[][] matrix) {
        List<String> allPaths = new ArrayList<>();
        populateMazePath(matrix, 0, 0, "", allPaths);
        return allPaths;
    }

    private static void populateMazePath(int[][] matrix, int x, int y, String path, List<String> allPaths) {
        if (x < 0 || x >= matrix.length) {
            return;
        }

        if (y < 0 || y >= matrix[0].length) {
            return;
        }

        if (x == matrix.length - 1 && y == matrix[0].length - 1) {
            allPaths.add(path);
            return;
        }

        populateMazePath(matrix, x+1, y, path + "H", allPaths);
        populateMazePath(matrix, x, y+1, path + "V", allPaths);
    }

    public static void printMazePathsWithSum(int[][] maze) {
        List<String> allPaths = new ArrayList<>();
    }

    private static void populateAllPathsOfTheMaze(int[][] maze, int x, int y, String path, List<String> allPaths) {
        if (x < 0 || x >= maze.length) {
            return;
        }

        if (y < 0 || y >= maze[0].length) {
            return;
        }

        if (x == maze.length && y == maze[0].length) {
            allPaths.add(path);
            return;
        }

        for(int ms = 1; ms + x < maze.length; ms++) {
            populateAllPathsOfTheMaze(maze, x + ms, y, path + "V" + ms, allPaths);
        }

        for(int ms = 1; ms + y < maze[0].length; ms++) {
            populateAllPathsOfTheMaze(maze, x, y + ms, path + "H", allPaths);
        }
    }

    public static void printPermutation(String input) {
        printPermutationInt(input.toCharArray(), 0, input.length() -1, "");
    }

    private static void printPermutationInt(char[] input, int left, int right, String result) {
        if (left == right) {
            System.out.println(result);
            return;
        }


        for(int i = left; i <= right; i++) {
            ArrayHelper.swap(input, left, i);
            printPermutationInt(input, i+1, right, result + input[left]);
            ArrayHelper.swap(input, left, i);
        }
    }

    public static void printEncoding(String input) {
        printEncodingInt(input, 0, "");
    }

    private static void printEncodingInt(String input, int index, String result) {
        if (index == input.length()) {
            if (!"".equals(result)) {
                System.out.println(result);
            }
        }

        if (index < input.length()) {
            String firstStr = input.substring(index, index+1);
            int first = Integer.parseInt(firstStr);

            if (first >= 1 && first <= 9) {
                char ch = (char)(first + 'a' - 1);
                printEncodingInt(input, index + 1, result + ch);
            }
        }


        if (index + 1 < input.length()) {
            String secondStr = input.substring(index, index + 2);
            int second = Integer.parseInt(secondStr);

            if (second >= 10 && second <= 26) {
                char ch = (char) (second + 'a' -1);
                printEncodingInt(input, index + 2, result + ch);
            }
        }
    }

    public static List<List<Integer>> getAllSubsets(int[] arr, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        populateSubSet(arr, 0, sum, result, new ArrayList<>());

        return result;
    }

    private static void populateSubSet(int[] arr, int index, int sum, List<List<Integer>> subsets, List<Integer> currSubset) {

        if (sum == 0) {
            // clone the list and add to the list
            List<Integer> clone = new ArrayList<>();

            for(int val : currSubset) {
                clone.add(val);
            }

            subsets.add(currSubset);

            return;
        }

        if (index >=  arr.length || sum < 0) {
            return;
        }

        populateSubSet(arr, index + 1, sum, subsets, currSubset);

        currSubset.add(arr[index]);
        populateSubSet(arr, index + 1, sum - arr[index], subsets, currSubset);
        currSubset.remove(Integer.valueOf(arr[index]));
    }

}
