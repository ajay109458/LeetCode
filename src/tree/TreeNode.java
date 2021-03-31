package tree;

public class TreeNode {

	public int val;
	public TreeNode left;
	public TreeNode right;
	public TreeNode next;
	
	public TreeNode() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TreeNode(int data) {
		super();
		this.val = data;
	}

	@Override
	public String toString() {
		return "TreeNode [data=" + val + "]";
	}
}
