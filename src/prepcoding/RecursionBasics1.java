package prepcoding;

import array.ArrayHelper;

import java.util.*;

public class RecursionBasics1 {

    public static void printDecreasing(int n) {
        if (n == 0) {
            return;
        }

        System.out.println(n);
        printDecreasing(n-1);
    }

    public static void printIncreasing(int n) {
        if (n == 0) {
            return;
        }

        printIncreasing(n-1);
        System.out.println(n);
    }

    public static void printDecreasingIncreasing(int n) {
        if (n == 0) {
            return;
        }

        System.out.println(n);
        printDecreasingIncreasing(n-1);
        System.out.println(n);
    }

    public static int powerLinear(int x, int n) {

        if (n == 0) {
            return 1;
        }

        if (n == 1) {
            return x;
        }

        return x * powerLinear(x, n - 1);
    }

    public static int powerLog(int x, int n) {

        if (n == 0) {
            return 1;
        }

        if (n == 1) {
            return x;
        }

        int result = powerLog(x, n/2) * powerLinear(x, n/2);
        result *= (n % 2 == 0) ? 1 : x;
        return result;
    }

    public static int factorial(int n) {
        if (n == 0) {
            return 1;
        }

        return  n * factorial(n - 1);
    }

    public static void printZigZag(int n) {
        if (n == 0) {
            return;
        }

        System.out.println("Pre : " + n);
        printZigZag(n-1);
        System.out.println("In : " + n);
        printZigZag(n-1);
        System.out.println("Post : " + n);
    }

    public static void printArray(int[] arr, int index) {

        if (index == arr.length) {
            return;
        }

        System.out.println(arr[index]);
        printArray(arr, index + 1);
    }

    public static void printArrayReverse(int[] arr, int index) {

        if (index == arr.length) {
            return;
        }

        printArrayReverse(arr, index + 1);
        System.out.println(arr[index]);
    }

    public static int findMax(int[] arr, int index) {
        if (index == arr.length) {
            return 0;
        }

        return Math.max(arr[index], findMax(arr, index+1));
    }

    public static Integer findFirstOccurance(int[] arr, int index, int val) {
        if (index == arr.length) {
            return null;
        }

        if (arr[index] == val) {
            return index;
        }

        return findFirstOccurance(arr, index + 1, val);
    }

    public static Integer findLastOccurance(int[] arr, int index, int val) {
        if (index == arr.length) {
            return null;
        }

        Integer result =  findLastOccurance(arr, index + 1, val);

        if (result == null && arr[index] == val) {
            return index;
        }

        return result;
    }

    public static List<Integer> findAllIndex(int[] arr, int val) {
        List<Integer> result = new ArrayList<>();
        populateIndexes(arr, 0, val, result);
        return result;
    }

    private static void populateIndexes(int[] arr, int index, int val, List<Integer> list) {
        if (index == arr.length) {
            return;
        }

        if (val == arr[index]) {
            list.add(index);
        }

        populateIndexes(arr, index + 1, val, list);
    }

    public static List<String> generateSubSequences(String str) {

        if(str == null || "".equals(str)) {
            return Arrays.asList("");
        }

        char currentCharacter = str.charAt(0);
        String remainingString = str.substring(1);
        List<String> subsequenceFromRemainingString = generateSubSequences(remainingString);

        List<String> result = new ArrayList<>();

        for(String substring : subsequenceFromRemainingString) {
            result.add(substring);
            result.add(currentCharacter + substring);
        }

        return result;
    }

    private static Map<Integer, String> characterMap = new HashMap<>();

    static {
        characterMap.put(2, "abc");
        characterMap.put(3, "def");
        characterMap.put(4, "ghi");
        characterMap.put(5, "jkl");
        characterMap.put(6, "mno");
        characterMap.put(7, "pqrs");
        characterMap.put(8, "tuv");
        characterMap.put(9, "wxyz");
    }

    public static List<String> letterCombination(String digits) {
        List<String> result = new ArrayList<>();
        populateLetterCombination(digits, 0, result, "");
        return result;
    }

    public static void populateLetterCombination(String digits, int index, List<String> result, String current) {
        if (index == digits.length()) {
            result.add(current);
            return;
        }

        int digit = digits.charAt(index) - '0';
        String mappedCharacters = characterMap.get(digit);
        if (mappedCharacters == null) {
            return;
        }

        for(char ch : mappedCharacters.toCharArray()) {
            populateLetterCombination(digits, index + 1, result, current + ch);
        }
    }

    public static List<String> getStairPaths(int n) {
        List<String> result = new ArrayList<>();

        if (n == 0) {
            return result;
        }

        populateStairPaths(n, result, "");

        return result;
    }

    private static void populateStairPaths(int n, List<String> result, String currentPath) {
        if (n < 0) {
            return;
        }

        if (n == 0) {
            result.add(currentPath);
            return;
        }

        populateStairPaths(n-1, result, n + currentPath );
        populateStairPaths(n-2, result, n + currentPath);
    }

    public static List<String> getMazePaths(int m, int n) {
        List<String> paths = new ArrayList<>();

        if (m != 0 && n != 0) {
            populateMazePath(0, 0, m, n, paths, "");
        }

        return paths;
    }

    private static void populateMazePath(int i, int j, int m, int n, List<String> result, String path) {
        if (i == m-1 && j == n-1) {
            result.add(path);
            return;
        }

        if (i >= m || j >= n) {
            return;
        }

        populateMazePath(i + 1, j, m, n, result, path + "v");
        populateMazePath(i , j + 1, m, n, result, path + "h");
    }

    public static List<String> generateAllPermutations(String input) {
        if (null == input || "".equals(input)) {
            return new ArrayList<>();
        }

        List<String> result = new ArrayList<>();
        populatePermutation(input.toCharArray(), 0, result);
        return result;
    }

    private static void populatePermutation(char[] arr, int index, List<String> result) {

        if (index == arr.length) {
            result.add(arr.toString());
            return;
        }

        for(int j = index; j < arr.length; j++) {
            ArrayHelper.swap(arr, index, j);
            populatePermutation(arr, index + 1, result);
            ArrayHelper.swap(arr, index, j);
        }
    }

    public static List<String> computeEncodings(String number) {
        List<String> encodings = new ArrayList<>();

        populateEncodings(number.toCharArray(), 0, "", encodings);

        return encodings;
    }

    private static void populateEncodings(char[] number, int index, String curr, List<String> result) {

        if (index == number.length) {
            result.add(curr);
            return;
        }

        int num =  number[index] - '0';
        if (num > 0 && num < 10) {
            char ch = (char)(num  + 'a' - 1);
            populateEncodings(number, index + 1, curr + ch, result);
        }

        if (index + 1 < number.length) {
            int d = number[index+1]-'0';
            num = num * 10 + d;

            if (num >= 10 && num <= 26) {
                char ch = (char) (num + 'a' - 1);
                populateEncodings(number, index + 2, curr + ch, result);
            }
        }
    }

    public static List<String> floodFillPaths(int[][] matrix) {

        int m = matrix.length;
        if (m == 0) {
            return new ArrayList<>();
        }

        int n = matrix[0].length;
        if (n == 0) {
            return new ArrayList<>();
        }

        boolean[][] visited = new boolean[m][n];

        List<String> result = new ArrayList<>();

        floodFillInternal(m, n, 0, 0, matrix, visited, "", result);

        return result;
    }

    private static void floodFillInternal(int m, int n, int i, int j, int[][] matrix, boolean[][] visited, String psf, List<String> result) {

        if (i < 0 || i >= m) {
            return;
        }

        if (j < 0 || j >= n) {
            return;
        }

        if (visited[i][j]) {
            return;
        }

        if (matrix[i][j] == 1) {
            return;
        }

        if (i == m - 1 && j == n - 1) {
            result.add(psf);
            return;
        }

        visited[i][j] = true;

        floodFillInternal(m, n, i + 1, j, matrix, visited, psf + "v", result);
        floodFillInternal(m, n, i, j + 1, matrix, visited, psf + "h", result);

        visited[i][j] = false;
    }

    public static List<List<Integer>> findSubsets(int[] nums, int target) {
        if (target == 0) {
            List<List<Integer>> result =  new ArrayList<>();
            result.add(new ArrayList<>());
            return result;
        }

        if (nums.length == 0) {
            return new ArrayList<>();
        }

        List<List<Integer>> result = new ArrayList<>();

        populateSubSetSumList(nums, 0, result, new ArrayList<>(), 0, target);

        return result;
    }

    private static void populateSubSetSumList(int[] nums, int index, List<List<Integer>> result, List<Integer> curr, int sum, int target) {
        if (index == nums.length) {

            if (sum == target) {
                List<Integer> clone = new ArrayList<>();
                for (Integer val : curr) {
                    clone.add(val);
                }

                curr.clear();
                result.add(clone);
            }


            return;
        }

        populateSubSetSumList(nums, index + 1, result, curr, sum , target);

        curr.add(nums[index]);

        populateSubSetSumList(nums, index + 1, result, curr, sum + nums[index], target);

        curr.remove(Integer.valueOf(nums[index]));
    }

    public static int[][] placeNQueens(int n) {
        int[][] board = new int[n][n];



        return board;
    }

    private static void populateNQueens(int[][] board, int row, int col) {

        if (row == board.length) {
            for(int i = 0; i < board.length; i++) {
                System.out.println(Arrays.toString(board[i]));
            }
            System.out.println();

            return;
        }

        if (!isSafe(board, row, col)) {
            return;
        }

        for(int j = 0; j < col; j++) {
            board[row][col] = 1;

            populateNQueens(board, row + 1, j);

            board[row][col] = 0;
        }
    }

    private static boolean isSafe(int[][] board, int row, int col) {

        //check vertially above
        for(int i = row - 1; i >= 0; i--) {
            if (board[i][col] == 1) {
                return false;
            }
        }

        for(int i = row -1, j = col -1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        for(int i = row -1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        return true;
    }


}
