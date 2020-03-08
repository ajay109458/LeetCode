package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import array.ArrayHelper;

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
		System.out.print(root.data + " ");
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
		root.data = val;
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
		root.data = val;
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
				System.out.print(node.data + " ");
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
		node.data = arr[mid];
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
		
		int remainingSum = sum - root.data;
		
		return checkRootToLeafSum(root.left, remainingSum) || 
				checkRootToLeafSum(root.right, remainingSum);
		
	}
	
	public static void printAllRootToLeafPathWithGivenSum(TreeNode root, int sum) {
		_populateRootToLeafPath(root, sum, new ArrayList<TreeNode>());
	}
	
	public static void _populateRootToLeafPath(TreeNode root, int sum, List<TreeNode> list) {
		
		if (root == null) {
			return;
		}
		
		list.add(root);
		sum -= root.data;
		
		if (root.left == null && root.right == null) {
			if (sum == 0) {
				System.out.println(list);
			}
		}
		
		_populateRootToLeafPath(root.left, sum, list);
		_populateRootToLeafPath(root.right, sum, list);
		
		list.remove(root);
	}

}
