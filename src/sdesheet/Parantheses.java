package sdesheet;

import java.util.Stack;

public class Parantheses {

    public String minRemovalToMakeStringValid(String input) {
        StringBuilder builder = new StringBuilder();

        int open = 0;

        for(char c : input.toCharArray()) {
            if (c == '(') {
                open++;
            } else if (c == ')') {
                if (open == 0) {
                    continue;
                }

                open--;
            }

            builder.append(c);
        }

        StringBuilder revBuilder = new StringBuilder();

        int close = 0;

        for(int i = builder.length() -1 ; i >= 0; i--) {
            char ch = input.charAt(i);
            if (ch == '(' && open-- > 0) {
                revBuilder.append(builder.charAt(i));
            }
        }

        return revBuilder.reverse().toString();
    }

    public static int minInsertionToMakeValid(String input) {
        int bal = 0;

        int ans = 0;

        for(char ch : input.toCharArray()) {
            bal += (ch == '(') ? 1 : -1;

            if (bal == -1) {
                ans += 1;
                bal = 0;
            }
        }

        return ans;
    }

    public static int longestValidParenthesis(String input) {
        Stack<Character> stack = new Stack<>();
        Stack<Integer> indexStack = new Stack<>();

        indexStack.push(-1);

        int maxLen = 0;

        for(int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == '(') {
                stack.push('(');
            } else {
                if (!stack.isEmpty()) {
                    stack.pop();
                }

                maxLen = Math.max(maxLen, i - indexStack.peek());
            }
        }

        return maxLen;
    }

}
