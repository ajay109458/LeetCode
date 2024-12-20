package Grind75;

import linkedlist.ListNode;
import tree.TreeNode;

import java.util.Arrays;
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


    private static class ListNodeWrapper {
        public ListNode head;
        public ListNode current;

        public ListNodeWrapper(ListNode root, ListNode current) {
            this.head = root;
            this.current = current;
        }
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        if (list1 == null)
            return list2;

        if (list2 == null) {
            return list1;
        }

        ListNode p = list1;
        ListNode q = list2;

        ListNode rHead = null;
        ListNode r = null;

        ListNode nodeToInsert;
        ListNodeWrapper wrapper;

        while(p != null && q != null) {
            if (p.val <= q.val) {
                nodeToInsert = p;
                p = p.next;
                nodeToInsert.next = null;
            } else {
                nodeToInsert = q;
                q = q.next;
                nodeToInsert.next = null;
            }

            wrapper = insertNodeToList(rHead, r, nodeToInsert);
            r = wrapper.current;
            rHead = wrapper.head;
        }

        if (p != null) {
            wrapper = insertNodeToList(rHead, r, p);
            rHead = wrapper.head;
        }

        if (q != null) {
            wrapper = insertNodeToList(rHead, r, q);
            rHead = wrapper.head;
        }

        return rHead;
    }

    private ListNodeWrapper insertNodeToList(ListNode head, ListNode current, ListNode nodePointer) {
        if (head == null) {
            head = nodePointer;
            current = head;
        } else {
            current.next = nodePointer;
            current = current.next;
        }

        return new ListNodeWrapper(head, current);
    }


    public static int maxProfit(int[] prices) {
        int maxProfit = 0;

        int minPrice = Integer.MAX_VALUE;

        for(int price : prices) {
            minPrice = Math.min(minPrice, price);
            maxProfit = Math.max(maxProfit, price - minPrice);
        }

        return maxProfit;
    }

    public boolean isPalindrome(String s) {
        s = s.toLowerCase();

        int left = 0;
        int right = s.length() - 1;

        while(left < right) {
            while(left < s.length() && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }

            while(right >= 0 && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            if (s.charAt(left) != s.charAt(right)) {
                return  false;
            }

            left++;
            right--;
        }

        return true;
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }

        TreeNode temp = invertTree(root.left);
        root.left = invertTree(root.right);
        root.right = temp;

        return root;
    }

    public boolean isAnagram(String s, String t) {
        int[] store = new int[26];

        for (char ch : s.toCharArray()) {
            store[ch - 'a']++;
        }

        for (char ch : t.toCharArray()) {
            store[ch - 'a']--;
        }

        return Arrays.stream(store).allMatch(value -> value == 0);
    }

    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while(left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right--;
            } else {
                left++;
            }
        }

        return -1;
    }
}
