class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) {
        this.val = val;
    }
}
public class CountCompleteTreeNodes {
    public int countNodes(TreeNode root) {
        int h = treeHeight(root);
        if (h == 0) {
            return 0;
        }
        /*
        if right tree height is h-1, that means the last node is on right tree
        and since left tree is a full tree, the total number of left tree is 2^(h-1)-1
        plus the root itself, it's 2^(h-1)-1+1
        then we add the coundNodes(root.right) which is the node number for right tree
        */
        if (treeHeight(root.right) == h-1) {
            return (1 << h-1)-1+1 + countNodes(root.right);
        }
        /*
        if right tree height is NOT h-1, that means the last node is NOT on right tree
        which means right tree is actually missing 1 or more nodes
        but it also means on h-2 level, right tree is full
        so right tree node number is 2^(h-2)-1, plus the root itself, it's 2^(h-2)-1+1
        and since left tree is a full tree, so we add the coundNodes(root.left)
        which is the node number for left tree
        */
        else {
            return (1 << h-2)-1+1 + countNodes(root.left);
        }
    }

    private int treeHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(treeHeight(root.left), treeHeight(root.right));
    }
}
