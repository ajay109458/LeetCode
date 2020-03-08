

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

	
}
