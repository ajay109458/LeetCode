

import tree.TreeNode;

public class TreeCreator {

	public static TreeNode createTreeForMaxDepth() {
		TreeNode root = new TreeNode(10);
		root.left = new TreeNode(8);
		root.right = new TreeNode(2);
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(5);
		return root;
	}

	
}
