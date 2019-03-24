class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class RecoverBinarySearchTree {
    /*
    imagine the following correct BST:
    5
   / \
  3   6
  however we swap 2 of the elements in the tree, the following inorder traversal will work
    */

    TreeNode prevElement = new TreeNode(Integer.MIN_VALUE);
    TreeNode firstNode = null;
    TreeNode secondNode = null;
    public void recoverTree(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root);

        int tmp = firstNode.val;
        firstNode.val = secondNode.val;
        secondNode.val = tmp;
    }

    public void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);

        if (firstNode == null && prevElement.val >= root.val) {
            firstNode = prevElement;
        }
        if (firstNode != null && prevElement.val >= root.val) {
            secondNode = root;
        }
        prevElement = root;

        inorder(root.right);
    }
}
