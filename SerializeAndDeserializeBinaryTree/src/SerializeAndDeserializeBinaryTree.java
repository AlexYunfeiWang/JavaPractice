import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
public class SerializeAndDeserializeBinaryTree {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                TreeNode cur = queue.poll();
                if (cur == null) {
                    sb.append("#,");
                }
                else {
                    sb.append(cur.val + ",");
                    queue.offer(cur.left);
                    queue.offer(cur.right);
                }
            }
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        String[] arr = data.split("\\,");
        Queue<TreeNode> queue = new LinkedList<>();
        int i = 0;
        TreeNode root = new TreeNode(Integer.valueOf(arr[i]));
        queue.offer(root);

        while(!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            i++;
            if (arr[i].equals("#")) {
                cur.left = null;
            }
            else {
                cur.left = new TreeNode(Integer.valueOf(arr[i]));
                queue.offer(cur.left);
            }

            i++;
            if (arr[i].equals("#")) {
                cur.right = null;
            }
            else {
                cur.right = new TreeNode(Integer.valueOf(arr[i]));
                queue.offer(cur.right);
            }
        }
        return root;
    }
}
