package companies;

import array.ArrayHelper;
import utils.Pair;

import javax.xml.stream.events.Characters;
import java.util.*;

public class ArrayProblems {

    public static int longestPalindromicSubsequence(String input) {
        int n = input.length();

        int[][] dp = new int[n][n];

        for(int len = 1; len <= n; len++) {
            for(int i = 0; i < n - len+1; i++) {
                int j = i + len - 1;

                if (i == j) {
                    dp[i][j] = 1;
                } else if (i+1 == j) {
                    if (input.charAt(i) == input.charAt(j))  {
                        dp[i][j] =2;
                    }
                } else {
                    if (input.charAt(i) == input.charAt(j)) {
                        dp[i][j] = dp[i+1][j-1] +2;
                    } else {
                        dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                    }
                }
            }
        }

        return dp[0][n-1];
    }

    public static int getMaxMatrix(int[][] matrix) {

        int m = matrix.length;
        if (m == 0) {
            return -1;
        }

        int n = matrix[0].length;

        if (n == 0) {
            return -1;
        }

        int[][] storage = new int[m][n];

        int maxLen = 0;

        for(int i = 0 ; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    storage[i][j] = matrix[i][j];
                } else {
                    if (matrix[i][j] == 1) {
                        storage[i][j] = 1 + Math.min(storage[i-1][j-1], Math.min(storage[i-1][j], storage[i][j-1]));
                    }
                }

                if (maxLen < storage[i][j]) {
                    maxLen = storage[i][j];
                }
            }
        }

        return maxLen;
    }

    private static int maxHistogramArea(int[] arr) {
        return 0;
    }

    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) {
            return 0;
        }

        int n = matrix.length;
        if (n == 0) {
            return 0;
        }

        int[] dp = new int[n];

        int maxArea = 0;

        for(int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    dp[j] += matrix[i][j];
                } else {
                    dp[j] = 0;
                }
            }

            int area = maxHistogramArea(dp);

            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }

    public static void rotateAMatrix(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) {
            return;
        }

        int n = matrix[0].length;
        if (n == 0) {
            return;
        }

        for(int i = 0; i < matrix.length; i++) {
            for(int j = i+1; j < matrix[0].length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        for(int i = 0; i < m; i++) {
            ArrayHelper.reverse(matrix[i]);
        }
    }

    public static void printSpiralMatrix(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) {
            return;
        }

        int n = matrix[0].length;
        if (n == 0) {
            return;
        }

        int left = 0;
        int right = n-1;

        int top = 0;
        int bottom = m -1;

        while(left <= right && top <= bottom) {

            for(int j = left; j <= right; j++) {
                System.out.print(matrix[top][j] + " ");
            }

            top++;
            if (top > bottom) {
                break;
            }


            for(int i = top; i <= bottom; i++) {
                System.out.print(matrix[i][right] + " ");
            }

            right--;

            if (left > right) {
                break;
            }

            for(int j = right; j >= left; j--) {
                System.out.print(matrix[bottom][j] + " ");
            }

            bottom--;
            if (top > bottom) {
                break;
            }

            for(int i = bottom; i >= top; i--) {
                System.out.print(matrix[i][left] + " ");
            }

            left++;

        }
    }

    public static boolean isJumpPossible(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        dp[0] = 0;

        for(int i = 1; i < n ; i++) {
            dp[i] = Integer.MAX_VALUE;

            for(int j = 0; j < i; j++) {
                if (j + arr[j] >= i && arr[j] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }

        return dp[n-1] != Integer.MAX_VALUE;
    }

    public int read(char[] buf, int n) {
        char[] temp = new char[4];
        int total = 0;

        while(total < n) {
            int count = read4(temp);

            for(int i = 0; i < count && total + i < n; i++) {
                buf[total ++] = temp[i];
            }
        }

        return total;
    }

    public static List<String> findMissingRange(int[] nums, int lower, int upper) {
        List<String> ranges = new ArrayList<>();

        for(int i = 0; i < nums.length; i++) {
            if (nums[i] > lower) {
                if (nums[i]-lower == 1) {
                    ranges.add(lower + "");
                } else {
                    ranges.add(lower + "->" + nums[i]);
                }
            }

            if (nums[i] == Integer.MAX_VALUE) {
                return ranges;
            }

            lower = nums[i] + 1;
        }

        if (lower < upper) {
            ranges.add(lower + "->" + upper);
        } else {
            ranges.add(lower + "");
        }

        return ranges;
    }

    private static int read4(char[] buf) {
        return 0;
    }

    public static String findClosestTime(String input) {
        String[] components = input.split(":");

        int minutes = Integer.parseInt(components[1]);
        int hours = Integer.parseInt(components[0]);

        int totalMinutes = hours * 60 + minutes;

        HashSet<Integer> digits = new HashSet<>();
        for(char ch : input.toCharArray()) {
            digits.add(ch - '0');
        }

        while(true) {
            totalMinutes = (totalMinutes + 1) % (24 * 60);

            int hourGen = totalMinutes/60;
            int minGen = totalMinutes%60;

            int[] nextTime = {hourGen/10, hourGen%10, minGen/10, minGen%10};
            boolean isValid = true;
            for(int digit : nextTime) {
                if (!digits.contains(digit)) {
                    isValid = false;
                }
            }

            if (isValid) {
                return String.format("%02d:%02d", hourGen, minGen);
            }
        }
    }

    public int maxDistToClosest(int[] seats) {

        int n = seats.length;

        int left = -1;

        int max = 0;
        for(int i = 0; i < n ; i++) {
            int seat = seats[i];
            if (seat == 1) {
                max = Math.max(max, (left == -1) ? i : (i - left)/2);
                left = i;
            }
        }

        return Math.max(max, n - left-1);
    }

    public double minCostToHireWorkers(int[] quality, int[] wage, int K) {

        int n = quality.length;

        double minCost = Double.MAX_VALUE;

        for(int captain = 0; captain < n; captain++) {
            double captainRatio = wage[captain]/quality[captain];

            List<Double> acceptedOffers = new ArrayList<>();
            for(int worker = 0; worker < n; worker++) {
                double offer = captainRatio * quality[worker];

                if (offer >= wage[worker]) {
                    acceptedOffers.add(offer);
                }
            }

            if (acceptedOffers.size() < K) {
                continue;
            }

            // Find K smallest offers
            PriorityQueue<Double> pq = new PriorityQueue<>((a, b) -> Double.compare(b, a));
            double totalCost = 0;

            for(int i = 0; i < K; i++) {
                pq.add(acceptedOffers.get(i));
                totalCost += acceptedOffers.get(i);
            }

            for(int i = K; i < acceptedOffers.size(); i++) {
                if (acceptedOffers.get(i) < pq.peek()) {
                    double rem = pq.remove();
                    totalCost -= rem;
                    pq.add(acceptedOffers.get(i));
                    totalCost += acceptedOffers.get(i);
                }
            }

            minCost = Math.min(minCost, totalCost);
        }

        return minCost;

    }

    /**
     *
     * Merge two sorted array and you can find the median of two sorted array
     * in O(m + n) time
     *
     * @param arr1
     * @param arr2
     * @return
     */
    public static int getMedian(int[] arr1, int[] arr2) {
        int x = arr1.length;
        int y = arr2.length;

        int low = 0;
        int high = x-1;

        while(low <= high) {
            int partitionX = (low + high) / 2;

            int partitionY = (x + y + 1)/2 - partitionX;

            int minX = (partitionX == 0) ? Integer.MIN_VALUE : arr1[partitionX-1];
            int maxX = (partitionX == arr1.length) ? Integer.MAX_VALUE : arr1[partitionX];

            int minY = (partitionY == 0) ? Integer.MIN_VALUE: arr2[partitionY - 1];
            int maxY = (partitionY == arr1.length) ? Integer.MAX_VALUE : arr2[partitionY];

            if (minX <=  maxY && minY <= maxX) {
                if ((x+y) % 2 == 0) {
                    return (Math.max(minX, minY) + Math.min(maxX, maxY))/2;
                } else {
                    return Math.max(minX, minY);
                }
            } else if (minY >= maxX) {
                low = partitionX + 1;
            } else {
                high = partitionX - 1;
            }
        }

        return -1;
    }

    HashSet<String> seen;
    StringBuilder ans;

    public String crackSafe(int n, int k) {
        if(n == 1 && k == 1) {
            return "0";
        }

        seen = new HashSet<>();
        ans = new StringBuilder();

        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < n-1; i++) {
            builder.append("0");
        }

        String start = builder.toString();

        dfs(start, k);

        ans.append(start);

        return ans.toString();

    }

    public void dfs(String node, int k) {
        for(int x = 0; x < k; x++) {
            String val = node + x;

            if (!seen.contains(val)) {
                seen.add(val);
                dfs(val.substring(1), k);

                ans.append(x);
            }
        }
    }

    public boolean isAlienSorted(String[] words, String order) {
        Arrays.sort(words);


        int[] orderMapping = new int[26];
        for(int i = 0; i < order.length(); i++) {
            orderMapping[order.charAt(i)-'a'] = i;
        }

        for(int i =1; i < words.length; i++) {

            String word1 = words[i-1];
            String word2 = words[i];

            int j = 0;
            int k = 0;

            while(j < word1.length() && k < word2.length()) {

                char ch1 = word1.charAt(j);
                char ch2 = word2.charAt(k);

                if (orderMapping[ch1-'a'] > orderMapping[ch2-'a']) {
                    return false;
                } else if (orderMapping[ch1-'a'] < orderMapping[ch2-'a']) {
                    break;
                } else {
                    j++;
                    k++;
                }
            }

            if (k == word2.length() && j != word1.length()) {
                return false;
            }
        }

        return true;
    }

    public static String removeKDigits(String input, int k) {
        Stack<Character> stack = new Stack<>();

        for(char ch : input.toCharArray()) {
            while(!stack.isEmpty() && stack.peek() > ch && k-- > 0) {
                stack.pop();
            }

            if (!stack.isEmpty() || ch != '0')
                stack.push(ch);
        }

        StringBuilder builder = new StringBuilder();

        while(!stack.isEmpty()) {
            builder.insert(0, stack.pop());
        }

        return builder.toString();
    }


    public static int maximumSizeContainer(int[] arr) {

        int left = 0;
        int right = arr.length - 1;

        int maxArea = 0;

        while(left <= right) {
            int area = Math.min(arr[left], arr[right]) * (right - left);
            maxArea = Math.max(area, maxArea);

            if (arr[left] <= arr[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

    public static String nextPermutationOfNumber(String number) {

        int n = number.length();
        char[] arr = number.toCharArray();

        int i;
        for(i = number.length() - 2; i >= 0; i--) {
            if (number.charAt(i) < number.charAt(i+1)) {
                break;
            }
        }

        if (i >= 0) {
            int maxIndex = i+1;

            for(int j = i+1; j < n; j++) {
                if (number.charAt(j) > number.charAt(i) && number.charAt(j) < number.charAt(maxIndex)) {
                    maxIndex = j;
                }
            }

            ArrayHelper.swap(arr, maxIndex, i);
        }

        ArrayHelper.reverse(arr, i+1, arr.length -1);

        return arr.toString();
    }

    public static boolean canJump(int[] nums) {
        int lastGoodPosition = nums.length - 1;

        for(int i =  nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= lastGoodPosition) {
                lastGoodPosition = i;
            }
        }

        return lastGoodPosition == 0;
    }

    public static String nextClosestTime(String time) {
        int minutes = Integer.parseInt(time.substring(0, 2)) * 60;
        minutes += Integer.parseInt(time.substring(3));

        HashSet<Integer> digits = new HashSet<>();
        for(char ch : time.toCharArray()) {
            digits.add(ch - '0');
        }

        while (true) {
            minutes = (minutes + 1) % (60 *24);

            int[] nextTime = {minutes/60/10, minutes/60%10, minutes%60/10, minutes%60%10};

            boolean isValid = true;
            for(int digit : nextTime) {
                if (!digits.contains(digit)) {
                    isValid = false;
                }
            }

            if (isValid) {
                return String.format("%02d:%02d", minutes/60, minutes%60);
            }
        }
    }


    public static int longestValidParenthesis(String s) {

        Stack<Character> charStack = new Stack<>();
        Stack<Integer> indexStack = new Stack<>();

        indexStack.push(-1);

        int max = 0;

        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == ')') {
                if (!charStack.isEmpty() && charStack.peek() == '(') {
                    charStack.pop();
                    indexStack.pop();
                    max = Math.max(max, i - indexStack.peek());
                } else {
                    indexStack.add(i);
                }

            } else {
                charStack.push(ch);
                indexStack.push(i);
            }
        }

        return max;
    }

    private static int factorial(int n) {
        if (n == 0 )
            return 1;

        return n * factorial(n-1);
    }

    public static String getKthPermutation(List<Characters> charList, int k, String result) {
        if (charList.isEmpty()) {
            return result;
        }

        int n = charList.size();

        int count = factorial(n-1);
        int selected = (k-1) / count;

        result += charList.remove(selected);

        k = k - selected * count;

        return getKthPermutation(charList, k, result);
    }

    public static double medianOfTwoSortedArray(int[] input1, int[] input2) {

        if (input1.length > input2.length) {
            return medianOfTwoSortedArray(input2, input1);
        }

        int x = input1.length;
        int y = input2.length;

        int low = 0;
        int high = x;

        while(low <= high) {

            int partitionX = low + (high - low) /2;
            int partitionY = (x + y + 1)/2 - partitionX;

            int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : input1[partitionX-1];
            int minRightX = (partitionX == x) ? Integer.MAX_VALUE : input1[partitionX];

            int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : input2[partitionY-1];
            int minRightY = (partitionY == y) ? Integer.MAX_VALUE : input2[partitionY];


            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                if ((x + y) % 2 == 0) {
                    return ((double)Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY))/2;
                } else {
                    return (double)Math.max(maxLeftX, maxLeftY);
                }
            } else if (maxLeftX > minRightY) {
                high = partitionX - 1;
            }
        }

        return -1;

    }

}
