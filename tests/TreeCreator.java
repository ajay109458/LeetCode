

import tree.TreeNode;

public class TreeCreator {

	public static TreeNode createTreeForMaxDepth() {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		return root;
	}
	
	
	public static TreeNode createTreeRootToLeafSum() {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(20);
		root.right = new TreeNode(3);
		root.right.left = new TreeNode(4);
		root.right.right = new TreeNode(7);
		root.right.left.left = new TreeNode(6);
		root.right.left.right = new TreeNode(7);
		root.right.right.left = new TreeNode(4);
		root.right.right.right = new TreeNode(9);
		return root;
	}

	
}
