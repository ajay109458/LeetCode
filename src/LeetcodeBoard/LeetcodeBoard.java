package LeetcodeBoard;

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


}
