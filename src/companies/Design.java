package companies;

import linkedlist.ListNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Design {

    static class MinStack {

        Stack<Integer> elementStack;
        Stack<Integer> minStack;

        /** initialize your data structure here. */
        public MinStack() {
            elementStack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int val) {
            int minVal =  (minStack.isEmpty()) ? val : Math.min(val, minStack.peek());
            minStack.push(minVal);
            elementStack.push(val);
        }

        public void pop() {
            minStack.pop();
            elementStack.pop();
        }

        public int top() {
            return elementStack.peek();
        }

        public int getMin() {
            return elementStack.peek();
        }
    }

    /**
     * LIFO
     * FIFO
     *
     * Least recently used item should be removed from the cache
     *
     * Cache:
     * Store data to speed up the already computed expensive operation
     *
     * LRU
     * - Least recently used
     *
     * LRU
     * - A cache using the LRU eviction policy
     */
    public static class LRUEvictionPolicy {

        private int currCapacity;
        private ListNode head;
        private ListNode tail;
        private Map<Integer, ListNode> hashMap;
        private int capacity;

        public LRUEvictionPolicy(int capacity) {
            this.currCapacity = 0;
            this.head = null;
            this.tail = null;
            this.hashMap = new HashMap<>();

            head = new ListNode(-1);
            tail = new ListNode(-1);

            head.next = tail;
            tail.prev = head;

            this.capacity = capacity;
        }

        /**
         * Expected Time Complexity O(1)
         *
         * @param key
         * @param value
         */
        public void put(int key, int value) {

            ListNode node = hashMap.get(key);

            if (node == null) {

                ListNode newNode = new ListNode(value);
                hashMap.put(key, newNode);

                currCapacity++;

                if (currCapacity > this.capacity) {
                    currCapacity--;
                    removeLastNode();
                }

            } else  {
                node.val = value;
                moveToHead(node);
            }
        }

        /**
         * Expected Time complexity - O(1)
         *
         * @param key
         * @return
         */
        public int get(int key) {
            ListNode node = hashMap.get(key);

            if (node == null) {
                return -1;
            }

            moveToHead(node);

            return node.val;
        }

        private void moveToHead(ListNode node) {
            removeNode(node);
            addNode(node);
        }

        private void addNode(ListNode node) {
            node.prev = head;
            node.next = head.next;

            head.next = node;
            node.next.prev = node;
        }

        private void removeNode(ListNode node) {
            ListNode nextNode = node.next;
            ListNode prevNode = node.prev;

            nextNode.prev = prevNode;
            prevNode.next = nextNode;
        }

        private ListNode removeLastNode() {
             ListNode lastNode  = tail.prev;
             removeNode(lastNode);
             return lastNode;
        }

    }

}
