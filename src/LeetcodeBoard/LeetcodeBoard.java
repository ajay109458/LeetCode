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

    public ListNode removeNthFromEnd(ListNode head, int n) {
        int length = sizeofList(head);
        int distanceFromStart = length - n;

        ListNode curr = head;

        int i = 1;
        while(i < distanceFromStart && curr != null) {
            curr = curr.next;
            i++;
        }

        if (distanceFromStart == 0 && curr != null) {
            return curr.next;
        }

        if (curr == null || curr.next == null) {
            return head;
        }

        ListNode temp = curr.next;
        curr.next = curr.next.next;
        temp.next = null;

        return head;
    }

    private int sizeofList(ListNode head) {
        ListNode p = head;
        int length = 0;

        while(p != null) {
            length++;
            p = p.next;

        }

        return length;
    }

    public boolean canPartition(int[] nums) {
        int totalSum = Arrays.stream(nums).sum();

        if (totalSum % 2 != 0) {
            return false;
        }

        int halfSum = totalSum / 2;
        int n = nums.length;

        boolean[][] dp = new boolean[n + 1][halfSum + 1];

        for(int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        for(int j = 1; j <= halfSum; j++) {
            dp[0][j] = false;
        }

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= halfSum; j++) {
                // Exclude and Include current element
                if (dp[i - 1][j] || (j >= nums[i-1] && dp[i-1][j - nums[i-1]])) {
                    dp[i][j] = true;
                }
            }
        }

        return dp[n][halfSum];
    }

    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        int sum = 0;
        map.put(sum, -1);

        int maxLength = 0;

        for(int i = 0; i < nums.length; i++) {
            sum += (nums[i] == 0)? -1 : 1;

            if (map.containsKey(sum)) {
                maxLength = Math.max(maxLength, i - map.get(sum));
            }

            map.putIfAbsent(sum, i);
        }

        return maxLength;
    }

    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < arr1.length; i++) {
            int num = arr1[i];

            while(num != 0) {
                set.add(num);
                num = num / 10;
            }
        }

        int maxLength = 0;

        for(int i = 0; i < arr2.length; i++) {
            int num = arr2[i];

            while(num != 0) {
                if (set.contains(num)) {
                    maxLength = Math.max(maxLength, sizeOfNum(num));
                    break;
                }

                num = num / 10;
            }
        }

        return maxLength;
    }

    private int sizeOfNum(int num) {
        int count = 0;

        while(num != 0) {
            num = num / 10;
            count++;
        }

        return count;
    }


    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));

        Stack<int[]> stack = new Stack<>();
        stack.push(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            while(!stack.isEmpty() && stack.peek()[1] > intervals[i][0]) {
                int[] temp = stack.pop();
                intervals[i][0] = Math.min(temp[0], intervals[i][0]);
                intervals[i][1] = Math.max(intervals[i][1], temp[1]);
            }

            stack.push(intervals[i]);
        }


        int[][] res = new int[stack.size()][2];

        for(int i = stack.size()-1; i >= 0; i--) {
            res[i] = stack.pop();
        }

        return res ;
    }

    public String intToRoman(int num) {



        Map<Integer, String> map = new HashMap<>();
        map.put(1, "I");
        map.put(4, "IV");
        map.put(5, "V");
        map.put(9, "IX");
        map.put(10, "X");
        map.put(40, "XL");
        map.put(50, "L");
        map.put(90, "XC");
        map.put(100, "C");
        map.put(400, "CD");
        map.put(500, "D");
        map.put(900, "CM");
        map.put(1000, "M");

        List<Integer> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys, Collections.reverseOrder());

        StringBuilder sb = new StringBuilder();

        int index = 0;

        while(num > 0) {
            int key = keys.get(index);
            if (num >= key) {
                sb.append(map.get(key));
                num -= key;
            } else {
                index++;
            }
        }

        return sb.toString();
    }

    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        boolean[][] visited = new boolean[m][n];
        int maxArea = 0;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int size = dfs(grid, visited, i, j);
                    maxArea = Math.max(maxArea, size);
                }
            }
        }

        return maxArea;
    }

    public int dfs(int[][] grid, boolean[][] visited, int x, int y) {
        if (x < 0 || x > grid.length - 1 || y < 0 || y > grid[0].length - 1) {
            return 0;
        }

        if (grid[x][y] == 0) {
            return 0;
        }

        if (visited[x][y]) {
            return 0;
        }

        visited[x][y] = true;

        return 1 + dfs(grid, visited, x, y + 1)
                + dfs(grid, visited, x, y - 1)
                + dfs(grid, visited, x + 1, y)
                + dfs(grid, visited, x - 1, y);
    }

    public int minSwaps(String s) {
        Stack<Character> stack = new Stack<>();

        int imblance = 0;

        for(char ch : s.toCharArray()) {
            if (ch == '[') {
                stack.push(ch);
            } else if (ch == ']') {
                if (stack.isEmpty()) {
                    imblance++;
                } else {
                    stack.pop();
                }
            }
        }

        return (imblance + 1)/2;
    }

    public int maxWidthRamp(int[] nums) {
        int n = nums.length;
        int [] maxOnRight = new int[n];

        maxOnRight[n - 1] = nums[n - 1];
        for(int i = n - 2; i >= 0; i--) {
            maxOnRight[i] = Math.max(maxOnRight[i+1], nums[i]);
        }

        int left = 0;
        int right = 0;

        int maxLength = 0;

        while(right < n) {
            if (nums[left] <= maxOnRight[right]) {
                maxLength = Math.max(maxLength, right - left);
                right++;
            } else {
                left++;
            }
        }

        return maxLength;
    }

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];

        int[] left = new int[n];
        int[] right = new int[n];

        left[0] = 1;
        for(int i = 1; i < n; i++) {
            left[i] = left[i - 1] * nums[i - 1];
        }

        right[n - 1] = 1;
        for(int i = n - 2; i >= 0; i--) {
            right[i] = right[i + 1] * nums[i + 1];
        }

        for(int i = 0; i < n; i++) {
            res[i] = left[i] * right[i];
        }

        return res;
    }

    class RandomNode {
        int val;
        RandomNode next;
        RandomNode random;

        public RandomNode(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }


    public RandomNode copyRandomList(RandomNode head) {
        if (head == null) {
            return null;
        }

        RandomNode p = head;
        while (p != null) {
            RandomNode temp = new RandomNode(p.val);
            temp.next = p.next;
            p.next = temp;

            p = temp.next;
        }

        p = head;
        while (p != null) {
            if (p.random != null) {
                p.next.random = p.random.next;
            }
            p = p.next.next;
        }

        RandomNode head2 = null;
        RandomNode p1 = head;
        RandomNode p2 = null;

        while(p1 != null) {
            if (head2 == null) {
                head2 = p1.next;
                p2 = p1.next;
            } else {
                p2 = p1.next;
            }

            p1 = p2.next;
        }

        return head2;
    }

    public static int minNumberOperations(int[] target) {
        return numberOfOps(target, 0, target.length - 1);
    }

    private static int numberOfOps(int[] nums, int left, int right) {
        if (left > right) {
            return 0;
        }

        int min = nums[left];
        for(int i = left + 1; i <= right; i++) {
            min = Math.min(min, nums[i]);
        }

        int count = min;

        for(int i = left; i <= right; i++) {
            nums[i] = nums[i] - min;
        }

        for(int i = left + 1; i <= right; i++) {
            if (nums[i] > min) {
                count += (nums[i] - nums[i-1]);
            }
        }

        return count;

    }

    public static void printArray(int[] arr, int left, int right) {
        for (int i = left; i <= right; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        return dp[m - 1][n - 1];
    }

    public int jump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];

        // Number of steps to reach the first element
        dp[0]  = 0;

        for (int i = 1; i < n; i++) {
            dp[i] = Integer.MAX_VALUE;
            for(int j = 0; j < i; j++) {
                if (dp[j] != Integer.MAX_VALUE && j + nums[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }

        return dp[n - 1];
    }

    public int findMinDifference(List<String> timePoints) {
        int[] minutes = new int[timePoints.size()];

        for(int i = 0; i < timePoints.size(); i++) {
            String time = timePoints.get(i);
            String[] split = time.split(":");
            minutes[i] = Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
        }

        Arrays.sort(minutes);
        int min = Integer.MAX_VALUE;

        for(int i = 1; i < minutes.length; i++) {
            min = Math.min(min, Math.abs(minutes[i] - minutes[i - 1]));
        }

        min = Math.min(min, 24*60 - minutes[minutes.length - 1] + minutes[0]);

        return min;
    }

    public int coinChange(int[] coins, int amount) {

        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for(int s = 0; s <= amount; s++) {
            for(int coin : coins) {
                if (s >= coin && dp[s-coin] != Integer.MAX_VALUE) {
                    dp[s] = Math.min(dp[s], dp[s-coin] + 1);
                }
            }
        }

        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for(int coin : coins) {
            for(int amt = 1; amt <= amount; amt++) {
                if (coin <= amt) {
                    dp[amt] += dp[amt - coin];
                }
            }
        }


        return dp[amount];
    }

    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        restoreIPAddresses(s, 0, "", res);
        return res;
    }

    public void restoreIPAddresses(String s, int dotIndex, String prefix, List<String> finalResult) {
        if (dotIndex == 4 && s.isEmpty()) {
            finalResult.add(prefix);
        }

        if (s.isEmpty()) {
            return;
        }

        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < 3; i++) {
            if (i < s.length()) {
                builder.append(s.charAt(i));
                String val = builder.toString();
                if (val.startsWith("0") && !val.equals("0")){
                    return;
                }
                if (Integer.parseInt(builder.toString()) < 256) {
                    String newPrefix = prefix + builder.toString() + ((dotIndex < 3) ? "." : "");
                    restoreIPAddresses(s.substring(i+1), dotIndex+1, newPrefix, finalResult);
                }
            }
        }
    }

    public void rotate(int[][] matrix) {
        int n = matrix.length;

        for(int i = 0; i < n/2; i++) {
            for (int j = i; j < n - i - 1; j++) {

                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = temp;
            }
        }
    }

    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();

        int[] maxOnRightIndex = new int[temperatures.length];

        for(int i = temperatures.length - 1; i >= 0; i--) {
            while(!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                stack.pop();
            }

            maxOnRightIndex[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        for(int i = 0; i < temperatures.length; i++) {
            System.out.println("Max on right for : " + temperatures[i] + " is " + ((maxOnRightIndex[i] == -1) ? 0 :temperatures[maxOnRightIndex[i]]));
            maxOnRightIndex[i] = (maxOnRightIndex[i] == -1) ? 0 : maxOnRightIndex[i] - i;
        }

        return maxOnRightIndex;
    }

    public ListNode rotateRight(ListNode head, int k) {

        if (head == null) {
            return null;
        }

        int size = sizeofList(head);
        k %= size;

        if (k == 0) {
            return head;
        }

        int i = 0;
        ListNode p = head;
        while(i < size - k - 1) {
            p = p.next;
            i++;
        }

        ListNode newHead = p.next;
        p.next = null;

        p = newHead;

        while(p.next != null) {
            p = p.next;
        }

        p.next = head;

        return newHead;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        int colIndex = n-1;
        int rowIndex = 0;

        while(rowIndex < m && colIndex >= 0) {
            if (matrix[rowIndex][colIndex] == target) {
                return true;
            } else if (matrix[rowIndex][colIndex] > target) {
                colIndex--;
            } else {
                rowIndex++;
            }
        }

        return false;
    }

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode root, int min, int max) {
        if (root == null) {
            return true;
        }

        if (root.val < min || root.val > max) {
            return false;
        }

        return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
    }

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> res = new ArrayList<>();

        root = populateRootsList(root, to_delete, res);

        if (root != null) {
            res.add(root);
        }

        return res;
    }

    public TreeNode populateRootsList(TreeNode root, int[] to_delete, List<TreeNode> res) {
        if (root == null) {
            return null;
        }

        boolean currentNodeDeleted = false;
        for(int val : to_delete) {
            if (val == root.val) {
                currentNodeDeleted = true;
                break;
            }
        }

        if (currentNodeDeleted) {
            if (root.left != null) {
                TreeNode temp = populateRootsList(root.left, to_delete, res);
                if (temp != null) {
                    res.add(temp);
                }
            }

            if (root.right != null) {
                TreeNode temp = populateRootsList(root.right, to_delete, res);
                if (temp != null) {
                    res.add(temp);
                }
            }

            return null;
        } else {
            root.left = populateRootsList(root.left, to_delete, res);
            root.right = populateRootsList(root.right, to_delete, res);

            return root;
        }
    }

    public int minAddToMakeValid(String s) {
        Stack<Character> stack = new Stack<>();
        int count = 0;
        for(char ch : s.toCharArray()) {
            if(ch == '(') {
                stack.push(ch);
            } else {
                if (stack.isEmpty()) {
                    count++;
                } else {
                    stack.pop();
                }
            }
        }

        return count;
    }

    public int kthFactor(int n, int k) {
        int count = 0;
        for(int i = 1; i <= n; i++) {
            if (n % i == 0) {
                count++;
                if (count == k) {
                    return i;
                }
            }
        }

        return -1;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();

        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node == null) {
                if (!temp.isEmpty()) {
                    res.add(temp);
                    temp = new ArrayList<>();
                }

                if (queue.isEmpty()) {
                    break;
                }

                queue.add(null);
            } else {
                System.out.println(node.val);
                temp.add(node.val);

                if (node.left != null) {
                    queue.add(node.left);
                }

                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }

        return res;
    }


    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int num: nums) {
            pq.add(num);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        return pq.peek();
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();



        return res;
    }

    public boolean solveNQueens(int row, boolean[][] matrix) {
        for(int col = 0; col < matrix.length; col++) {
            if (isValid(row, col, matrix)) {
                matrix[row][col] = true;
                boolean isPossible = solveNQueens(row + 1, matrix);
                if (isPossible) {
                    return true;
                }
                matrix[row][col] = false;
            }
        }

        return false;
    }

    public boolean isValid(int row, int col, boolean[][] matrix) {
        for (int i = 0; i < row; i++) {
            if (matrix[col][i]) {
                return false;
            }
        }

        for (int c = 0; c < col; c++) {
            if (matrix[row][c]) {
                return false;
            }
        }

        int r = row-1;
        int c = col-1;
        while(r >= 0 && c >=0) {
            if (matrix[r--][c--]) {
                return false;
            }
        }

        r = row-1;
        c = col+1;

        while (r >= 0 && c < col) {
            if (matrix[r--][c++]) {
                return false;
            }
        }

        return true;
    }

    public int combinationSum4(int[] nums, int target) {
        if (target == 0) {
            return 1;
        }

        if (nums == null || nums.length == 0) {
            return 0;
        }

        return combinationSumInternal(nums, target);

    }

    private int combinationSumInternal(int[] nums, int target) {
        if (target == 0) {
            return 1;
        }

        if (target < 0 ) {
            return 0;
        }

        int count = 0;
        for(int i = 0; i < nums.length; i++) {
            count += combinationSumInternal(nums, target - nums[i]);
        }

        return count;
    }

    private int combinationSumInternalDp(int[] nums, int target) {
        int[] dp = new int[target + 1];

        dp[0] = 1;

        for (int i = 1; i <= target; i++) {
            for(int num :  nums) {
                if (i - num >= 0) {
                    dp[i] += dp[i - num];
                }
            }
        }

        return dp[target];
    }

    public void setZeroes(int[][] matrix) {
        int[] row = new int[matrix.length];
        int[] col = new int[matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            row[i] = 1;
        }

        for (int j = 0; j < matrix[0].length; j++) {
            col[j] = 1;
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = 0;
                    col[j] = 0;
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (col[j] == 0 || row[i] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

}
