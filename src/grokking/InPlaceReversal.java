package grokking;

import linkedlist.ListNode;
import tree.TreeNode;

import java.util.*;

public class InPlaceReversal {

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode curr = head;
        ListNode prev = null;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        int i = 1;

        ListNode prev = null;
        // Reach to the left position
        ListNode curr = head;
        while(i < left && curr != null) {
            prev = curr;
            curr = curr.next;
            i++;
        }

        ListNode lastOfFirst = prev;
        ListNode lastOfSecond = curr;


        // Reverse till the right position
        while(i < right && curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            i++;
        }

        ListNode startOfSecond = prev;

        // Join first and second segment
        if (lastOfFirst != null) {
            lastOfFirst.next = startOfSecond;
        } else {
            head = startOfSecond;
        }

        if (lastOfSecond != null) {
            lastOfSecond.next = curr;
        }

        return head;
    }


}
