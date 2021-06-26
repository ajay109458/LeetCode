package prepcoding;

import tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.*;

public class BinaryTree {

    private BinaryTree() {

    }

    public static int sizeOfTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return 1 + sizeOfTree(root.left) + sizeOfTree(root.right);
    }

    public static int sumOfTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return root.val + sumOfTree(root.left) + sumOfTree(root.right);
    }

   public static int heightOfTree(TreeNode root){
        if (root == null)
            return 0;

        return 1 + Math.max(heightOfTree(root.left), heightOfTree(root.right));
   }


   public static Integer maxOfTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        Integer leftMax = maxOfTree(root.left);
        Integer rightMax = maxOfTree(root.right);

        Integer max = root.val;

        if (leftMax != null) {
            max = Math.max(leftMax, max);
        }

        if (rightMax != null) {
            max = Math.max(rightMax, max);
        }

        return max;
   }


   public static void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        inOrder(root.left);
        System.out.println(root.val);
        inOrder(root.right);
   }

   public static void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        System.out.println(root.val);
        preOrder(root.left);
        preOrder(root.right);
   }

   public static void postOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.val);
   }

    public static void levelOrderTraversal(TreeNode root) {
        if (root == null)
            return;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);

        while(!queue.isEmpty()) {
            TreeNode rem = queue.remove();

            if (rem == null) {

                if (queue.isEmpty()) {
                    break;
                } else {
                    queue.add(null);
                }

            } else {
                System.out.println(rem.val);
                if (root.left != null) {
                    queue.add(root.left);
                }

                if (root.right != null) {
                    queue.add(root.right);
                }
            }
        }
    }

    public static List<TreeNode> findRootToNodePath(TreeNode root, TreeNode node) {
        List<TreeNode> result = new ArrayList<>();
        if (root == null)
            return result;

        populateNodeRootPath(root, node, result);
        return result;
    }

    public static boolean populateNodeRootPath(TreeNode root, TreeNode node, List<TreeNode> list) {

        if (root == null) {
            return false;
        }

        list.add(node);

        if (root == node) {
            return true;
        }

        if (populateNodeRootPath(root.left, node, list)) {
            return true;
        }

        if (populateNodeRootPath(root.right, node, list)) {
            return true;
        }

        list.remove(node);

        return false;
    }

    public static void printKLevelDown(TreeNode root, int k) {

        if (root == null) {
            return;
        }

        if (k == 0) {
            System.out.println(root.val);
        }

        printKLevelDown(root.left, k-1);
        printKLevelDown(root.right, k-1);
    }

    public static void printKLevelDown(TreeNode root, int k, TreeNode blockerNode) {

       if (root == null || root == blockerNode) {
           return;
       }

       if (k == 0) {
           System.out.println(root.val);
       }

       printKLevelDown(root.left, k -1, blockerNode);
       printKLevelDown(root.right, k- 1, blockerNode);
    }

    /*
    * 1. Populate root to node path
    * 2. Reverse order from each node print (k-i) distance nodes
    * 3. Add blockers
    * */
    public static void printNodesKDistanceAway(TreeNode root, TreeNode node, int k) {
       List<TreeNode> list = new ArrayList<>();
       populateNodeRootPath(root, node, list);

       int l = 0;
       for(int i = list.size() - 1; i >= 0 && k - l >= 0; i--) {
           printKLevelDown(list.get(i), k - l, (i+1) < list.size() ? list.get(i) : null);
           l++;
       }
    }

    /**
     * Consider all path from root to leaf whose element sum is in the range
     *
     */
    public static void printRootToLeafPathInRange(TreeNode root, String path, int sum, int min, int max) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            path += root.val;
            sum += root.val;
            if (sum >= min && sum <= max) {
                System.out.println(path);
            }
            return;
        }

        printRootToLeafPathInRange(root.left, path + root.val, sum + root.val, min, max);
    }

    public static void transformToLeftClonedTree(TreeNode root) {
        if (root == null) {
            return ;
        }

        transformToLeftClonedTree(root.left);
        transformToLeftClonedTree(root.right);

        TreeNode clone = new TreeNode(root.val);
        clone.left = root.left;
        root.left = clone;
    }

    public static TreeNode normalizeALeftCloneTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode temp = root.left;
        root.left = temp;
        temp.left = null;

        normalizeALeftCloneTree(root.left);
        normalizeALeftCloneTree(root.right);
        return root;
    }

    public static void printSingleChildNodes(TreeNode root) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right != null) {
            System.out.println(root.val);
        } else if (root.left != null && root.right == null) {
            System.out.println(root.val);
        }

        printSingleChildNodes(root.left);
        printSingleChildNodes(root.right);
    }

    public static TreeNode removeLeaves(TreeNode root) {
        if (root == null) {
            return null;
        }

        if (root.left == null && root.right == null) {
            return null;
        }

        root.left = removeLeaves(root.left);
        root.right = removeLeaves(root.right);

        return root;
    }

    /**
     * Why logic:
     *  Left deepest + Right Deepest +
     *
     * Why inefficient
     * - Recursion of height in the recursion of diameter
     *
     *  Time Complexity = O(N*N)
     */
    public static int getDiameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int pathSize = 1 + heightOfTree(root.left) + heightOfTree(root.right);

        return Math.max(pathSize, Math.max(getDiameterOfBinaryTree(root.left), getDiameterOfBinaryTree(root.right)));
    }

    static class DiaPair {
        int height;
        int diameter;

        public DiaPair(int height, int diameter) {
            this.height = height;
            this.diameter = diameter;
        }
    }

    public static DiaPair getDiameterOfBinaryTree2(TreeNode root) {
        if (root == null) {
            return new DiaPair(0, 0);
        }

        DiaPair leftDiaPair = getDiameterOfBinaryTree2(root.left);
        DiaPair rightDiaPair = getDiameterOfBinaryTree2(root.left);

        int pathSize = 1 + leftDiaPair.height + rightDiaPair.height;
        int height = 1 + Math.max(leftDiaPair.height, rightDiaPair.height);

        return new DiaPair(height, Math.max(pathSize, Math.max(leftDiaPair.diameter, rightDiaPair.diameter)));
    }

    public static boolean isBalancedBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        int leftHeight = heightOfTree(root.left);
        int rightHeight = heightOfTree(root.right);

        return Math.abs(leftHeight - rightHeight) <= 1
                && isBalancedBST(root.left)
                && isBalancedBST(root.right);

    }

    static class BSTPair {
        boolean isBST;
        int min;
        int max;
        TreeNode largestBSTRoot;
        int largestBSTSize;
    }

    /**
     * isBST
     * Largest BST
     * @param root
     * @return
     */
    public static BSTPair isBST(TreeNode root) {
        if (root == null) {
            BSTPair pair = new BSTPair();
            pair.min = Integer.MAX_VALUE;
            pair.max = Integer.MIN_VALUE;
            pair.isBST = true;
            pair.largestBSTRoot = null;
            pair.largestBSTSize = 0;
        }

        BSTPair lp = isBST(root.left);
        BSTPair rp = isBST(root.right);

        BSTPair rootPair = new BSTPair();

        rootPair.isBST = lp.isBST && rp.isBST && (root.val >= lp.max && root.val <= rp.min);
        rootPair.max = Math.max(root.val, Math.max(lp.max, rp.max));
        rootPair.min = Math.min(root.val, Math.min(lp.min, rp.min));

        if (rootPair.isBST) {
            rootPair.largestBSTRoot = root;
            rootPair.largestBSTSize = 1 + lp.largestBSTSize + rp.largestBSTSize;
        } else if (lp.largestBSTSize > rp.largestBSTSize){
            rootPair.largestBSTSize = lp.largestBSTSize;
            rootPair.largestBSTRoot = lp.largestBSTRoot;
        } else {
            rootPair.largestBSTSize = rp.largestBSTSize;
            rootPair.largestBSTRoot = rp.largestBSTRoot;
        }

        return rootPair;
    }

}
