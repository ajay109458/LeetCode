package tree;

import java.util.*;
import java.util.stream.Collectors;

import array.ArrayHelper;
import graph.DisjointSet;
import stack.QueueHelper;
import utils.PairSet;

public class TreeProblems {

	/**
	 * Maximum depth of a tree
	 */
	private static int preOrderIndex = 0;
	private static int postOrderIndex = 0;

	public static int getMaxDepth(TreeNode root) {

		if (root == null)
			return 0;

		int leftDepth = getMaxDepth(root.left);
		int rightDepth = getMaxDepth(root.right);

		return Math.max(leftDepth, rightDepth) + 1;
	}

	public static void printInOrder(TreeNode root) {
		if (root == null) {
			return;
		}

		printInOrder(root.left);
		System.out.print(root.val + " ");
		printInOrder(root.right);
	}

	public static TreeNode constructTreeFromInorderPreorder(int[] inOrder, int[] preOrder) {

		preOrderIndex = 0;

		return _constructTreeFromInorderPreorder(inOrder, preOrder, 0, inOrder.length - 1);
	}

	private static TreeNode _constructTreeFromInorderPreorder(int[] inorder, int[] preOrder, int inStart, int inEnd) {

		if (inStart > inEnd) {
			return null;
		}

		if (inStart == inEnd) {
			preOrderIndex++;
			return new TreeNode(inorder[inStart]);
		}

		int val = preOrder[preOrderIndex++];

		int indexOfElement = ArrayHelper.search(inorder, val);

		TreeNode root = new TreeNode();
		root.val = val;
		root.left = _constructTreeFromInorderPreorder(inorder, preOrder, inStart, indexOfElement - 1);
		root.right = _constructTreeFromInorderPreorder(inorder, preOrder, indexOfElement + 1, inEnd);

		return root;
	}

	public static TreeNode constructTreeFromInorderPostorder(int[] inOrder, int[] postOrder) {

		postOrderIndex = 0;

		return _constructTreeFromInorderPostorder(inOrder, postOrder, 0, inOrder.length - 1);
	}

	private static TreeNode _constructTreeFromInorderPostorder(int[] inOrder, int[] postOrder, int inStart, int inEnd) {

		if (inStart > inEnd) {
			return null;
		}

		if (inStart == inEnd) {
			postOrderIndex--;
			return new TreeNode(inOrder[inStart]);
		}

		int val = postOrder[postOrderIndex--];

		int indexOfElement = ArrayHelper.search(inOrder, val);

		TreeNode root = new TreeNode();
		root.val = val;
		root.right = _constructTreeFromInorderPreorder(inOrder, postOrder, indexOfElement + 1, inEnd);
		root.left = _constructTreeFromInorderPreorder(inOrder, postOrder, inStart, indexOfElement - 1);

		return root;
	}

	public static void printBottomUpLevelOrderTraversal(TreeNode root) {

		Queue<TreeNode> queue = new LinkedList<TreeNode>();

		if (root == null) {
			return;
		}

		queue.add(root);
		queue.add(null);

		Stack<List<TreeNode>> stack = new Stack<List<TreeNode>>();
		List<TreeNode> list = new ArrayList<>();

		while (true) {

			TreeNode node = queue.poll();

			if (node == null) {

				stack.add(list);

				if (queue.isEmpty())
					break;

				queue.add(null);

				list = new ArrayList<TreeNode>();

			} else {

				list.add(node);

				if (node.left != null) {
					queue.add(node.left);
				}

				if (node.right != null) {
					queue.add(node.right);
				}
			}

		}

		System.out.println("Printing node values : " + stack.size());
		while (!stack.isEmpty()) {
			list = stack.pop();

			for (TreeNode node : list) {
				System.out.print(node.val + " ");
			}
			System.out.println();
		}
	}

	public static TreeNode convertSortedArrayToBST(int[] arr) {
		return _convertSortedArrayToBST(arr, 0, arr.length - 1);
	}

	private static TreeNode _convertSortedArrayToBST(int[] arr, int left, int right) {

		if (left > right) {
			return null;
		}

		int mid = left + (right - left) / 2;

		TreeNode node = new TreeNode();
		node.val = arr[mid];
		node.left = _convertSortedArrayToBST(arr, left, mid - 1);
		node.right = _convertSortedArrayToBST(arr, mid + 1, right);

		return node;
	}

	public static boolean isHeightBalanced(TreeNode root) {

		if (root == null) {
			return true;
		}

		int leftHeight = getMaxDepth(root.left);
		int rightHeight = getMaxDepth(root.right);

		return isHeightBalanced(root.left) && isHeightBalanced(root.right) && Math.abs(rightHeight - leftHeight) < 2;

	}

	public static boolean checkRootToLeafSum(TreeNode root, int sum) {

		if (root == null) {
			return sum == 0;
		}

		int remainingSum = sum - root.val;

		return checkRootToLeafSum(root.left, remainingSum) || checkRootToLeafSum(root.right, remainingSum);

	}

	public static void printAllRootToLeafPathWithGivenSum(TreeNode root, int sum) {
		_populateRootToLeafPath(root, sum, new ArrayList<TreeNode>());
	}

	public static void _populateRootToLeafPath(TreeNode root, int sum, List<TreeNode> list) {

		if (root == null) {
			return;
		}

		list.add(root);
		sum -= root.val;

		if (root.left == null && root.right == null) {
			if (sum == 0) {
				System.out.println(list);
			}
		}

		_populateRootToLeafPath(root.left, sum, list);
		_populateRootToLeafPath(root.right, sum, list);

		list.remove(root);
	}

	public static List<TreeNode> convertTreeToFlattenList(TreeNode root) {
		List<TreeNode> list = new LinkedList<TreeNode>();
		_populateTreeToFlattenList(root, list);
		return list;
	}

	private static void _populateTreeToFlattenList(TreeNode root, List<TreeNode> list) {

		if (root == null) {
			return;
		}

		list.add(root);

		_populateTreeToFlattenList(root.left, list);
		_populateTreeToFlattenList(root.right, list);
	}
	
	
	public static int maxRootToLeafSum(TreeNode root) {
		
		if (root == null) {
			return 0;
		}

		long leftSum = Math.max(maxRootToLeafSum(root.left), Long.MIN_VALUE);
		long rightSum = Math.max(maxRootToLeafSum(root.right), Long.MIN_VALUE);

		return (int)Math.max(Math.max(leftSum, rightSum) + root.val, Long.MIN_VALUE);
		
	}

	public static long maxSumPath(TreeNode root) {

		if (root == null)
			return Long.MIN_VALUE;

		int currentSum =  root.val + maxRootToLeafSum(root.left) + maxRootToLeafSum(root.right);

		return Math.max(currentSum, Math.max(maxSumPath(root.left), maxSumPath(root.right)));
		
	}


	public List<List<Integer>> findLeaves(TreeNode root) {

		List<List<Integer>> result = new ArrayList<>();

		while(true) {
			List<Integer> list = new ArrayList<>();
			boolean status = getAndRemoveLeaf(root, list);

			result.add(list);

			if (status) {
				break;
			}
		}

		return result;
	}

	public boolean getAndRemoveLeaf(TreeNode root, List<Integer> leaves) {

		if (root == null) {
			return true;
		}

		if (root.left == null && root.right == null) {
			leaves.add(root.val);
			return true;
		}

		boolean isLeftLeaf = getAndRemoveLeaf(root.left, leaves);

		if (isLeftLeaf) {
			root.left = null;
		}

		boolean isRightLeaf = getAndRemoveLeaf(root.left, leaves);
		if (isRightLeaf) {
			root.right = null;
		}

		return false;
	}

	public List<Integer> findMaxInEachRow(TreeNode root) {
		List<Integer> result = new ArrayList<>();

		if (root == null)
			return result;

		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		queue.add(null);

		int max = 0;

		while(!queue.isEmpty()) {
			TreeNode temp = queue.poll();

			if (temp!= null) {
				max = Math.max(max, temp.val);

				if (temp.left != null) {
					queue.add(temp.left);
				}

				if (temp.right != null) {
					queue.add(temp.right);
				}
			} else {
				result.add(max);
				max = 0;
				queue.add(null);
			}

			if (queue.isEmpty())
				break;

		}

		return result;
	}

	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> list = new ArrayList<>();

		populateInorder(root, list);

		return list;
	}

	private void populateInorder(TreeNode root, List<Integer> list) {
		if (root == null) {
			return;
		}

		populateInorder(root.left, list);
		list.add(root.val);
		populateInorder(root.right, list);
	}

	private void populateInorder1(TreeNode root, List<TreeNode> list) {
		if (root == null) {
			return;
		}

		populateInorder1(root.left, list);
		list.add(root);
		populateInorder1(root.right, list);
	}


	public List<List<Integer>> levelOrder(TreeNode root) {

		List<List<Integer>> result = new ArrayList<>();

		if (root == null) {
			return result;
		}

		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		queue.add(null);

		List<Integer> list = new ArrayList<>();

		boolean fromStart = true;

		while(!queue.isEmpty()) {
			TreeNode temp = queue.poll();

			if (temp != null)  {
				list.add(temp.val);

				if (fromStart) {
					if (temp.left != null) {
						queue.add(temp.left);
					}

					if (temp.right != null) {
						queue.add(temp.right);
					}
				} else {
					if (temp.right != null) {
						queue.add(temp.right);
					}

					if (temp.left != null) {
						queue.add(temp.left);
					}
				}
 			} else {
				result.add(list);

				fromStart = !fromStart;

				if (!queue.isEmpty()) {
					queue.add(null);
				}

				list = new ArrayList<>();
			}
		}


		return result;
	}

	public List<List<Integer>> levelOrderZig(TreeNode root) {
		List<List<Integer>> result = new ArrayList<>();

		if (root == null) {
			return result;
		}

		Stack<TreeNode> s1 = new Stack<>();
		Stack<TreeNode> s2 = new Stack<>();

		s1.add(root);

		boolean isFirst = true;

		List<Integer> list = new ArrayList<>();

		while(!s1.isEmpty() && !s2.isEmpty()) {

			while(!s1.isEmpty()) {
				TreeNode temp = s1.pop();

				list.add(temp.val);

				if (temp.right != null) {
					s2.add(temp.right);
				}

				if (temp.left != null) {
					s2.add(temp.left);
				}
			}

			if (!list.isEmpty()) {
				result.add(list);
				list = new ArrayList<>();
			}

			while(!s2.isEmpty()) {
				TreeNode temp = s2.pop();

				list.add(temp.val);

				if (temp.left != null) {
					s2.add(temp.left);
				}

				if (temp.right != null) {
					s2.add(temp.right);
				}
			}

			if (!list.isEmpty()) {
				result.add(list);
				list = new ArrayList<>();
			}
		}

		return result;
	}

	public List<List<Integer>> pathSum(TreeNode root, int sum) {
		List<List<Integer>> result = new ArrayList<>();

		if (root == null) {
			return result;
		}

		List<TreeNode> list = new ArrayList<>();
		populateSumPath(root, sum, list, result);

		return result;
	}

	private void populateSumPath(TreeNode root, int remainingSum, List<TreeNode> list, List<List<Integer>> result) {

		if (root == null) {
			return;
		}


		list.add(root);
		remainingSum = remainingSum - root.val;


		if (root.left == null && root.right == null && remainingSum == 0) {
			List<Integer> temp = list.stream().map(node -> node.val).collect(Collectors.toList());
			result.add(temp);
			list.remove(root);
			return;
		}

		populateSumPath(root.left, remainingSum, list, result);
		populateSumPath(root.right, remainingSum, list, result);
		list.remove(root);

		return;
	}

	public static TreeNode constructTree(char[] arr) {
		int index = 0;

		if (arr.length == 0) {
			return null;
		}

		TreeNode root = new TreeNode(arr[index++] - '0');

		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);

		while(!queue.isEmpty()) {
			if (index >= arr.length) {
				break;
			}

			TreeNode temp = queue.poll();

			char ch = arr[index++];

			if (ch == 'n') {
				temp.left = null;
			} else {
				temp.left = new TreeNode(ch - '0');
				queue.add(temp.left);
			}

			if (index >= arr.length) {
				break;
			}

			ch = arr[index++];

			if (ch == 'n') {
				temp.right = null;
			} else {
				temp.right = new TreeNode(ch - '0');
				queue.add(temp.right);
			}
		}

		return root;
	}

	public static void inOrder(TreeNode root) {
		if (root == null) {
			return;
		}

		inOrder(root.left);
		System.out.print(root.val + " ");
		inOrder(root.right);
	}

	static TreeNode prev = null;
	public static void flatten(TreeNode root) {
		prev = null;
		flattenz(root);
	}

	private static void flattenz(TreeNode root) {

		if (root == null) {
			return;
		}

		TreeNode left = root.left;
		TreeNode right = root.right;

		root.right = null;

		root.left = null;

		if (prev != null) {
			prev.right = root;
		}

		prev = root;

		flattenz(left);
		flattenz(right);

	}

	public static void connectNextPointer(TreeNode root) {

		if (root == null) {
			return;
		}

		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		queue.add(null);

		while(!queue.isEmpty()) {

			TreeNode temp = queue.poll();

			if (temp != null) {

				if (queue.peek() != null) {
					temp.next = queue.peek();
				}

				if (temp.left != null) {
					queue.add(temp.left);
				}

				if (temp.right != null) {
					queue.add(temp.right);
				}

			} else {

				if (queue.isEmpty()) {
					break;
				}

				queue.add(null);
			}
		}

	}

	public int maxPathSum(TreeNode root) {
		return -1;
	}

	public int rangeSumBST(TreeNode root, int low, int high) {
		if (root == null) {
			return 0;
		}

		int sum = 0;

		if (root.val >= low && root.val <= high) {
			sum += root.val;
		}

		if (root.val < low) {
			return sum + rangeSumBST(root.right, low, high);
		}

		if (root.val > high) {
			return sum + rangeSumBST(root.left, low, high);
		}

		return sum + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
	}

	public List<Integer> getLonelyNodes(TreeNode root) {
		List<Integer> lonelyNodes = new ArrayList<>();

		populateLonelyNodes(root, lonelyNodes);

		return lonelyNodes;

	}

	void populateLonelyNodes(TreeNode root, List<Integer> lonelyNodes){
		if ( root == null) {
			return;
		}

		if (root.left == null && root.right == null) {
			return;
		}

		if (root.left == null || root.right == null) {
			if (root.left != null) {
				lonelyNodes.add(root.left.val);
			} else if (root.right != null) {
				lonelyNodes.add(root.right.val);
			}
		}


		populateLonelyNodes(root.left, lonelyNodes);
		populateLonelyNodes(root.right, lonelyNodes);
	}

	public int sumRootToLeaf(TreeNode root) {
		return sumRootToLeaf(root, 0);
	}

	private int sumRootToLeaf(TreeNode root, int val) {
		if (root == null) {
			return 0;
		}

		int currVal = 2 * val + root.val;

		if (root.left == null && root.right == null) {
			return currVal;
		}

		return sumRootToLeaf(root.left, currVal) + sumRootToLeaf(root.right, currVal);
	}

	public List<Double> averageOfLevels(TreeNode root) {
		Map<Integer, Integer> sumByLevelMap = new HashMap<>();
		Map<Integer, Integer> countByLevelMap = new HashMap<>();

		populateMap(root, sumByLevelMap, countByLevelMap, 0);

		List<Double> result = new ArrayList<>();
		for(int i = 0; i < sumByLevelMap.size(); i++) {
			result.add((sumByLevelMap.get(i)* 1.0)/countByLevelMap.get(i));
		}

		return result;
	}

	private void populateMap(TreeNode root, Map<Integer, Integer> sumByLevelMap, Map<Integer, Integer> countByLevelMap, int level) {

		if (root == null) {
			return;
		}

		sumByLevelMap.put(level, (sumByLevelMap.get(level) == null? 0 : sumByLevelMap.get(level)) + root.val);
		countByLevelMap.put(level, (countByLevelMap.get(level) == null? 0 : countByLevelMap.get(level)) + 1);

		populateMap(root.left, sumByLevelMap, countByLevelMap, level + 1);
		populateMap(root.right, sumByLevelMap, countByLevelMap, level + 1);
	}

	public TreeNode trimBST(TreeNode root, int low, int high) {
		if (root == null) {
			return null;
		}

		if (root.val < low) {
			return trimBST(root.right, low, high);
		}

		if (root.val > high) {
			return trimBST(root.left, low, high);
		}

		root.left = trimBST(root.left, low, high);
		root.right = trimBST(root.right, low, high);
		return root;
	}

	public boolean findTarget(TreeNode root, int k) {
		List<Integer> inOrderList = new ArrayList<>();
		populateInOrderList(root, inOrderList);
		Integer[] numbers = inOrderList.toArray(new Integer[inOrderList.size()]);

		int left = 0;
		int right = numbers.length -1;

		while(left < right) {
			int cSum = numbers[left] + numbers[right];

			if (cSum == k) {
				return  true;
			} else if (cSum < k) {
				left++;
			} else {
				right--;
			}
		}

		return false;
	}

	public void populateInOrderList(TreeNode root, List<Integer> list) {

		if (root == null) {
			return;
		}

		populateInorder(root.left, list);
		list.add(root.val);
		populateInorder(root.right, list);
	}


	public String tree2str(TreeNode t) {
		if (t == null) {
			return "";
		}

		String result = "";
		result += tree2str(t.left);
		result += "(" + t.val + ")";
		result += tree2str(t.right);
		return result;
	}

	public int getLevel(TreeNode root, int val, int level) {
		if (root == null) {
			return -1;
		}

		if (root.val == val) {
			return level;
		}

		int cLevel = getLevel(root.left, val, level + 1);
		if (cLevel != -1) {
			return cLevel;
		}
		return getLevel(root.right, val, level);
	}

	public boolean isSameParent(TreeNode root, int x, int y) {
		if (root == null) {
			return false;
		}

		if (root.left == null || root.right == null) {
			return false;
		}

		return  (root.left.val == x && root.right.val == y) ||
				(root.right.val == x && root.left.val == y) ||
				isSameParent(root.left, x, y) ||
				isSameParent(root.right, x, y);
	}

	public boolean isCousins(TreeNode root, int x, int y) {
		if (root == null) {
			return false;
		}

		int xLevel = getLevel(root, x, 0);
		int yLevel = getLevel(root, y, 0);

		return (xLevel == yLevel && !isSameParent(root, x, y));
	}

	public boolean isSubtree(TreeNode s, TreeNode t) {
		if (s == null) {
			return true;
		}

		if (t == null)
			return false;

		return isEqual(s, t) || isSubtree(s, t.left) || isSubtree(s, t.right);
	}

	public boolean isEqual(TreeNode r1, TreeNode r2) {
		if (r1 == null && r2 == null) {
			return true;
		}

		if (r1 == null || r2 == null) {
			return false;
		}

		return r1.val == r2.val && isEqual(r1.left, r2.left) && isEqual(r1.right, r2.right);
	}

	public int findSecondMinimumValue(TreeNode root) {
		List<Integer> inorder = new ArrayList<>();
		populateInorder(root, inorder);

		int min = Integer.MAX_VALUE;
		int secondMin = Integer.MAX_VALUE;

		for(int val : inorder) {
			if (val < min) {
				if (min < secondMin) {
					secondMin = min;
				}

				min = val;
			} else if (val > min && val < secondMin) {
				secondMin = val;
			}
		}

		return (secondMin == Integer.MAX_VALUE) ? -1 : secondMin;
	}


	public int deepestLeavesSum(TreeNode root) {
		int height = getHeight(root);

		return sumOfLeafAtLevel(root, height);
	}

	public int sumOfLeafAtLevel(TreeNode root, int level) {
		if (root == null || level == 0) {
			return 0;
		}

		if (level == 1) {
			return root.val;
		}

		return sumRootToLeaf(root.left, level) + sumRootToLeaf(root.right, level);
	}

	public int getHeight(TreeNode root) {
		if (root == null) {
			return 0;
		}

		return 1 + Math.max(getHeight(root.left), getHeight(root.right));
	}

	private int pIndex = 0;

	public TreeNode buildTree(int[] preorder, int[] inorder) {
		return buildTree(preorder, inorder, 0, inorder.length-1);
	}

	public TreeNode buildTree(int[] preorder, int[] inorder, int left, int right) {
		if (left > right) {
			return null;
		}

		int target = preorder[pIndex++];
		int index = getIndex(inorder, target);

		if (index == -1) {
			return null;
		}

		TreeNode root = new TreeNode(target);
		root.left = buildTree(preorder, inorder, left, index - 1);
		root.right = buildTree(preorder, inorder, index + 1, right);

		return root;
	}

	public int getIndex(int[] arr, int target) {
		int left = 0;
		int right = arr.length -1;

		for (int i = left; i <= right; i++) {
			if (arr[i] == target) {
				return i;
			}
		}

		return -1;
	}

	public int sumEvenGrandparent(TreeNode root) {
		return sumEvenGrandParentInt(root, null);
	}


	public int sumEvenGrandParentInt(TreeNode root, TreeNode parent) {
		if (root == null) {
			return 0;
		}

		int sum = 0;

		if (parent != null && parent.val % 2 == 0) {
			if (root.left != null) {
				sum += root.left.val;
			}

			if (root.right != null) {
				sum += root.right.val;
			}
		}

		return sum + sumEvenGrandParentInt(root.left, root);
	}

	public boolean isEvenOddTree(TreeNode root) {
		int level = 0;

		if (root == null) {
			return true;
		}

		TreeNode prev = null;

		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);

		while(!queue.isEmpty()) {

			TreeNode node = queue.poll();

			if (node == null) {
				if (queue.isEmpty()) {
					break;
				}

				queue.add(null);
			} else {

				if (level % 2 == 0 && node.val %2 == 0) {
					return false;
				} else if (level % 2 != 0 && node.val %2 != 0) {
					return false;
				} else {
					if (prev != null) {
						if (level%2 == 0 && prev.val > node.val) {
							return false;
						} else if (level % 2 != 0 && prev.val < node.val) {
							return false;
						}
					}
				}

				if (node.left != null) {
					queue.add(node);
				}

				if (node.right != null) {
					queue.add(node);
				}
			}

			prev = node;
		}

		return true;
	}

	public TreeNode constructMaximumBinaryTree(int[] nums) {
		return constructMaximumBinaryTree(nums, 0, nums.length -1);
	}

	public TreeNode constructMaximumBinaryTree(int[] nums, int left, int right) {

		if (left >  right) {
			return null;
		}

		int maxIndex = left;
		for (int i = left + 1; i <= right; i++) {
			if (nums[maxIndex] < nums[i]) {
				maxIndex = i;
			}
		}

		TreeNode root = new TreeNode(nums[maxIndex]);
		root.left = constructMaximumBinaryTree(nums, left, maxIndex -1);
		root.right = constructMaximumBinaryTree(nums, maxIndex + 1, right);

		return root;
	}

	public TreeNode cloneTree(TreeNode root) {
		if (root == null) {
			return null;
		}

		TreeNode cloneRoot = new TreeNode(root.val);


		return cloneRoot;
	}

	public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
		List<Integer> list1 = new ArrayList<>();
		List<Integer> list2 = new ArrayList<>();

		populateInorder(root1, list1);
		populateInorder(root2, list2);

		List<Integer> result = new ArrayList<>();

		while(!list1.isEmpty() && !list2.isEmpty()) {
			if (list1.get(0) < list2.get(0)) {
				result.add(list1.remove(0));
			} else {
				result.add(list2.remove(0));
			}
		}


		result.addAll(list2);
		result.addAll(list1);

		return result;
	}

	public TreeNode findNearestRightNode(TreeNode root, TreeNode u) {
		if (root == null) {
			return null;
		}

		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		queue.add(null);

		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();

			if (node == u) {
				return queue.peek();
			}

			if (node == null) {
				if (queue.isEmpty()) {
					break;
				}
				queue.add(null);
			} else {

				if (node.left != null) {
					queue.add(node.left);
				}

				if (node.right != null) {
					queue.add(node.right);
				}
			}

		}

		return null;
	}

	public int maxLevelSum(TreeNode root) {
		Map<Integer, Integer> sumByLevelMap = new TreeMap<>();

		populateLevelSum(root, sumByLevelMap, 1);

		int maxLevel = 1;
		int maxValue = 0;
		for(Map.Entry<Integer, Integer> entry: sumByLevelMap.entrySet()) {
			if (entry.getValue() > maxValue) {
				maxValue = entry.getValue();
				maxLevel = entry.getKey();
			}
		}

		return maxLevel;
	}

	public void populateLevelSum(TreeNode root, Map<Integer, Integer> sumMap, int level) {
		if (root == null) {
			return;
		}

		Integer sum = sumMap.get(level);
		if (sum == null) {
			sum = 0;
		}

		sum += root.val;
		sumMap.put(level, sum);

		populateLevelSum(root.left, sumMap, level + 1);
		populateLevelSum(root.right, sumMap, level + 1);
	}

	public int goodNodes(TreeNode root) {
		return countGoodNodes(root, null);
	}

	public int countGoodNodes(TreeNode root, Integer maxValue) {
		if (root == null) {
			return 0;
		}

		int count = 0;

		if (maxValue == null || root.val > maxValue) {
			maxValue = root.val;
			count++;
		}

		return count + countGoodNodes(root.left, maxValue) + countGoodNodes(root.right, maxValue);
	}

	public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
		Set<Integer> nodesToDelete = new HashSet<>();
		List<TreeNode> result = new ArrayList<>();


		for (int val : to_delete) {
			nodesToDelete.add(val);
		}

		populateDelTree(root, nodesToDelete, result, true);

		return result;
	}

	private TreeNode populateDelTree(TreeNode root, Set<Integer> nodesToDelete, List<TreeNode> result, boolean isRoot) {
		if (root == null) {
			return null;
		}

		if (!nodesToDelete.contains(root.val) && isRoot) {
			result.add(root);
		}

		TreeNode rootLeft = root.left;
		TreeNode rootRight = root.right;


		boolean shouldDelete = false;
		if (nodesToDelete.contains(root.val)) {
			root.left = null;
			root.right = null;
			shouldDelete = true;
		}

		root.left = populateDelTree(rootLeft, nodesToDelete, result, shouldDelete);
		root.right = populateDelTree(rootRight, nodesToDelete, result, shouldDelete);

		if (shouldDelete) {
			return null;
		}

		return root;
	}

	public int[] findFrequentTreeSum(TreeNode root) {

		if (root == null) {
			return new int[0];
		}

		Map<Integer, Integer> freqBySumCountMap = new HashMap<>();
		populateFrequencyOfSum(root, freqBySumCountMap);

		List<Map.Entry> entries = freqBySumCountMap.entrySet().stream()
				.sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
				.collect(Collectors.toList());

		 long maxFreq = (Long)entries.get(0).getValue();

		 List<Integer> result = new ArrayList<>();

		 for(Map.Entry<Integer, Long> entry : entries) {
		 	if (entry.getValue().equals(maxFreq)) {
		 		result.add(entry.getKey());
			}
		 }

		 int[] arr = new int[result.size()];
		 for(int  i = 0; i < result.size(); i++) {
		 	arr[i] = result.get(i);
		 }
		 return arr;
	}

	public int populateFrequencyOfSum(TreeNode root, Map<Integer, Integer> freqBySumCountMap) {
		if (root == null) {
			return 0;
		}

		int sum = root.val
				+ populateFrequencyOfSum(root.left, freqBySumCountMap)
				+ populateFrequencyOfSum(root.right, freqBySumCountMap);

		Integer count =  freqBySumCountMap.get(sum);
		count = (count == null? 0: count) + 1;
		freqBySumCountMap.put(sum, count);

		return sum;
	}

	class BSTIterator {

		private TreeNode root;
		Stack<TreeNode> stack;

		public BSTIterator(TreeNode root) {
			this.root = root;
			stack = new Stack<>();

			while(root != null) {
				stack.add(root);
				root = root.left;
			}
		}

		public int next() {
			if (stack.isEmpty()) {
				return -1;
			}

			TreeNode top = stack.pop();

			if (top.right != null) {
				TreeNode root = top.right;
				while(root != null) {
					stack.add(root);
					root = root.left;
				}
			}

			return top.val;
		}

		public boolean hasNext() {
			return !stack.isEmpty();
		}
	}

	class MyStack {

		/*
		*  1 2 3 4 5
		*
		* */

		Queue<Integer> queue;

		/** Initialize your data structure here. */
		public MyStack() {
			queue = new LinkedList<>();
		}

		/** Push element x onto stack. */
		public void push(int x) {
			Queue<Integer> tempQueue = new LinkedList<>();

			while(!queue.isEmpty()) {
				tempQueue.add(queue.remove());
			}

			queue.add(x);

			while(!tempQueue.isEmpty()) {
				queue.add(tempQueue.remove());
			}
		}

		/** Removes the element on top of the stack and returns that element. */
		public int pop() {
			return queue.remove();
		}

		/** Get the top element. */
		public int top() {
			return queue.peek();
		}

		/** Returns whether the stack is empty. */
		public boolean empty() {
			return queue.isEmpty();
		}
	}

	public boolean verifyPreorder(int[] preorder) {
		int[] inorder = new int[preorder.length];
		preOrderIndex = 0;

		for(int i = 0; i < preorder.length; i++) {
			inorder[i] = preorder[i];
		}

		Arrays.sort(inorder);

		return isInRange(preorder, inorder, 0, inorder.length -1, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	public boolean isInRange(int[] preOrder, int[] inorder, int left, int right, int min, int max) {
		int index = Arrays.binarySearch(inorder, left, right,  preOrder[preOrderIndex]);

		int rootVal = inorder[index];

		if (rootVal < min || rootVal > max) {
			return false;
		}

		preOrderIndex++;
		return isInRange(preOrder, inorder, left, index - 1, min, rootVal) &&  isInRange(preOrder, inorder, index + 1, right, rootVal, max);
	}

	public String removeDuplicateLetters(String s) {
		int[] mapper = new int[26];

		for(char ch : s.toCharArray()) {
			mapper[ch - 'a'] = 1;
		}

		StringBuilder result = new StringBuilder();
		for(int i = 0; i < 26; i++) {
			if (mapper[i] == 1) {
				result.append((char)(i +'a'));
			}
		}

		return result.toString();
	}

	private int kValue = 0;

	public int kSmallest(TreeNode root) {
		if (root == null) {
			return -1;
		}

		if (kValue == 0) {
			return root.val;
		}

		int val = kSmallest(root.left);
		if (val != -1) {
			return val;
		}

		kValue--;

		return kSmallest(root.right);
	}

	public int closestValue(TreeNode root, double target) {
		return closestValue(root, target, null);
	}

	public int closestValue(TreeNode root, double target, TreeNode closest) {
		if (root == null) {
			if (closest != null) {
				return closest.val;
			}

			return -1;
		}

		if (closest == null) {
			closest = root;
		} else {
			if (Math.abs(root.val - target) < Math.abs(closest.val - target)) {
				closest = root;
			}
		}

		if (root.val <= target) {
			return closestValue(root.right, target, closest);
		}

		 return closestValue(root.right, target, closest);
	}

	public TreeNode invertTree(TreeNode root) {
		if (root == null) {
			return null;
		}

		TreeNode temp = root.left;
		root.left = invertTree(root.right);
		root.right = invertTree(temp);

		return root;
	}

	public int numTrees(int n) {
		return catalanNumber(n);
	}

	private int catalanNumber(int n) {
		int[] catalan  = new int[n+1];
		catalan[0] = catalan[1] = 1;

		for(int i = 2; i <=n; i++) {
			catalan[i] = 0;
			for(int j = 0; j < i; j++) {
				catalan[i] += catalan[j] * catalan[i-j-1];
			}
		}

		return catalan[n];
	}

	/**
	 * Nodes on the left are smaller and Nodes on the right are larger
	 * @param n
	 * @return
	 */
	public List<TreeNode> generateTrees(int n) {
		if (n == 0) {
			return new ArrayList<>();
		}

		return generateTreeList(1, n);
	}

	private List<TreeNode> generateTreeList(int start, int end) {
		List<TreeNode> list = new ArrayList<>();

		if (start > end) {
			return list;
		}

		for (int i = start; i <= end; i++) {
			List<TreeNode> leftList = generateTreeList(start, i-1);
			List<TreeNode> rightList = generateTreeList(i+1, end);

			for(TreeNode left : leftList) {
				for(TreeNode right : rightList) {
					TreeNode root = new TreeNode(i);

					root.left = left;
					root.right = right;

					list.add(root);
				}
			}
		}

		return list;
	}

	public boolean isValidBST(TreeNode root) {
		return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
	}

	private boolean isValidBST(TreeNode root, long min, long max) {
		if (root == null) {
			return true;
		}

		return (root.val >= min && root.val <= max) && isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
	}


	public void recoverTree(TreeNode root) {
		prev = null;
		PairSet<TreeNode, TreeNode> firstPair = new PairSet<>(null, null);
		PairSet<TreeNode, TreeNode> secondPair = new PairSet<>(null, null);

		recoverInorder(root, firstPair, secondPair);

		TreeNode firstNode =  firstPair.x;
		TreeNode secondNode = firstPair.y;
		if (secondPair.x != null && secondPair.y != null) {
			secondNode = secondPair.y;
		}

		//swap first and second node
		if (firstNode != null) {
			int x = firstNode.val;
			firstNode.val = secondNode.val;
			secondNode.val = x;
		}
	}

	private void recoverInorder(TreeNode root, PairSet<TreeNode, TreeNode> firstPair, PairSet<TreeNode, TreeNode> secondPair) {

		if (root == null) {
			return;
		}

		recoverInorder(root.left, firstPair, secondPair);

		if (prev != null && prev.val > root.val) {
			if (firstPair.x == null) {

				firstPair.x = prev;
				firstPair.y = root;
			} else {
				secondPair.x = prev;
				secondPair.y = root;
			}
		}
		prev = root;
		recoverInorder(root.right, firstPair, secondPair);

	}

	int postIndex;
	public TreeNode buildTreePostOrder(int[] inorder, int[] postorder) {
		postIndex = postorder.length - 1;
		Map<Integer, Integer>  indexByValMap = new HashMap<>();

		for(int i = 0; i < inorder.length; i++) {
			indexByValMap.put(inorder[i], i);
		}

		return constructPostOrder(inorder, postorder, 0, postIndex, indexByValMap);
	}

	private TreeNode constructPostOrder(int[] inorder, int[] postorder, int left, int right, Map<Integer, Integer>  indexByValMap) {

		if (postIndex < 0) {
			return null;
		}

		int val = postorder[postIndex--];
		Integer index = indexByValMap.get(val);

		if (index == null) {
			return null;
		}

		TreeNode root = new TreeNode(val);

		root.right = constructPostOrder(inorder, postorder, index+1, right, indexByValMap);
		root.left = constructPostOrder(inorder, postorder, left, index -1, indexByValMap);

		return root;
	}

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null)
			return null;

		if (root == p || root == q)
			return root;

		TreeNode left = lowestCommonAncestor(root.left, p, q);
		TreeNode right = lowestCommonAncestor(root.right, p, q);

		if (left != null && right != null) {
			return root;
		}

		if (left != null)
			return left;

		return right;
	}

	TreeNode lca = null;
	int lcaMatchCount = 0;
	public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
		lca = null;
		LCA(root, p, q);

		if (lcaMatchCount < 2) {
			return null;
		}

		return lca;
	}

	private TreeNode LCA(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null)
			return null;

		if (root == p || root == q) {
			lcaMatchCount++;
			return root;
		}

		TreeNode left = LCA(root.left, p, q);
		TreeNode right = LCA(root.right, p, q);

		if (left != null && right != null) {
			lca = root;
			return root;
		}

		if (left != null)
			return left;

		return right;
	}

	int countUniversalSubTrees = 0;
	public int countUnivalSubtrees(TreeNode root) {
		countUniversalSubTrees = 0;
		countUniSubTree(root);

		return countUniversalSubTrees;
	}

	boolean countUniSubTree(TreeNode root) {
		if (root == null)
			return true;

		boolean left = countUniSubTree(root.left);
		boolean right = countUniSubTree(root.right);

		if (left == false || right == false)
			return false;

		if (root.left != null && root.val != root.left.val)
			return false;

		if (root.right != null && root.val != root.right.val)
			return false;

		countUniversalSubTrees++;

		return true;
	}

	public boolean isNStraightHand(int[] hand, int W) {
		TreeMap<Integer, Integer> map = new TreeMap<>();
		for(int card : hand) {
			map.put(card, map.getOrDefault(card, 0) + 1);
		}

		while(!map.isEmpty()) {
			int firstCard = map.firstKey();

			for(int i = firstCard; i < firstCard + W; i++) {
				if (!map.containsKey(i))
					return  false;
				int count = map.get(i);
				if (count == 1) {
					map.remove(i);
				} else {
					map.put(i, count - 1);
				}
			}
		}

		return true;
	}


	public List<Integer> closestKValues(TreeNode root, double target, int k) {
		List<Integer> list = new ArrayList<>();
		populateInorder(root, list);

		Collections.sort(list, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				Double t1 = Math.abs(o1 - target);
				Double t2 = Math.abs(o2 - target);

				return t1.compareTo(t2);
			}
		});

		List<Integer> result = new ArrayList<>();
		int index = 0;
		while (k-- > 0) {
			result.add(list.get(index++));
		}

		return result;
	}

	TreeNode inorderSuccessor;
	public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
		if (root == null)
			return null;

		TreeNode tn = inorderSuccessor(root.right, p);

		if (tn != null) {
			return tn;
		}

		if (root == p) {
			return inorderSuccessor;
		}

		inorderSuccessor = root;
		return inorderSuccessor(root.left, p);

	}

	public class Codec {

		// Encodes a tree to a single string.
		public String serialize(TreeNode root) {
			if (root == null) {
				return "X,";
			}

			return root.val + "," + serialize(root.left) + serialize(root.right);
		}

		// Decodes your encoded data to tree.
		public TreeNode deserialize(String data) {
			Queue<String> nodesLeft = new LinkedList<>();
			nodesLeft.addAll(Arrays.asList(data.split(",")));
			return deserializeHelper(nodesLeft);
		}

		public TreeNode deserializeHelper(Queue<String> nodesLeft) {
			String valueForNode = nodesLeft.poll();

			if (valueForNode.equals("X"))
				return null;

			TreeNode root = new TreeNode(Integer.valueOf(valueForNode));
			root.left = deserializeHelper(nodesLeft);
			root.right = deserializeHelper(nodesLeft);
			return root;
		}
	}

	TreeNode prevNode;
	public TreeNode increasingBST(TreeNode root) {

		if (root == null)
			return null;

		List<TreeNode> list = new ArrayList<>();
		populateInorder1(root, list);

		for(int i = 1 ; i < list.size(); i++) {
			list.get(i-1).right = list.get(i).right;
		}

		return list.get(0);
	}

	public int longestConsecutive(TreeNode root) {
		return longestConsecutive(root, null, 0);
	}

	private int longestConsecutive(TreeNode root, TreeNode parent, int len) {
		if (root == null)
			return len;

		int temp = len;
		if (parent != null && root.val - parent.val != 1) {
			len = 0;
		}

		int childLen =  Math.max(longestConsecutive(root.left, root, len + 1), longestConsecutive(root.right, root, len + 1));
		return Math.max(temp, childLen);
	}

	private int sizeOfTree(TreeNode root) {
		if (root == null)
			return 0;

		return sizeOfTree(root.left) + 1 + sizeOfTree(root.right);
	}

	public int largestBSTSubtree(TreeNode root) {
		if (root == null)
			return 0;

		if (isValidBST(root))
			return sizeOfTree(root);

		return Math.max(largestBSTSubtree(root.left), largestBSTSubtree(root.right));
	}

	public int rob(TreeNode root) {
		if (root == null)
			return 0;

		int val  = 0;

		if (root.left != null) {
			val += (rob(root.left.left) + rob(root.left.right));
		}

		if (root.right != null) {
			val += (rob(root.right.left) + rob(root.right.right));
		}

		return Math.max(root.val + val, rob(root.left) + rob(root.right));
	}

	public void populateTreeArray(TreeNode root, int level, int[] arr) {
		if (root == null) {
			return;
		}

		arr[level] += root.val;

		populateTreeArray(root.left, level + 1, arr);
		populateTreeArray(root.right, level + 1, arr);
	}

	public int[] findMode(TreeNode root) {
		List<Integer> list = new ArrayList<>();
		populateInorder(root, list);

		List<Integer> modes = new ArrayList<>();

		int max = 0;

		fillModes(root, modes);

		int[] res = new int[modes.size()];
		for(int i = 0; i < res.length; i++) {
			res[i] = modes.get(i);
		}

		return res;
	}

	int modCount = 1;
	int max = 0;
	public void fillModes(TreeNode node, List<Integer> modes) {
		if (node == null)
			return;

		fillModes(node.left, modes);

		if (prev != null) {
			if (prev.val == node.val)
				modCount ++;
			else {
				modCount = 1;
			}
		}

		if (modCount > max) {
			modes.clear();
			modes.add(node.val);
			max = modCount;
		} else if (modCount == max) {
			modes.add(node.val);
		}

		prev = node;

		fillModes(node.right, modes);
	}

	public int pathSum1(TreeNode root, int sum) {
		return pathSum(root, sum, 0, new HashSet<>());
	}

	private int pathSum(TreeNode root, int sum, int curSum, Set<Integer> prefixSum) {
		if(root == null)
			return 0;

		curSum += root.val;
		int count = 0;
		if (prefixSum.contains(curSum - sum)) {
			count+= 1;
		}
		prefixSum.add(curSum);

		count += pathSum(root.left, sum, curSum, prefixSum);
		count += pathSum(root.right, sum, curSum, prefixSum);

		return count;
	}

	public int pathSum3(TreeNode root, int sum) {
		if (root == null)
			return 0;

		return pathSum3(root.left, sum) + pathSum3(root.right, sum) + pathSumInternal(root, sum);
	}

	private int pathSumInternal(TreeNode root, int sum) {
		if (root == null)
			return 0;
		int count = 0;

		if (root.val == sum)
			count++;

		count += pathSumInternal(root.left, sum - root.val);
		count += pathSumInternal(root.right, sum - root.val);
		return  count;
	}

	/*
	* Cases:
	*  Node that we have to delete has
	* - No child
	* - Single child (left child or right child)
	* - Both the child
	*
	* */
	public TreeNode deleteNode(TreeNode root, int key) {
		if (root == null)
			return null;

		if (key > root.val) {
			root.right = deleteNode(root.right, key);
		} else if (key < root.val) {
			root.left = deleteNode(root.left, key);
		} else {
			if (root.left != null && root.right != null) {
				int lmax = getMax(root.left);
				root.val = lmax;
				root.left = deleteNode(root.left, lmax);
			} else if (root.left != null) {
				return root.left;
			} else if (root.right != null) {
				return root.right;
			} else {
				return null;
			}
		}

		return root;
	}

	public int getMax(TreeNode root) {
		if (root.right != null) {
			return getMax(root.right);
		} else {
			return root.val;
		}
	}

	boolean isNodeFound = false;
	public TreeNode inorderSuccessor(TreeNode node) {

		return getInorderSuccessor(node, node);

	}

	private TreeNode getInorderSuccessor(TreeNode root, TreeNode node) {
		if (root == null)
			return null;

		TreeNode succ = getInorderSuccessor(root.left, node);

		if (succ != null)
			return succ;

		if (isNodeFound) {
			isNodeFound = false;
			return root;
		}

		if (root == node) {
			isNodeFound = true;
		}

		return getInorderSuccessor(root.right, node);
	}


	public int findBottomLeftValue(TreeNode root) {
		Map<Integer, Integer> map = new HashMap<>();
		populateLeftMostAtLevel(root, map, 0);

		Optional<Integer> max = map.keySet().stream().max(Integer::compareTo);
		return map.get(max.get());
	}

	private void populateLeftMostAtLevel(TreeNode root, Map<Integer, Integer> map, int level) {
		if (root == null)
			return;

		map.putIfAbsent(level, root.val);

		populateLeftMostAtLevel(root.left, map, level + 1);
		populateLeftMostAtLevel(root.right, map, level + 1);
	}

	public TreeNode str2tree(String s) {
		if (s == null || "".equals(s))
			return null;

		int index = 0;

		int rootVal = 0;

		while(index < s.length()) {
			char ch = s.charAt(index);
			if (Character.isDigit(ch)) {
				rootVal = rootVal * 10 + (ch - '0');
			} else {
				break;
			}
		}

		int startIndex = -1;
		int count = 0;
		String leftChildString = "";
		while(index < s.length()) {
			char ch = s.charAt(index);
			if (ch == '(') {
				if (startIndex != -1) {
					startIndex = index;
				}
				count++;
			}
			if (ch == ')')
				count--;

			if (count == 0) {
				leftChildString = s.substring(startIndex, index);
				break;
			}
		}

		startIndex  = -1;
		index = index+1;
		String rightChildString = "";
		while(index < s.length()) {
			char ch = s.charAt(index);
			if (ch == '(') {
				if (startIndex != -1) {
					startIndex = index;
				}
				count++;
			}
			if (ch == ')')
				count--;

			if (count == 0) {
				rightChildString = s.substring(startIndex, index);
				break;
			}
		}

		TreeNode root = new TreeNode(rootVal);

		root.left = str2tree(leftChildString);
		root.right = str2tree(rightChildString);

		return root;
	}

	TreeNode successorNode;
	public TreeNode convertBST(TreeNode root) {
		if (root == null)
			return null;

		convertBST(root.right);

		if (successorNode != null) {
			root.val =  root.val + successorNode.val;
		}

		successorNode = root;

		convertBST(root.left);

		return root;
	}

	int numMoves;
	public int distributeCoins(TreeNode root) {
		numMoves = 0;
		computeCoins(root);
		return numMoves;
	}

	public int computeCoins(TreeNode root) {
		if (root == null)
			return 0;

		int left  = computeCoins(root.left);
		int right  = computeCoins(root.right);
		numMoves += Math.abs(left) + Math.abs(right);

		return root.val + left + right - 1;
	}

	public boolean isCompleteTree(TreeNode root) {
		boolean isEnd = false;

		if (root == null)
			return true;

		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);

		while(!queue.isEmpty()) {
			TreeNode temp = queue.poll();

			if (temp == null) {
				isEnd = true;
			} else {
				if (isEnd)
					return false;

				queue.offer(temp.left);
				queue.offer(temp.right);
			}
		}

		return true;
	}

	int maxWidth;

	public int widthOfBinaryTree(TreeNode root) {
		Map<Integer, Integer> lmap = new HashMap<>();
		Map<Integer, Integer> rmap = new HashMap<>();
		populateWidthMap(root,lmap, rmap,  0, 1);

		Integer max = null;
		for(int key : lmap.keySet()) {
			int val = ( rmap.get(key) - lmap.get(key));
			if (max == null) {
				max = val;
			} else {
				max = Math.max(max, val);
			}
		}

		return max;
	}

	public void populateWidthMap(TreeNode root, Map<Integer, Integer> leftMap, Map<Integer, Integer> rightMap, int level, int dir) {
		if (root == null)
			return;

		Integer val = leftMap.get(root);
		if (val == null) {
			leftMap.put(level, dir);
		} else {
			if (dir < val) {
				leftMap.put(level, dir);
			}
		}

		val = rightMap.get(root);
		if (val == null) {
			rightMap.put(level, dir);
		} else {
			if (dir > val) {
				rightMap.put(level, dir);
			}
		}

		populateWidthMap(root.left, leftMap, rightMap, level + 1, 2*dir);
		populateWidthMap(root.right, leftMap, rightMap,  level + 1, 2*dir + 1);
	}

	public int diameterOfBinaryTree(TreeNode root) {
		if (root == null)
			return 0;

		return Math.max(Math.max(diameterOfBinaryTree(root.left), diameterOfBinaryTree(root.right)), getHeight(root.left) + getHeight(root.right) + 1);
	}

	public int[][] insert(int[][] intervals, int[] newInterval) {

		List<int[]> result = new ArrayList<>();

		for(int[] interval : intervals) {

			if (interval[1] < newInterval[0]) {
				result.add(interval);
			} else if (newInterval[1] < interval[0]) {
				result.add(newInterval);
			} else {
				newInterval[0] = Math.min(newInterval[0], interval[0]);
				newInterval[1] = Math.min(newInterval[1], interval[1]);
			}
		}

		result.add(newInterval);

		return result.toArray(new int[result.size()][]);

	}

	public boolean flipEquiv(TreeNode root1, TreeNode root2) {

		if (root1 == null && root2 == null)
			return true;

		if (root1 == null || root2 == null)
			return false;

		if (root1.val !=  root2.val)
			return false;

		return (flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right)) || (flipEquiv(root1.right, root2.left) && flipEquiv(root1.left, root2.right));
	}

	public List<String> binaryTreePaths(TreeNode root) {
		if (root == null) {
			return new ArrayList<>();
		}

		List<String> result = new ArrayList<>();

		populateBinaryTree(root, result, "");

		return result;
	}

	private void populateBinaryTree(TreeNode root, List<String> result, String path) {

		if (root == null) {
			result.add(path.toString());
		}

		populateBinaryTree(root.left, result, ("".equals(path)?"":"->") + root.val);
		populateBinaryTree(root.right, result, ("".equals(path)?"":"->") + root.val);
	}

	public List<List<Integer>> verticalOrder(TreeNode root) {
		Map<Integer, Map<Integer, Integer>> map = new TreeMap<>();
		populateTreeMap(root, map, 0,0);

		List<List<Integer>> result = new ArrayList<>();

		for(Map<Integer, Integer> map1 : map.values()) {
			result.add(new ArrayList<>(map1.values()));
		}

		return result;
	}

	private void populateTreeMap(TreeNode root, Map<Integer, Map<Integer, Integer>> map, int xAxisVal, int level) {

		if (root == null)
			return;

		Map<Integer, Integer> list = map.get(xAxisVal);
		if (list == null) {
			list = new HashMap<>();
			map.put(xAxisVal, list);
		}

		list.put(level, root.val);

		populateTreeMap(root.left, map, xAxisVal-1, level + 1);
		populateTreeMap(root.right, map, xAxisVal+1, level + 1);
	}

	public boolean isSymmetric(TreeNode root) {
		if (root == null) {
			return true;
		}

		return areEqual(root.left, root.right);
	}

	public static boolean areEqual(TreeNode root1, TreeNode root2) {
		if (root1 == null && root2 == null) {
			return true;
		}

		if (root1 == null || root2 == null) {
			return false;
		}

		return root1.val == root2.val && areEqual(root1.left, root2.left) && areEqual(root1.right, root2.right);
	}

	public boolean hasPathSum(TreeNode root, int targetSum) {
		if (root == null) {
			return false;
		}

		if (root.left == null && root.right == null) {
			return targetSum == root.val;
		}

		return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
	}

	public List<List<Integer>> levelOrder(TreeNodeN root) {
		Queue<TreeNodeN> queue = new LinkedList<>();

		List<List<Integer>> result = new ArrayList<>();
		List<Integer> levelNodes = new ArrayList<>();

		if (root == null) {
			return result;
		}

		queue.add(root);
		queue.add(null);

		while(!queue.isEmpty()) {

			TreeNodeN node = queue.poll();

			if (node == null) {

				result.add(levelNodes);
				levelNodes = new ArrayList<>();

				if (queue.isEmpty()) {
					break;
				}

				queue.add(null);
			} else {
				levelNodes.add(node.val);

				for(TreeNodeN child : node.children) {
					if (child != null) {
						queue.add(child);
					}
				}
			}

		}

		return result;

	}
}
