package prepcoding;

import utils.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class StackQueue {

    public static boolean isDuplicateBrackets(String input) {

        Stack<Character> stack = new Stack<>();

        for(char ch : input.toCharArray()) {
            if (ch == ')') {

                int count = 0;

                while(!stack.isEmpty() && stack.peek() != '(') {
                    stack.pop();
                    count++;
                }

                if (count == 0)
                    return true;

                if (!stack.isEmpty() && stack.peek() == '(')
                    stack.pop();

            } else {
                stack.push(ch);
            }
        }

        return false;
    }



    public static boolean isDuplicatePairOfBraces(String expression) {
        Stack<Character> stack = new Stack<>();

        for(char ch : expression.toCharArray()) {
            if (ch == ')') {

                int count = 0;
                while(stack.peek() != '(') {
                    stack.pop();
                    count++;
                }

                if (count == 0) {
                    return true;
                }

                stack.pop();

            } else {
                stack.push(ch);
            }
        }

        return false;
    }

    public static boolean isBalancedBrackets(String expression) {
        Stack<Character> stack = new Stack<>();

        for(char ch : expression.toCharArray()) {
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else if (ch == ')' || ch == '}' || ch == ']') {
                if (stack.isEmpty())
                    return false;

                char[] ends = {'}', ']', ')'};
                char[] starts = {'{', '[', '('};

                for(int i = 0; i < ends.length; i++) {
                    if (ch == ends[i] && (stack.peek() != starts[i])) {
                        return false;
                    }
                }
            }
        }

        return true;
    }


    public static int[] nextGE(int[] arr) {
        Stack<Integer> stack = new Stack<>();

        int n = arr.length;

        int[] result = new int[n];

        for(int i = n -1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() < arr[i])
                stack.pop();

            result[i] = stack.isEmpty() ? - 1 : stack.peek();

            stack.push(arr[i]);
        }

        return result;
    }

    public static int[] nextGreaterElementOnRight(int[] nums) {
        Stack<Integer> stack =  new Stack<>();

        int[] res = new int[nums.length];

        for(int i = nums.length - 1; i >= 0; i--) {
            while(!stack.isEmpty() && stack.peek() < nums[i]) {
                stack.pop();
            }

            res[i] = (stack.isEmpty()) ? -1 : stack.peek();

            stack.push(nums[i]);
        }

        return res;
    }

    public static void stockSpan(int[] nums) {

        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < nums.length; i++) {
            while(!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                stack.pop();
            }

            int span = i - (stack.isEmpty() ? 0 : stack.peek()) + 1;
            System.out.print(span);
            stack.push(i);
        }
    }

    public static int nextLargestAreaHistogram(int[] nums) {

        int n = nums.length;

        int[] lb = new int[n];
        int[] rb = new int[n];

        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < nums.length; i++) {
            while(!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                stack.pop();
            }

            lb[i] = stack.isEmpty() ? -1 : stack.peek();

            stack.push(i);
        }

        stack.clear();

        for(int i = n - 1; i >= 0; i--) {
            while(!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                stack.peek();
            }

            rb[i] = stack.isEmpty() ? nums.length : stack.peek();

            stack.push(i);
        }

        int maxArea = 0;
        for(int i = 0; i < nums.length; i++) {
            int width = rb[i] - lb[i] -1;
            int area = nums[i] * width;
            maxArea = Math.max(area, maxArea);
        }

        return maxArea;

    }

    public static int largestAreaInHistogram(int[] nums) {

        int n = nums.length;

        int[] lb = new int[n];
        int[] rb = new int[n];

        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < nums.length; i++) {
            while(!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                stack.pop();
            }

            lb[i] = (stack.isEmpty()) ? -1 : i;
        }

        for(int i = n - 1; i >=0; i--) {
            while(!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                stack.pop();
            }

            rb[i] = (stack.isEmpty()) ? n : i;
        }

        int maxArea = 0;
        for(int i = 0; i < nums.length; i++) {
            int width = rb[i] - lb[i]  - 1;

            maxArea = Math.max(maxArea, width * nums[i]);
        }

        return maxArea;

    }

    public static void printSlidingWindowMaximum(int[] nums, int k) {

        int n = nums.length;

        int[] nextGEs = new int[n];

        Stack<Integer> stack = new Stack<>();
        for(int i = n - 1; i >= 0; i--) {
            while(!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                stack.pop();
            }

            nextGEs[i] = stack.isEmpty() ? nums.length : i;

            stack.push(i);
        }

        for(int i = 0; i <= n - k; i++) {
            // Window start with index i

            int j = i;

            while(nextGEs[j] < i+k) {
                j = nextGEs[j];
            }

            System.out.println(nums[j]);
        }

    }

    public static int findCelebrity(int n, boolean[][] doKnow) {

        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < n; i++) {
            stack.push(i);
        }

        while (stack.size() > 1) {
            int x = stack.pop();
            int y = stack.pop();

            if (doKnow[x][y]) {
                stack.push(y);
            } else {
                stack.push(x);
            }


        }

        if (stack.isEmpty()) {
            return -1;
        }

        int possibleCelebrity = stack.pop();

        for(int col = 0; col < n; col++) {
            if (doKnow[possibleCelebrity][col]) {
                return -1;
            }
        }

        for(int row = 0; row < n; row++) {
            if (row != possibleCelebrity && !doKnow[row][possibleCelebrity]) {
                return -1;
            }
        }

        return possibleCelebrity;
    }

    private static class Interval {
        int start;
        int end;
    }

    public static List<Interval> mergeInterval(Interval[] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));

        Stack<Interval> stack = new Stack<>();

        for(Interval interval : intervals) {
            if (stack.isEmpty() && stack.peek().end > interval.start) {
                stack.peek().end = Math.max(stack.peek().end, interval.end);
            }
        }

        List<Interval> result = new ArrayList<>();
        while(!stack.isEmpty()) {
            result.add(0, stack.pop());
        }

        return result;
    }

    public static String smallestPossibleNumber1(String pattern) {
        Stack<Character> stack = new Stack<>();

        StringBuilder result = new StringBuilder();
        int digit = 1;

        for(char ch : pattern.toCharArray()) {

            if (ch == 'i') {
                StringBuilder builder = new StringBuilder();

                while(!stack.isEmpty()) {
                    builder.append(digit);
                    digit++;
                    stack.pop();
                }

                result.append(builder);
            } else {
                stack.push(ch);
            }

        }

        return result.toString();
    }

    public static String smallestPossibleNumber2(String pattern) {
        Stack<Integer> stack = new Stack<>();

        StringBuilder result = new StringBuilder();
        int digit = 1;

        for(char ch : pattern.toCharArray()) {

            stack.push(digit++);
            if (ch == 'i') {
                while(!stack.isEmpty()) {
                    result.append(stack.pop());
                }
            }
        }

        return result.toString();
    }


}
