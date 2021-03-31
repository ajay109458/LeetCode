import linkedlist.LRUCache;
import linkedlist.LinkedListHelper;
import linkedlist.ListNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sun.awt.image.ImageWatched;
import tree.TreeNode;
import tree.TreeProblems;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinkedListProblemTests {

	@BeforeEach
	void init() {
		System.out.println("------------------------------");
	}

	@Test
	public void checkReverseInGroup() {
		ListNode head = createLinkList();
		ListNode rev = LinkedListHelper.reverseKGroup(head, 3);
		LinkedListHelper.print(rev);
	}

	@Test
	public void swapInPair() {
		ListNode head = createLinkList();
		ListNode rev = LinkedListHelper.swapPairs(head);
		LinkedListHelper.print(rev);
	}

	@Test
	public void deleteNNodesAfterMNodesCheck() {
		ListNode head = createLinkList();
		ListNode rev = LinkedListHelper.deleteNodes(head, 1, 2);
		LinkedListHelper.print(rev);
	}

	@Test
	public void rotateList() {
		ListNode head = createLinkList();
		ListNode rev = LinkedListHelper.rotateRight(head, 1);
		LinkedListHelper.print(rev);
	}

	@Test
	public void deleteDuplicate() {
		ListNode head = createLinkList();
		ListNode rev = LinkedListHelper.deleteDuplicatesFromSortedList(head);
		LinkedListHelper.print(rev);
	}

	@Test
	public void reverseListInBewtween() {
		ListNode head = createLinkList();
		ListNode rev = LinkedListHelper.reverseBetween(head, 2, 4);
		LinkedListHelper.print(rev);
	}

	@Test
	public void addNumberTest() {
		ListNode head1 = createLinkList();
		ListNode head2 = createLinkList();

		ListNode result = LinkedListHelper.addNumber(head1, head2);

		LinkedListHelper.print(result);
	}


	@Test
	public void checkLRUCache() {
		LRUCache lRUCache = new LRUCache(2);
		lRUCache.put(1, 1); // cache is {1=1}
		lRUCache.put(2, 2); // cache is {1=1, 2=2}
		lRUCache.get(1);    // return 1
		lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
		lRUCache.get(2);    // returns -1 (not found)
		lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
		lRUCache.get(1);    // return -1 (not found)
		lRUCache.get(3);    // return 3
		lRUCache.get(4);    // return 4
	}

	private ListNode createLinkList() {
		ListNode p1 = new ListNode(1);
		p1.next = new ListNode(2);
		p1.next.next = new ListNode(2);
		p1.next.next.next = new ListNode(4);

		return p1;
	}

}
