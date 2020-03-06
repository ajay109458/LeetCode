import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import tree.TreeNode;
import tree.TreeProblems;

public class TreeProblemTests {

	@Test
	public void checkMaxDepthOfTree() {
		
		TreeNode root = TreeCreator.createTreeForMaxDepth();
		int actualDepth = TreeProblems.getMaxDepth(root);
		int expectedDepth = 3;
		
		assertEquals(expectedDepth, actualDepth);
	}
	
	@Test
	public void validateInOrderPreOrderToTree() {
		
		int[] inOrder = {4, 2, 1, 3};
		int[] preOrder = {1, 2, 3, 4};
		
		TreeNode root = TreeProblems.constructTreeFromInorderPreorder(inOrder, preOrder);
		
		TreeProblems.printInOrder(root);
		System.out.println();
	}
	
	@Test
	public void validateInOrderPostOrderToTree() {
		
		int[] inOrder = {4, 2, 1, 3};
		int[] postOrder = {4, 2, 3, 1};
		
		TreeNode root = TreeProblems.constructTreeFromInorderPreorder(inOrder, postOrder);
		
		TreeProblems.printInOrder(root);
		System.out.println();
	}
	
}
