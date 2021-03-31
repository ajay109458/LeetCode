package array;

import linkedlist.ListNode;
import tree.TreeNode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecursionHelper {

    public ListNode plusOne(ListNode head) {
        int carry = plusOneSub(head);

        if (carry != 0) {
            ListNode node = new ListNode(carry);
            node.next = head;
            head.next = node;
        }

        return head;
    }

    private int plusOneSub(ListNode head) {
        if (head == null) {
            return 0;
        }

        if (head.next == null) {
            int sum = head.val + 1;
            int carry = sum/10;
            sum %= 10;
            head.val = sum;
            return carry;
        }

        int sum = head.val + plusOneSub(head.next);
        head.val = sum% 10;
        return sum/10;
    }

    public int tribonacci(int n) {
        if (n == 0 || n == 1) {
            return n;
        }

        if (n == 2) {
            return 1;
        }

        return tribonacci(n-1) + tribonacci(n-2) + tribonacci(n-3);
    }


    /**
     *
     * 4
     *
     * 2   9
     *
     * 3 5    7
     *
     *
     * 6
     * 2   7
     * 0 0 0
     * @param root
     * @return
     */

    public int findTilt(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftSum = findTilt(root.left);
        int rightSum = findTilt(root.right);

        int currVal = root.val;

        root.val = Math.abs(leftSum-rightSum);

        return currVal + leftSum + rightSum;
    }

    public int[] numsSameConsecDiff(int n, int k) {
        List<Integer> results = new ArrayList<>();
        populateNumberList(n, k, 0, results);

        int[] arr = new int[results.size()];
        for(int i = 0; i < results.size(); i++) {
            arr[i] = results.get(i);
        }

        return arr;
    }

    private void populateNumberList(int n, int k, int curr, List<Integer> result) {
        if (n <= 0) {
            result.add(curr);
        }

        curr *= 10;
        int startDigit = 0;
        if (curr == 0) {
            startDigit = 1;
        }

        while(startDigit <= 9) {
            if (startDigit+k < 10 || startDigit -k > 0) {
                populateNumberList(n-1, k, curr + startDigit, result);
            }
        }
    }
}
