
class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
public class PathSum3 {
    int count = 0;
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return count;
        }
        preOrder(root, sum);
        return count;
    }

    public void preOrder(TreeNode root, int sum) {
        if (root == null) {
            return;
        }
        DFS(root, sum);
        preOrder(root.left, sum);
        preOrder(root.right, sum);
    }

    public void DFS(TreeNode root, int sum) {
        if (root == null) {
            return;
        }
        if (root.val == sum) {
            count++;
        }
        DFS(root.left, sum - root.val);
        DFS(root.right, sum - root.val);
    }
}
