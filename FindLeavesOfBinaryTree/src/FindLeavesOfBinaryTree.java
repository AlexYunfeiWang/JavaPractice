import java.util.ArrayList;
import java.util.List;

/*
Given a binary tree, collect a tree's nodes as if you were doing
this: Collect and remove all leaves, repeat until the tree is empty.

Example
Example1
Input: {1,2,3,4,5}
Output: [[4, 5, 3], [2], [1]].
Explanation:

    1
   / \
  2   3
 / \
4   5
 */

class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class FindLeavesOfBinaryTree {
    public List<List<Integer>> findLeaves(TreeNode root) {
        // write your code here
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        postOrder(root, res);
        return res;
    }

    private int postOrder(TreeNode root, List<List<Integer>> res) {
        if (root == null) {
            return 0;
        }
        int leftHeight = postOrder(root.left, res);
        int rightHeight = postOrder(root.right, res);

        int index = Math.max(leftHeight, rightHeight);

        if (res.size() <= index) {
            res.add(new ArrayList<>());
        }
        res.get(index).add(root.val);
        return index+1;
    }
}
