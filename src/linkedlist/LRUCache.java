package linkedlist;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    private int capacity;

    private int count;

    private ListNode head, end;

    private Map<Integer, ListNode> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
    }

    public int get(int key) {
        ListNode node = map.get(key);

        if (node != null) {
            if (node.prev != null) {
                node.prev.next = node.next;
            }

            if (node.next != null) {
                //if not last node
                node.next.prev = node.prev;
            } else {
                end = (node.prev == null) ? node : node.prev;
            }

            node.prev = null;

            node.next = head;
            head.prev = node;

            head = node;
        } else {
            System.out.println("#" + -1);
            return -1;
        }

        System.out.println("#" +node.val);
        return node.val;
    }

    public void put(int key, int value) {
        ListNode node = map.get(key);



            ListNode temp = new ListNode(value);
            map.put(key, temp);

            if (count+1 > capacity) {
                // set the end
                map.put(end.val, null);
                if (end.prev == null) {
                    head = temp;
                    end = temp;
                } else {
                    ListNode secondLast = end.prev;
                    secondLast.next = null;
                    end.prev = null;

                    end = secondLast;
                }


            } else{
                count++;
            }

            if (head == null) {
                head = temp;
                end = temp;
            } else {
                temp.next = head;
                head.prev = temp;
                head = temp;
            }


        System.out.println("***************");
        LinkedListHelper.print(head);
    }

}
