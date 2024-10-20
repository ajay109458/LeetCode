package LeetcodeBoard;

import array.ArrayHelper;
import tree.TreeNode;
import utils.ListNode;

import java.util.*;
import java.util.stream.Collectors;

public class LeetcodeBoard {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode rHead = null;
        ListNode r = null;

        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return null;
        }

        int carry = 0;

        while(l1 != null && l2 != null) {
            int sum = l1.val + l2.val + carry;
            carry = sum / 10;
            sum = sum % 10;


            ListNode temp = new ListNode(sum);

            if (rHead == null) {
                rHead = temp;
                r = rHead;
            } else {
                r.next = temp;
                r = r.next;
            }

            l1 = l1.next;
            l2 = l2.next;
        }

        while(l1 != null) {
            int sum = l1.val + carry;
            carry = sum / 10;
            sum = sum % 10;

            ListNode temp = new ListNode(sum);

            if (rHead == null) {
                rHead = temp;
                r = rHead;
            } else {
                r.next = temp;
                r = r.next;
            }

            l1 = l1.next;
        }

        while(l2 != null) {
            int sum = l2.val + carry;
            carry = sum/10;
            sum = sum % 10;


            ListNode temp = new ListNode(sum);

            if (rHead == null) {
                rHead = temp;
                r = rHead;
            } else {
                r.next = temp;
                r = r.next;
            }

            l2 = l2.next;
        }

        if (carry != 0) {
            ListNode temp = new ListNode(carry);

            if (rHead == null) {
                rHead = temp;
                r = rHead;
            } else {
                r.next = temp;
                r = r.next;
            }
        }

        return rHead;
    }

    public int maxSubArray(int[] nums) {
        int maxSumSoFar = Integer.MIN_VALUE;
        int currSum = 0;

        for(int num : nums) {
            currSum += num;
            maxSumSoFar = Math.max(maxSumSoFar, currSum);
            if (currSum < 0) {
                currSum = 0;
            }
        }

        return maxSumSoFar;
    }

    public int lengthOfLongestSubstring(String s) {
        int start = 0;
        int curr = 0;

        Set<Character> set = new HashSet<>();
        int maxLen = 0;

        while(curr < s.length()) {

            while (set.contains(s.charAt(curr))) {
                set.remove(s.charAt(start++));
            }

            set.add(s.charAt(curr++));

            maxLen = Math.max(maxLen, set.size());
        }

        return maxLen;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            int first = nums[i];
            int targetSum = -1 * first;

            int left = i+1;
            int right = nums.length - 1;

            while (left < right) {
                int currentSum = nums[left] + nums[right];

                if (currentSum == targetSum) {
                    List<Integer> tuple = Arrays.asList(nums[i], nums[left], nums[right]);
                    result.add(tuple);

                    while(i+1 < nums.length && nums[i] == nums[i+1]) {
                        i++;
                    }

                    while(left < right && nums[left] == nums[left+1]) {
                        left++;
                    }

                    while(left < right && nums[right] == nums[right-1]) {
                        right--;
                    }


                    left++;
                    right--;
                } else if (currentSum < targetSum) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return result;
    }

    public int trap(int[] height) {
        int n = height.length;

        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        leftMax[0] = height[0];
        for(int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i-1], height[i]);
        }

        rightMax[n-1] = height[n-1];
        for(int i = n - 1; i >= 0; i--) {
            rightMax[i] = Math.max(height[i], rightMax[i+1]);
        }

        int maxWater = 0;

        for(int i = 0; i < n; i++) {
            maxWater += (Math.min(leftMax[i], rightMax[i]) - height[i]);
        }

        return maxWater;
    }

    public int reverse(int x) {
        boolean isNegative = false;

        if (x < 0) {
            x = -1 * x;
            isNegative = true;
        }

        int rev = 0;

        while(x != 0) {
            rev = rev * 10 + (x % 10);
            x /= 10;
        }

        return isNegative ? -1 * rev : rev;
    }

    public int search(int[] nums, int target) {
        int pivotIndex = findPivot(nums);

        if (pivotIndex != -1) {
            int index =  binarySearch(nums, 0, pivotIndex-1, target);
            if (index != -1) {
                return index;
            }

            return binarySearch(nums, pivotIndex, nums.length-1, target);
        }

        return -1;
    }

    private int binarySearch(int[] nums, int left, int right, int target) {
        if (right > left) {
            return -1;
        }

        int mid = (left + right) / 2;

        if (nums[mid] == target) {
            return mid;
        }

        int index = binarySearch(nums, left, mid -1, target);
        if (index != -1) {
            return index;
        }

        return binarySearch(nums, mid + 1, right, target);
    }

    private int findPivot(int[] num) {
        int left = 0;
        int right = num.length - 1;

        if (num[left] <= num[right]) {
            return left;
        }

        while (left < right) {
            int mid = (left + right) / 2;

            if (mid == 0 || num[mid-1] > num[mid]) {
                return mid;
            } else if (num[0] > num[mid]) {
                right--;
            } else {
                left++;
            }
        }

        return -1;
    }

    public String largestNumber(int[] nums) {
        String number =  Arrays.stream(nums)
                .mapToObj(String::valueOf)
                .sorted((a, b) -> (b+a).compareTo(a+b))
                .collect(Collectors.joining());

        return number.replaceAll("^0+$", "0");

    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for(String str: strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String key = new String(charArray);

            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(str);
        }

        return new ArrayList<>(map.values());
    }

    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length())
            return false;

        int[] s1Count = new int[26];
        int[] s2Count = new int[26];

        for(int i = 0; i < s1.length(); i++) {
            s1Count[s1.charAt(i) -'a']++;
            s2Count[s2.charAt(i) -'a']++;
        }

        int left = 0;
        for (int right = s1.length(); right < s2.length(); right++) {
            if (isMatch(s1Count, s2Count)) {
                return true;
            }

            s2Count[s2.charAt(left++) - 'a']--;
            s2Count[s2.charAt(right)-'a']++;
        }

        return isMatch(s1Count, s2Count);
    }

    private boolean isMatch(int[] s1Count, int[] s2Count) {
        for (int i = 0; i < 26; i++) {
            if (s1Count[i] != s2Count[i]) {
                return false;
            }
        }

        return true;
    }


    public int rob(int[] nums) {
        int incl = 0;
        int excl = 0;

        for(int i = 0; i < nums.length; i++) {
            int prev_incl = incl;
            int prev_excl = excl;

            excl = Math.max(prev_incl, prev_excl);
            incl = Math.max(prev_excl + nums[i], prev_incl);
        }

        return Math.max(incl, excl);
    }

    public int numIslands(char[][] grid) {
        int m = grid.length;

        if (m == 0)
            return 0;

        int n = grid[0].length;

        boolean[][] visited = new boolean[m][n];

        int count = 0;

        for(int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    dfs(grid, visited, i, j);
                    count++;
                }
            }
        }

        return count;
    }

    private void dfs(char[][] grid, boolean[][] visited, int x, int y) {
        if (x < 0 || x >= grid.length) {
            return;
        }

        if (y < 0 || y >= grid[0].length) {
            return;
        }

        if (grid[x][y] == '0') {
            return;
        }

        if (visited[x][y]) {
            return;
        }

        visited[x][y] = true;

        dfs(grid, visited, x + 1, y);
        dfs(grid, visited, x - 1, y);
        dfs(grid, visited, x, y + 1);
        dfs(grid, visited, x, y - 1);
    }

    public static String decodeString(String s) {
        Stack<Integer> stackNum = new Stack<>();
        Stack<String>  stackStr = new Stack<>();

        int num = 0;
        StringBuilder curr = new StringBuilder();

        for(char ch :  s.toCharArray()) {
            if(Character.isDigit(ch)) {
                // case when digit came after the letter
                if (curr.length() != 0) {
                    stackStr.push(curr.toString());
                    curr = new StringBuilder();
                }

                num = num * 10 + (ch - '0');


            } else if (Character.isAlphabetic(ch)) {
                curr.append(ch);
            } else if (ch == '[') {
                stackNum.push(num);
                num = 0;
            } else if (ch == ']') {
                int n = stackNum.pop();
                StringBuilder b = new StringBuilder();

                while (n-- > 0) {
                    b.append(curr.toString());
                }

                if (!stackStr.isEmpty()) {
                    b.insert(0, stackStr.pop());
                }

                curr = b;
            }
        }

        return curr.toString();
    }



    public ListNode mergeKLists(ListNode[] lists) {
        return new ListNode();
    }

    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;

        reverseArray(nums, 0, n-k-1);
        reverseArray(nums, n-k, n-1);
        reverseArray(nums, 0, n-1);
    }

    private void reverseArray(int[] nums, int left, int right) {
        while(left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;

            left++;
            right--;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);

        }

        if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        }

        return root;
    }

    public String longestPalindrome(String s) {
        int n = s.length();

        boolean[][] dp = new boolean[n][n];
        int len = 0;
        String result = "";

        for(int gap = 0; gap < n; gap++) {
            for(int i = 0, j = gap; j < n; i++, j++) {
                if( gap == 0) {
                    dp[i][j] = true;
                } else if (gap == 1){
                    if (s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = false;
                    }
                } else {
                    if (s.charAt(i) == s.charAt(j) && dp[i][j]) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = false;
                    }
                }

                if (dp[i][j]) {
                    len = gap + 1;
                    result = s.substring(i, j);
                }
            }
        }

        return result;
    }

    public String longestPalindrome1(String s) {
        int n = s.length();
        int maxLength = 0;
        int start = -1;
        for(int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (isPalindrome1(s, i, j)) {
                    if(j-i+1 > maxLength) {
                        maxLength = j - i + 1;
                        start = i;
                    }
                }
            }
        }

        return s.substring(start, start + maxLength);
    }

    private boolean isPalindrome1(String s, int start, int end) {
        while(start < end) {
            if (s.charAt(start++) != s.charAt(end--)) {
                return false;
            }
        }

        return true;
    }

    public int largestRectangleArea(int[] heights) {
        int n = heights.length;

        // store the index of next smallest element on the left
        int[] lb = new int[n];
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        lb[0] = -1;

        for(int i = 1; i < n; i++) {
            while(!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                lb[i] = -1;
            } else {
                lb[i] = stack.peek();
            }

            stack.push(i);
        }


        // store the index of next smallest element on the right
        int[] rb = new int[n];
        stack = new Stack<>();
        stack.push(n-1);
        rb[n-1] = n;

        for(int i = n-2; i >= 0; i--) {
            while(!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                rb[i] = heights.length;
            } else {
                rb[i] = stack.peek();
            }

            stack.push(i);
        }

        int maxArea = 0;

        for(int i = 0; i < n; i++) {
            int width = rb[i] - lb[i] - 1;
            int area = heights[i] * width;
            maxArea = Math.max(area, maxArea);
        }

        return maxArea;
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        int left = 0;
        int up = 0;

        int right = matrix[0].length - 1;
        int down = matrix.length - 1;

        List<Integer> result = new ArrayList<>();

        while(up <= down && left <= right) {

            // Top row
            for(int j = left; j <= right; j++) {
                result.add(matrix[up][j]);
            }
            up++;

            // Last Column
            for(int i = up; i <= down; i++) {
                result.add(matrix[i][right]);
            }
            right--;

            if (up <= right) {
                // Bottom row
                for(int j = right; j >= left; j--) {
                    result.add(matrix[down][j]);
                }
                down--;
            }

            if (left <= right) {
                // first column
                for(int i = down; i >= up; i--) {
                    result.add(matrix[i][left]);
                }
                left++;
            }
        }

        return result;
    }

    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int right = n-1;

        int currIndex = -1;
        for(int i = n-1; i > 0; i--) {
            if (nums[i] > nums[i-1]) {
                currIndex = i-1;
                break;
            }
        }

        if (currIndex != -1) {
            int minIndex = currIndex + 1;

            for(int i = currIndex; i < n; i++) {
                if (nums[i] < nums[minIndex] && nums[i] > nums[currIndex]) {
                    minIndex = i;
                }
            }
            swap(nums, currIndex, minIndex);
        }

        // Only reverse will also work. But it was waiting for some test case.
        Arrays.sort(nums, currIndex+1, n);

    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private class ListBox {
        public int val;
        public int listIndex;
        public ListNode currPtr;

        public ListBox(int val, int index, ListNode head) {
            this.val = val;
            this.listIndex = index;
            this.currPtr = head;

        }
    }

    public ListNode mergeKLists1(ListNode[] lists) {
        PriorityQueue<ListBox> pq = new PriorityQueue<>(Comparator.comparingInt(box -> box.val));

        int K = lists.length;
        ListNode result = null;
        ListNode p = null;

        for(int i = 0; i < K; i++) {
            if (lists[i] != null) {
                pq.offer(new ListBox(lists[i].val, i, lists[i]));
            }
        }

        while(!pq.isEmpty()) {
            ListBox box = pq.poll();
            box.currPtr = box.currPtr.next;

            ListNode temp = new ListNode(box.val);
            if (box.currPtr != null) {
                pq.add(new ListBox(box.currPtr.val, box.listIndex, box.currPtr));
            }

            if (result == null) {
                result = temp;
                p = temp;
            } else {
                p.next = temp;
                p = temp;
            }
        }

        return result;
    }

    public int peakIndexInMountainArray(int[] A) {
        int left = 0;
        int right = A.length - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if ((mid != 0 && A[mid] > A[mid-1]) && (mid != A.length && A[mid] > A[mid+1])) {
                return mid;
            } else if (A[mid] < A[mid+1]){
                left = mid + 1;
            } else {
                right = mid - 1;
            }

        }

        return -1;
    }


    public double myPow(double x, int n) {
        Map<Integer, Double> map = new HashMap<>();

        return myPowInternal(x, n, map);
    }

    public double myPowInternal(double x, int n, Map<Integer, Double> map) {
        if (n == 0) {
            return 1;
        }

        if (n == 1) {
            return x;
        }

        if (map.containsKey(n)) {
            return map.get(n);
        }

        if (n < 0) {
            return myPow(1/x, -n);
        }

        double val =  myPowInternal(x, n / 2, map) * myPowInternal(x, n / 2, map) * ( (n %2 == 0) ? 1 : x);
        map.put(n, val);

        return val;
    }

    public int totalFruit(int[] fruits) {
        int i = 0;
        int j = 0;

        Map<Integer, List<Integer>> map = new HashMap<>();

        int len = 0;

        while(j < fruits.length) {
            map.putIfAbsent(fruits[i], new ArrayList<>());
            map.get(fruits[i]).add(j);

            while(map.size() > 2) {
                if (map.get(fruits[i]) != null) {
                    if (!map.get(fruits[i]).isEmpty()) {
                        map.get(fruits[i]).remove(new Integer(i));

                        if (map.get(fruits[i]).isEmpty()) {
                            map.remove(fruits[i]);
                        }
                    }
                }

                i++;
            }

            len = Math.max(len, j - i+1);

            j++;
        }

        return len;
    }

    private class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int orangesRotting(int[][] grid) {
        Queue<Point> queue = new LinkedList<>();


        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new Point(i, j));
                }
            }
        }

        queue.add(null);

        int count = 0;
        while(!queue.isEmpty()) {
            Point point = queue.poll();

            if (point == null) {


                if (queue.isEmpty()) {
                    break;
                }

                count++;

                queue.add(null);
            } else {
                if (isValidNeighbour(grid, point.x-1, point.y)) {
                    grid[point.x-1][point.y] = 2;
                    queue.add(new Point(point.x-1, point.y));
                }

                if (isValidNeighbour(grid, point.x+1, point.y)) {
                    grid[point.x+1][point.y] = 2;
                    queue.add(new Point(point.x+1, point.y));
                }

                if (isValidNeighbour(grid, point.x, point.y-1)) {
                    grid[point.x][point.y-1] = 2;
                    queue.add(new Point(point.x, point.y-1));
                }

                if (isValidNeighbour(grid, point.x, point.y+1)) {
                    grid[point.x][point.y+1] = 2;
                    queue.add(new Point(point.x, point.y+1));
                }
            }
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    return -1;
                }
            }
        }

        return count;
    }

    private boolean isValidNeighbour(int[][] grid, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
            return false;
        }

        return grid[x][y] == 1;
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode nextNode  = head.next;

        if (nextNode == null) {
           return head;
        }

        head.next = swapPairs(nextNode.next);
        nextNode.next = head;

        return nextNode;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        // Check if size of node equal to K
        if (isLengthLessThanK(head, k)) {
            return head;
        }

        ListNode prev = null;
        ListNode curr = head;

        int i = 0;
        while(curr != null && i++ < k) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        head.next = reverseKGroup(curr, k);
        return prev;
    }

    private boolean isLengthLessThanK(ListNode head, int k) {
        ListNode p = head;
        int length = 0;

        while(p != null) {
            length++;
            p = p.next;

            if (length >= k) {
                return false;
            }
        }

        return true;
    }

}
