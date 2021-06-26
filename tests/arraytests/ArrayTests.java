package arraytests;

import array.ArrayHelper;
import companies.ArrayProblems;
import hash.HashHelper;
import linkedlist.LinkedListHelper;
import linkedlist.ListNode;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArrayTests {

	@Test
	public void validateMergePairs() {
		int[][] arr = {{1,3},{2,6},{8,10},{15,18}};

		ArrayHelper.mergePairs(arr);
	}


	@Test
	public void checkThreeSum() {
		int[] nums = new int[]{-1,0,1,2,-1,-4};
		ArrayHelper.threeSum(nums);
	}

	@Test
	public void checkTrap() {
		int[] nums = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};

		int water = ArrayHelper.trap(nums);

		System.out.println(water);
	}

	@Test
	public void checkCombinationSum() {
		int[] candidates = {2, 3, 6, 7};
		List<List<Integer>> result = ArrayHelper.combinationSum(candidates, 7);

		System.out.println(result);
	}

	@Test
	public void checkForTripplet() {
		int[] arr = {7,3,7,3,12,1,12,2,3};
		ArrayHelper.countGoodTriplets(arr, 5, 8, 1);
	}

	@Test
	public void checkMinOperations() {
		int[] arr = {3,2,20,1,1,3};
		int count = ArrayHelper.minOperations(arr, 10);
		System.out.println(count);
	}

	@Test
	public  void waysToMakeFairTest() {
		int[] arr = {1, 1, 1};
		int count = ArrayHelper.waysToMakeFair1(arr);
		System.out.println(count);
	}

	@Test
	public void testOpsCount() {
		int[] arr = {37,2,9,49,58,57,48,17};
		int count = ArrayHelper.minMoves(arr, 58);
		System.out.println(count);
	}

	@Test
	public void checkBoxUnits() {
		int[][] arr = {{5,10},{2,5},{4,7},{3,9}};
		int max = HashHelper.maximumUnits(arr, 10);
		System.out.println(max);
	}

	@Test
	public void countPairWithTwoPower() {
		int[] arr = {1,3,5,7,9};
		int count = HashHelper.countPairs(arr);
		System.out.println(count);
	}

	@Test
	public void emailDistinctUserCount() {
		String[] emails = {"test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"};
		int count = ArrayHelper.numUniqueEmails(emails);
		System.out.println(count);
	}

	@Test
	public void data() {
		String result = ArrayHelper.licenseKeyFormatting("5F3Z-2e-9-w", 4);
		System.out.println(result);
	}

	@Test
	public void isPalindrome() {
		boolean isPalindrome = ArrayHelper.isPalindrome("A man, a plan, a canal: Panama");
		System.out.println(isPalindrome);
	}

	@Test
	public void mergeListTest() {
		int[][] arr = {{1, 4, 5}, {1, 3, 4}, {2, 6}};
		ListNode[] list = getListNodes(arr);

		ListNode result = ArrayHelper.mergeKLists(list);
		LinkedListHelper.print(result);
	}

	public ListNode[] getListNodes(int[][] list) {
		ListNode[] result = new ListNode[list.length];

		for(int i = 0; i < list.length; i++) {
			result[i] = createList(list[i]);
 		}

		return result;
	}

	public ListNode createList(int[] arr) {
		ListNode head = null, r = null;

		for(int val: arr) {
			ListNode temp = new ListNode(val);

			if (head == null) {
				head = temp;
				r = temp;
			} else {
				r.next = temp;
				r = r.next;
			}
		}

		return head;
	}

	@Test
	public void testMakeJump() {
		int[] arr = {2,3,0,1,4};
		int count = ArrayHelper.makeJump(arr);
		System.out.println(count);
	}

	@Test
	public void testClosestSum() {
		int[] arr = {-1,2,1,-4};

		int val = ArrayHelper.threeSumClosest(arr, 1);
		System.out.println(val);
	}

	@Test
	public void bullCowGetHint() {
		String secret = "1807";
		String guess = "7810";

		String hint = ArrayHelper.getHint(secret, guess);
		System.out.println(hint);
	}

	@Test
	public void findClosestElement() {
		int[] arr = {1,10,15,25,35,45,50,59};
		int k = 1, x = 30;

		System.out.println(ArrayHelper.findClosestElements(arr, k, x));
	}

	@Test
	public void compareVersion() {
		System.out.println(ArrayHelper.compareVersion("0.1","1.1"));
	}

	@Test
	public void toppingTest() {
		int[] baseCosts = {10};
		int[] toppingCosts = {1};
		int cost = ArrayHelper.closestCost(baseCosts, toppingCosts, 1);
		System.out.println(cost);
	}

	@Test
	public void bubbleSort() {
		int[] arr = {5, 9, 8, 2, 1};

		ArrayHelper.bubbleSort(arr);

		System.out.println(Arrays.toString(arr));
	}

	@Test
	public void selectionSort() {
		int[] arr = {5, 9, 8, 2, 1};

		ArrayHelper.selectionSort(arr);

		System.out.println(Arrays.toString(arr));
	}

	@Test
	public void removeKDigits() {
		String number = "14301620";
		String result = ArrayProblems.removeKDigits(number, 4);
		System.out.println(result);
	}
}
