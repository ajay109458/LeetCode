package tree;

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
}
