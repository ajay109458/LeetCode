package stack;

import utils.Pair;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StackHelper {

    public static String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();

        if (null == path || "".equals(path)) {
            return path;
        }

        String[] dirNames = path.split("/");

        for(String dirName : dirNames) {
            if (".".equals(dirName) || "".equals(dirName)) {
                continue;
            } else if ("..".equals(dirName)) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(dirName);
            }
        }

        List<String> simplifiedDirNames = new ArrayList<>();
        while(!stack.isEmpty()) {
            simplifiedDirNames.add(0, stack.pop());
        }

        String result =  String.join("/", simplifiedDirNames);

        return "/" + result;
    }

    public String decodeAtIndex(String S, int K) {
        Stack<Character> stack = new Stack<>();

        StringBuilder temp = new StringBuilder();
        for(char ch : S.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                while(!stack.isEmpty()) {
                    temp.insert(0, stack.pop());
                }

                int count = ch - '0';

                while (count-- > 0) {
                    temp.append(temp.toString());
                }
            } else {
                stack.push(ch);
            }
        }

        return temp.toString().charAt(K) + "";
    }


    /**
     * n >= 0
     *
     * remove k digits
     *
     * so resulting number is min possible
     *
     * 1 4 3 2 2 1 9 ->
     *
     * 1 0 2 0 0
     *
     * 1
     * @return
     */
    public String removeKdigits(String num, int k) {
        Stack<Character> stack = new Stack<>();

        for (char ch : num.toCharArray()) {

            while(!stack.isEmpty() && stack.peek() > ch && k > 0) {
                stack.pop();
                k--;
            }

            if (!stack.isEmpty() && ch != '0')
                stack.push(ch);
        }

        while(!stack.isEmpty() && k-- > 0) {
            stack.pop();
        }

        if (stack.isEmpty()) {
            return "0";
        }

        StringBuilder builder = new StringBuilder();
        while(!stack.isEmpty()) {
            builder.insert(0, stack.pop());
        }

        return builder.toString();
    }

    public String frequencySort(String s) {

        Map<Character, Long> countByChar = s.chars()
                .mapToObj(c -> (char)c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        List<String> characters = countByChar.entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .map(e1 -> e1.getKey().toString())
                .collect(Collectors.toList());

        return String.join("", characters);
    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Long> freqByNumMap = Arrays.stream(nums)
                .mapToObj(num -> (Integer)num)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        List<Integer> result = freqByNumMap.entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .map(e -> e.getKey())
                .limit(k)
                .collect(Collectors.toList());

       int[] arr = new int[result.size()];

       for(int i = 0; i < result.size(); i++) {
           arr[i] = result.get(i);
       }

       return arr;
    }

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        Set<String> ops= new HashSet<>(Arrays.asList("+", "-", "*", "/"));

        for(String token: tokens) {
            if (ops.contains(token)) {
                Integer val1 = stack.pop();
                Integer val2 = stack.pop();

                switch (token) {
                    case "+":
                        stack.push(val1 + val2);
                        break;
                    case "*":
                        stack.push(val1 * val2);
                        break;
                    case "/":
                        stack.push(val1 / val2);
                        break;
                    case "-":
                        stack.push(val1 - val2);
                        break;
                }
            } else {
                stack.push(Integer.parseInt(token));
            }
        }

        return stack.peek();
    }

    public int calculate(String s) {
        int sign = 1;

        int sum = 0;

        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < s.length(); i++) {

            // number
            char ch =  s.charAt(i);
            if (ch >= '0' && ch <= '9') {
                int num = 0;

                while(i < s.length() && ch >='0' && ch <= '9') {
                    num = num * 10 + (ch - '0');
                    i++;
                }

                sum += sign * num;
                i--;
            } else if (ch == '+') {
                sign = 1;
            } else if (ch == '-') {
                sign = -1;
            } else if (ch == '(') {
                stack.push(sum);
                stack.push(sign);
                sum = 0;
                sign = 1;
            } else if (ch == ')') {
                sum = stack.pop() * sum;
                sum += sum;
            }
        }

        return sum;
    }

    class MyQueue {

        Stack<Integer> stack ;

        /** Initialize your data structure here. */
        public MyQueue() {
            stack = new Stack<>();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            Stack<Integer> tempStack = new Stack<>();

            while(!stack.isEmpty()) {
                tempStack.push(stack.pop());
            }

            stack.push(x);

            while(!tempStack.isEmpty()) {
                stack.push(tempStack.pop());
            }
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            return stack.pop();
        }

        /** Get the front element. */
        public int peek() {
            return stack.peek();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return stack.isEmpty();
        }
    }

    public boolean isValidSerialization(String preorder) {
        Stack<String> stack = new Stack<>();
        String[] strs = preorder.split(",");

        for(String ch : strs) {
            ch = ch.trim();
            if ("#".equals(ch)) {
                if (!stack.isEmpty() && "#".equals(stack.peek())) {
                    stack.pop();
                    if (stack.isEmpty())
                        return false;
                    stack.pop();
                }
            }

            System.out.println(stack);

            stack.push(ch);
        }

        while(!stack.isEmpty() && "#".equals(stack.peek())) {
            stack.pop();
        }

        return stack.isEmpty();
    }

    public String decodeString(String s) {
       Stack<Integer> counts = new Stack<>();

       Stack<String> results = new Stack<>();

       String curr = "";

       int index = 0;

       while(index < s.length()) {
           char ch = s.charAt(index);
           if (Character.isDigit(ch)) {
                int count = 0;

                while(Character.isDigit(ch)) {
                    index++;
                    count = count * 10 + (ch - '0');
                    ch = s.charAt(index);
                }

                counts.push(count);
           } else if (ch == '[') {
               results.push(curr);
               curr = "";
               index++;

           } else if (ch == ']') {
                StringBuilder temp = new StringBuilder(results.pop());
                int count = counts.pop();
                while(count-- > 0) {
                    temp.append(curr);

                }

                curr = temp.toString();
                index++;
           } else {
               curr += ch;
               index++;
           }


       }

       return curr;
    }

    public String parseTernary(String expression) {
        Stack<String> stack = new Stack<>();

        int index = 0;

        while(index < expression.length()) {
            char ch = expression.charAt(index);

            if (Character.isDigit(ch)) {
                String num = "";
                while(Character.isDigit(ch)) {
                    num = num + ch ;
                    index++;
                    ch = expression.charAt(index);
                }
                index--;
                stack.push(num);
            } else if (ch == 'T' || ch == 'F') {
                stack.push(ch + "");
            }

            index++;
        }

        String res = "";
        while(!stack.isEmpty()) {
            String b = stack.pop();
            String a = stack.pop();
            String op = stack.pop();

            if ("T".equals(op)) {
                res = a;
            } else if ("F".equals(op)) {
                res = b;
            }

            if (stack.isEmpty()) {
                break;
            } else {
                stack.push(res);
            }
        }

        return res;
    }

    public boolean find132pattern(int[] nums) {

        int n = nums.length;

        if (nums.length == 0)
            return false;

        int[] minFront = new int[n];

        Stack<Integer> stack = new Stack<>();

        minFront[0] = nums[0];
        for(int i = 1; i < nums.length; i++) {
            nums[i] = Math.min(nums[i], nums[i-1]);
        }

        for(int i = n -1; i >= 0; i--) {
            if (nums[i] > minFront[i]) {
                while(!stack.isEmpty() && stack.peek() <= minFront[i]) {
                    stack.pop();
                }

                if (!stack.isEmpty() && stack.peek() < nums[i]) {
                    return true;
                }

                stack.push(nums[i]);
            }
        }

        return false;
    }

    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;


        int[] outputArr = new int[n];
        Arrays.fill(outputArr, -1);

        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < nums.length * 2; i++) {
            if (!stack.isEmpty() && nums[stack.peek()] < nums[i % n]) {
                outputArr[stack.pop()] = nums[i%n];
            }

            if (i < n) {
                stack.push(i);
            }
        }

        return outputArr;
    }

    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();

        for(int asteroid : asteroids) {
            if(stack.isEmpty()) {
                stack.push(asteroid);
            } else if (stack.peek() > 0 && asteroid > 0) {
                stack.push(asteroid);
            } else if (stack.peek() < 0 && asteroid < 0) {
                stack.push(asteroid);
            } else if(stack.peek() > 0 && asteroid < 0) {

                while (stack.peek() > 0 && asteroid < 0 && Math.abs(stack.peek()) < Math.abs(asteroid)) {
                    stack.pop();
                }

                if (stack.peek() > 0 && asteroid < 0 && Math.abs(stack.peek()) <= Math.abs(asteroid)) {
                    stack.pop();
                    continue;
                }

                if (stack.isEmpty() || !(stack.peek() > 0 && asteroid < 0)) {
                    stack.add(asteroid);
                }
            } else {
                stack.push(asteroid);
            }
        }

        int[] res = new int[stack.size()];
        for(int i = res.length -1; i >= 0; i--) {
            res[i] = stack.pop();
        }
        return res;
    }

    public static String countOfAtoms(String formula) {
        Stack<String> stack = new Stack<>();

        int index = 0;

        String atom = "";

        while(index < formula.length()) {
            char ch = formula.charAt(index);

            if (ch >= 'A' && ch <= 'Z' ) {
                if (!atom.isEmpty()) {
                    stack.push(atom);
                }
                atom = ch + "";
            } else if (ch >= 'a' && ch <= 'z' ) {
                atom += ch;
            } else if (Character.isDigit(ch)) {
                if (!atom.isEmpty()) {
                    stack.push(atom);
                }
                int count = 0;

                String res = "";

                while(index < formula.length() && Character.isDigit(ch)) {
                    count = count * 10 + (ch - '0');
                    index++;
                    ch = formula.charAt(index);
                }

                index--;

                if (!stack.isEmpty()) {
                    String top = stack.pop();
                    String decode = "";
                    if (")".equals(top)) {
                        while(!stack.isEmpty()) {
                            top = stack.pop();
                            if ("(".equals(stack.peek())) {
                                break;
                            }

                            decode = top + decode;
                        }
                    } else {
                        decode = top;
                    }

                    while(count-- > 0) {
                        res += decode;
                    }

                    stack.push(res);
                }
            } else {
                if (!atom.isEmpty()) {
                    stack.push(atom);
                }
                stack.push(ch + " ");
            }

            index++;
        }

        if (!atom.isEmpty()) {
            stack.push(atom);
        }

        String result = "";

        while(!stack.isEmpty()) {
            result = stack.pop() + result;
        }

        return "";
    }

    public String makeGood(String s) {
        Stack<Character> stack = new Stack<>();

        for(char ch : s.toCharArray()) {
            boolean shouldAdd = true;
            if (!stack.isEmpty()) {
                char peek = stack.peek();

                if (ch -'a' == peek - 'A' || ch - 'A' == peek-'a') {
                    stack.pop();
                    shouldAdd = false;
                }
            }

            if (shouldAdd)
                stack.push(ch);
        }

        String res = "";
        while(!stack.isEmpty()) {
            res = stack.pop() + res;
        }

        return res;
    }

    public int minOperations(String[] logs) {
        int count = 0;
        for(String log : logs) {
            if (log.equals("../")) {
                if (count > 0) {
                    count--;
                }
            } else if (log.equals("./")) {

            } else {
                count++;
            }
        }

        return count;
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for(char ch : s.toCharArray()) {
            switch (ch) {
                case '(' :
                case '{':
                case '[':
                    stack.push(ch);
                    break;
                case ']':
                case '}':
                case ')':
                    if (!stack.isEmpty())
                        stack.pop();
            }
        }

        return stack.isEmpty();
    }

    public static int maxClosestDistance(int[] seats) {
        int left = -1;

        int n = seats.length;

        int maxSeats = 0;

        for(int i = 0; i < n; i++) {
            if (seats[i] == 1) {
                if (left == - 1) {
                    maxSeats = i;
                } else {
                    maxSeats = Math.max(maxSeats, (i - left)  / 2);
                }

                left = i;
            }


        }

        maxSeats = Math.max(maxSeats, (n - left - 1));

        return maxSeats;
    }

    public String minRemoveToMakeValid(String s) {

        StringBuilder builder = new StringBuilder();

        int open = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                open++;
            } else if (ch== ')') {
                if (open == 0)
                    continue;

                open--;
            }

            builder.append(ch);
        }

        StringBuilder result = new StringBuilder();
        for(int i = builder.length() - 1; i >= 0; i--) {

            if (builder.charAt(i) == '(' && open-- > 0)
                continue;

            result.append(builder.charAt(i));
        }

        return result.reverse().toString();
    }

    public static List<Pair> mergeKIntervals(List<Pair> intervals) {
        List<Pair> result = new ArrayList<>();

        Collections.sort(intervals, (a, b) -> Integer.compare(a.x, b.x));

        Stack<Pair> stack = new Stack<>();

        for(Pair interval : intervals) {
            if (!stack.isEmpty() && stack.peek().y > interval.x) {
                stack.peek().y = Math.max(stack.peek().y, interval.y);
            } else {
                stack.push(interval);
            }
        }

        while(!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return result;
    }

}
