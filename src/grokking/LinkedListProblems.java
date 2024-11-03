package grokking;

import linkedlist.ListNode;

import java.util.HashSet;
import java.util.Set;

public class LinkedListProblems {

    public static boolean containsCycle(ListNode head) {
        if (head == null || head.next == null) return false;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) return true;
        }

        return false;
    }

    public static ListNode findMiddle(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public static ListNode startOfCyclicList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) break;
        }

        slow = head;

        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }

    public static boolean isHappyNumber(int num) {
        return isHappyNumber(num, new HashSet<Long>());
    }

    private static boolean isHappyNumber(int num, Set<Long> values)
    {
        if (num <= 0) return false;
        if (num == 1) return true;

        if (values.contains(num)) return false;

        long sumOfSquares = 0;

        while(num != 0) {
            int unitDigit = num % 10;
            sumOfSquares += unitDigit * unitDigit;
            num /= 10;
        }

        values.add(sumOfSquares);
        return isHappyNumber(num, values);
    }

    public static ListNode reverseList(ListNode head) {
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

    public static ListNode copyList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode curr = head;

        ListNode head1 = null;
        ListNode r = null;

        while (curr != null) {
            ListNode temp = new ListNode(curr.val);

            if (head1 == null) {
                head1 = temp;
                r = temp;
            } else {
                r.next = temp;
                r = r.next;
            }

            curr = curr.next;
        }

        return head1;
    }

    public static boolean areEqual(ListNode head1, ListNode head2) {
        if (head1 == null && head2 == null) return true;
        if (head1 == null || head2 == null) return false;

        return head1.val == head2.val && areEqual(head1.next, head2.next);
    }

    public boolean isPalindrome(ListNode head) {
         if (head == null || head.next == null) return true;

         ListNode head1 = copyList(head);
         head1 = reverseList(head1);
         return areEqual(head1, head);
    }

    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;

        // find the middle of the list and track prev pointer.
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prev.next = null;

        ListNode p = head;
        ListNode q = reverseList(slow.next);

        while (p != null && q != null) {
            ListNode pNext = p.next;
            ListNode qNext = q.next;

            p.next = q;

            if (pNext != null) {
                q.next = pNext;
            }

            q = qNext;
            p = pNext;
        }
    }

    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }

}
