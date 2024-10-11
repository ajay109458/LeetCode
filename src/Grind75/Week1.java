package Grind75;

import linkedlist.ListNode;
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
}
