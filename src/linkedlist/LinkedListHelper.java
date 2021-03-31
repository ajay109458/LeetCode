package linkedlist;

import tree.TreeNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class LinkedListHelper {

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode curr = head;
        ListNode prev = null;
        ListNode next = null;

        int count = 0;

        int length = len(head);

        if (length < k) {
            return head;
        }

        while (curr != null && count < k) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;

            count++;
        }

        if (curr != null) {
            head.next = reverseKGroup(curr, k);
        }

        return prev;
    }

    public static ListNode swapPairs(ListNode head) {
        return reverseKGroup(head, 2);
    }

    private static int len(ListNode head) {
        if (head == null) {
            return 0;
        }

        return 1 + len(head.next);
    }

    public static ListNode deleteNodes(ListNode head, int m, int n) {

        if (head == null) {
            return null;
        }

        ListNode pPrev = head;
        ListNode qPrev = null;

        while(true) {

            int i = 0;

            while(i < m-1 && pPrev != null ) {
                i++;
                pPrev = pPrev.next;
            }

            if (pPrev == null) {
                break;
            }

            qPrev = pPrev.next;

            int j = 0;
            while(j < n-1 && qPrev != null) {
                j++;
                qPrev = qPrev.next;
            }

            if (qPrev == null) {
                pPrev.next = null;
                break;
            }

            pPrev.next = qPrev.next;
            pPrev = pPrev.next;

        }

        return head;

    }

    public static void print(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }

        System.out.println();
    }

    public int getDecimalValue(ListNode head) {
        int index = len(head) - 1;

        int val = 0;
        while(head != null) {

            if (head.val == 1) {
                val += (Math.pow(2, index));
            }

            index--;
            head = head.next;
        }

        return val;
    }

    public static ListNode rotateRight(ListNode head, int k) {
        int len = len(head);

        int diff = len - k%len;

        if (diff == 0) {
            return head;
        }

        ListNode prev = head;

        int i = 0;
        while (prev != null && i < diff - 1) {
            prev = prev.next;
            i++;
        }

        ListNode newHead = prev.next;
        prev.next = null;

        prev = newHead;
        while(prev.next != null) {
            prev = prev.next;
        }

        prev.next = head;

        return newHead;
    }

    public static ListNode deleteDuplicatesFromSortedList(ListNode head) {
        ListNode p = head;

        while (p != null) {
            ListNode q = (p == head) ? p : p.next;

            boolean isDup = false;
            while (q != null && q.next != null && q.val == q.next.val) {
                isDup = true;
                q = q.next;
            }

            if (isDup) {

                if (q == null || q.next == null) {
                    p.next = null;
                } else {
                    q = q.next;
                    p.next = q;
                }

            } else {
                p =  p.next;
            }
        }

        return head;
    }

    public ListNode partition(ListNode head, int x) {
        ListNode smaller = null;
        ListNode larger = null;

        ListNode p = head;
        ListNode pSmall = null;
        ListNode pLarge = null;

        while(p != null) {

            if (p.val < x) {
                if (smaller == null) {
                    smaller = p;
                    pSmall = smaller;
                } else {
                    pSmall.next = p;
                    pSmall = p;
                }

                p = p.next;
                pSmall.next = null;
            } else {
                if (larger == null) {
                    larger = p;
                    pLarge = larger;
                } else {
                    pLarge.next = p;
                    pLarge = p;
                }

                p = p.next;
                pLarge.next = null;
            }
        }

        if (smaller == null) {
            return larger;
        } else {
            pSmall.next = larger;
            return smaller;
        }
    }

    public static ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode startBefore = null;
        ListNode start = head;
        ListNode end = head;
        ListNode nextAfter = null;

        int index = 1;
        while(index < m - 1 && start != null) {
            startBefore = start;
            start = start.next;
            index++;
        }

        index = 1;
        while(index < n -1 && end != null) {
            end = end.next;
            index++;
        }

        if (end != null) {
            nextAfter = end.next;
            end.next = null;
        }

        ListNode revRoot = reverse(start);

        if(startBefore!= null) {
            startBefore.next = revRoot;
        }

        while (revRoot != null && revRoot.next != null) {
            revRoot = revRoot.next;
        }

        if(revRoot != null) {
            revRoot.next = nextAfter;
        }

        return head;
    }


    public ListNode deleteDuplicates(ListNode head) {
        ListNode p = head;
        ListNode prev = null;

        while(p != null && p.next != null) {

            ListNode dupS = null;
            ListNode dupE = null;

            if (p.val == p.next.val) {
                dupS = p;
                dupE = p.next;
            }

            while(dupE != null && dupE.next != null) {
                if (dupE.val != dupE.next.val)
                    break;
                dupE = dupE.next;
            }


            if (dupS != null) {
                p = dupE.next;
                dupE.next = null;
                if (prev != null) {
                    prev.next = p;
                } else {
                    head = p;
                }
            } else {
                prev = p;
                p = p.next;
            }
        }

        return head;
    }

    public void reorderList(ListNode head) {

        if (head == null) {
            return;
        }

        ListNode middle = findMiddleElement(head);
        middle = reverse(middle);

        ListNode p = head;
        ListNode prev = null;

        while(p != null) {
            ListNode q = p.next;

            if (middle != null) {
                ListNode temp = middle;
                middle = middle.next;
                temp.next = null;

                p.next = temp;
                temp.next = q;
                prev = temp;
            }


            p = q;
        }

        if (prev != null && middle != null) {
            prev.next = middle;
        }



        return;
    }

    public ListNode insertionSortList(ListNode head) {
        ListNode result = null;

        while(head != null) {
            ListNode temp = head;
            head = head.next;
            temp.next = null;

            result =  insertNode(result, temp);
        }

        return result;
    }

    private ListNode insertNode(ListNode head, ListNode node) {
        if (head == null) {
            return node;
        }

        if (node.val < head.val) {
            node.next = head;
            head = node;
            return head;
        }

        ListNode p = head;

        while(p != null && p.next != null) {
            if (node.val >= p.val && node.val < p.next.val) {
                ListNode q = p.next;
                p.next = node;
                node.next = q;

                break;
            }

            p = p.next;
        }

        if (p.next == null && node.val >= p.val) {
            p.next = node;
        }

        return head;
    }


    public static ListNode addNumber(ListNode head1, ListNode head2) {
        ListNode p = reverse(head1);
        ListNode q = reverse(head2);



        ListNode result = null;
        ListNode rHead = null;

        int carry = 0;

        while ( p != null && q != null) {
            int sum = p.val + q.val + carry;
            carry = sum / 10;
            int digit = sum %10;

            ListNode temp = new ListNode(digit);

            if (rHead == null) {
                result = temp;
                rHead = temp;
            } else {
                result.next = temp;
                result = result.next;
            }

            p = p.next;
            q = q.next;
        }

        while  (p != null) {
            int sum = p.val + carry;
            carry = sum / 10;
            int digit = sum %10;
            p = p.next;

            ListNode temp = new ListNode(digit);

            if (rHead == null) {
                result = temp;
                rHead = temp;
            } else {
                result.next = temp;
                result = result.next;
            }
        }

        while (q != null) {
            int sum = q.val + carry;
            carry = sum / 10;
            int digit = sum %10;
            q = q.next;

            ListNode temp = new ListNode(digit);

            if (rHead == null) {
                result = temp;
                rHead = temp;
            } else {
                result.next = temp;
                result = result.next;
            }
        }

        if (carry != 0) {
            ListNode temp = new ListNode(carry);
            result.next = temp;
        }

        return reverse(rHead);
    }

    public static ListNode reverse(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode curr = head;
        ListNode prev = null;
        ListNode next = null;

        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }

    public static ListNode getNodeOfIntersect(ListNode head) {

        if (head == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = null;

        while (fast != null && fast.next !=null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                slow = head;
                break;
            }
        }

        if (fast == null || fast.next == null)
            return null;

        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }

    public ListNode copyRandomList(ListNode head) {
        ListNode newHead = null;
        ListNode p = head;
        ListNode q = null;

        Map<ListNode, ListNode> map = new HashMap<>();

        while (p != null) {
            if (newHead == null) {
                newHead = p;
                q = p;
            } else {
                q.next = p;
                q = q.next;
            }

            map.put(p, q);
        }

        p = head;
        while(p != null) {
            if (p.random != null)
                map.get(p).random = map.get(p.random);
        }

        return newHead;
    }

    public ListNode findMiddleElement(ListNode head) {
        ListNode slowPtr = head;
        ListNode fastPtr = head;
        ListNode prevPtr = null;

        while(fastPtr != null && fastPtr.next != null) {
            prevPtr = slowPtr;
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;
        }

        if (prevPtr != null) {
            prevPtr.next = null;
        }

        return slowPtr;
    }

    public TreeNode sortedListToBST(ListNode head) {

        if (head == null) {
            return null;
        }

        ListNode middle = findMiddleElement(head);

        TreeNode node = new TreeNode(middle.val);

        if (head == middle) {
            return node;
        }

        node.left = sortedListToBST(head);
        node.right = sortedListToBST(middle.next);

        return node;

    }

    public ListNode oddEvenList(ListNode head) {

        if (head == null || head.next == null)
            return head;

        ListNode oddHead = null;
        ListNode evenHead = null;

        ListNode even = null;
        ListNode odd = null;

        ListNode p = head;

        int count = 0;

        while( p != null) {
            ListNode temp = p;
            p = temp.next;

            temp.next = null;
            if (count % 2 == 0) {
                if (evenHead == null) {
                    evenHead = temp;
                    even = temp;
                } else {
                    even.next = temp;
                    even = even.next;
                }
            } else {
                if (oddHead == null) {
                    oddHead = temp;
                    odd = temp;
                } else {
                    odd.next = temp;
                    odd = even.next;
                }
            }

            count++;
        }

        if (oddHead == null) {
            return evenHead;
        }

        p = oddHead;

        while(p != null && p.next != null) {
            p = p.next;
        }

        p.next = evenHead;

        return oddHead;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode result = null;
        ListNode r = null;

        ListNode p = l1;
        ListNode q = l2;

        while( p != null && q != null) {

            ListNode temp = null;
            if (p.val <= q.val) {
                temp = p;
                p = p.next;
            } else {
                temp = q;
                q = q.next;
            }

            temp.next = null;

            if (result == null) {
                result = temp;
                r = temp;
            } else {
                r.next = temp;
                r = r.next;
            }
        }

        if(p != null) {
            r.next = p;
        }

        if (q != null) {
            r.next = q;
        }


        return result;
    }

}

