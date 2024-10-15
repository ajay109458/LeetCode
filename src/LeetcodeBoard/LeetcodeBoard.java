package LeetcodeBoard;

import utils.ListNode;
import java.util.*;

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


}
