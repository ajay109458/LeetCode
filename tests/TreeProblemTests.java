import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tree.TreeNode;
import tree.TreeProblems;

public class TreeProblemTests {

	@BeforeEach
	void init() {
		System.out.println("------------------------------");
	}

	@Test
	public void checkMaxDepthOfTree() {

		TreeNode root = TreeCreator.createTreeForMaxDepth();
		int actualDepth = TreeProblems.getMaxDepth(root);
		int expectedDepth = 3;

		System.out.println("Actual depth of the tree : " + actualDepth);

		assertEquals(expectedDepth, actualDepth);
	}

	@Test
	public void validateInOrderPreOrderToTree() {

		int[] inOrder = { 4, 2, 1, 3 };
		int[] preOrder = { 1, 2, 3, 4 };

		TreeNode root = TreeProblems.constructTreeFromInorderPreorder(inOrder, preOrder);

		TreeProblems.printInOrder(root);
		System.out.println();
	}

	@Test
	public void validateInOrderPostOrderToTree() {

		int[] inOrder = { 4, 2, 1, 3 };
		int[] postOrder = { 4, 2, 3, 1 };

		TreeNode root = TreeProblems.constructTreeFromInorderPreorder(inOrder, postOrder);

		TreeProblems.printInOrder(root);
		System.out.println();
	}

	@Test
	public void printLevelOrderTraversalBottomUp() {

		TreeNode root = TreeCreator.createTreeForMaxDepth();

		TreeProblems.printBottomUpLevelOrderTraversal(root);
	}

	@Test
	public void validateConvertSortedArrayToBST() {

		int[] arr = { 1, 2, 3, 4, 5, 6, 7 };

		TreeNode root = TreeProblems.convertSortedArrayToBST(arr);

		TreeProblems.printInOrder(root);
		System.out.println();

	}

	@Test
	public void validateHeightBalanced() {

		TreeNode root = TreeCreator.createTreeForMaxDepth();

		boolean isBalanced = TreeProblems.isHeightBalanced(root);

		System.out.println("Is balanced tree : " + isBalanced);

		assertEquals(true, isBalanced);
	}
	
	@Test
	public void checkRootToLeafSum() {
		
		TreeNode root = TreeCreator.createTreeForMaxDepth();
		
		boolean isRootToLeafSum = TreeProblems.checkRootToLeafSum(root, 10);
		
		System.out.println("Root to leaf sum : " + isRootToLeafSum);
		
	}
	
	@Test
	public void printPathRootToLeafPathSum() {
		
		TreeNode root = TreeCreator.createTreeRootToLeafSum();
		
		TreeProblems.printAllRootToLeafPathWithGivenSum(root, 15);
		
	}

}
