package Grind75;

import java.util.Stack;

public class Week1 {

    /**
     * Leetcode 001
     * @param nums - Array
     * @param target - Target element
     * @return index of two elements whose sum will be equal to the target
     */
    public static int[] twoSum(int[] nums, int target) {

        int[] result = new int[2];

        // Iterate over each number
        for(int i = 0; i < nums.length; i++) {   // O(n)
            int num = nums[i];
            result[0] = i;

            // Check if target - num exists, if yes return the index.
            int index = getIndex(nums, target - num, i);

            if (index != -1) {
                result[1] = index;
                return result;
            }
        }

        return null;
    }

    private static int getIndex(int[] nums, int val, int currentIndex) {

        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == val && i != currentIndex) {
                return i;
            }
        }

        return -1;
   }

    /**
     * Leetcode 20 - Valid parentheses
     * @param s - Input string
     * @return - true or false based on the string is valid or not.
     */
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for(char ch : s.toCharArray()) {
            if (isOpenBraces(ch)) {
                stack.push(ch);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }

                char popedCh = stack.pop();
                if (!isMatchingEndBraces(ch, popedCh)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    private static boolean isMatchingEndBraces(char ch, char popedCh) {
        return (ch == '}' && popedCh == '{') || (ch == ')' && popedCh == '(') || (ch == ']' && popedCh == '[');
    }

    private static boolean isOpenBraces(char ch) {
        return ch == '(' || ch == '{' || ch == '[';
    }
}
