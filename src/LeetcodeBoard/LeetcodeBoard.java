package LeetcodeBoard;

import utils.ListNode;

import java.io.CharArrayReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

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




}
