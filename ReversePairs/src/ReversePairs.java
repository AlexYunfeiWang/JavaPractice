class Node {
    int val, cnt;
    Node left, right;

    Node(int val) {
        this.val = val;
        this.cnt = 1;
    }
}
public class ReversePairs {
    /*
    node.cnt is the number of nodes that are greater or equal to the node

    so for insert: if insert value is greater or equal to the root, root.cnt++,
    and do DFS on root.right.
    if insert value is less than root, do DFS on root.left

    for search: if search value is less than root, then return root.cnt + DFS
    on root.left.
    if search value is greater or equal to root, then do DFS on root.right

    for a number nums[i] in nums, we search for anything greater than nums[i]*2
    in the existing tree(which contains all the elements before i, and update
    the total count, then we insert nums[i] in the tree too)

    */
    public int reversePairs(int[] nums) {
        int res = 0;
        Node root = null;

        for (int ele : nums) {
            res += search(root, 2L * ele + 1);
            root = insert(root, ele);
        }

        return res;
    }
    private int search(Node root, long val) {
        if (root == null) {
            return 0;
        } else if (val == root.val) {
            return root.cnt;
        } else if (val < root.val) {
            return root.cnt + search(root.left, val);
        } else {
            return search(root.right, val);
        }
    }

    private Node insert(Node root, int val) {
        if (root == null) {
            root = new Node(val);
        } else if (val == root.val) {
            root.cnt++;
        } else if (val < root.val) {
            root.left = insert(root.left, val);
        } else {
            root.cnt++;
            root.right = insert(root.right, val);
        }

        return root;
    }
}
