/*
Given a Binary Search Tree (BST), convert it to a Greater Tree
such that every key of the original BST is changed to the original
key plus sum of all keys greater than the original key in BST.

Example
Example 1:

Input :
              5
            /   \
           2     13
Output :
             18
            /   \
          20     13
 */
class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
 }
public class ConvertBSTToGreaterTree {
    int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        // write your code here
        if (root == null) {
            return root;
        }
        reverseInOrder(root);
        return root;
    }

    private void reverseInOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        reverseInOrder(root.right);
        sum += root.val;
        root.val = sum;
        reverseInOrder(root.left);
    }
}
