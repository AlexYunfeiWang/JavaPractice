import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class PrintBinaryTree {
    public List<List<String>> printTree(TreeNode root) {
        List<List<String>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        int row = getHeight(root);
        int col = (int)Math.pow(2, row) - 1;

        for (int i = 0; i < row; ++i) {
            res.add(new ArrayList<String>());
            for (int j = 0; j < col; ++j) {
                res.get(i).add("");
            }
        }

        Queue<TreeNode> queue = new LinkedList<>();
        Queue<int[]> indexQ = new LinkedList<>();

        queue.offer(root);
        indexQ.offer(new int[]{0, col-1});

        int height = -1;

        while(!queue.isEmpty()) {
            height++;
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                TreeNode cur = queue.poll();
                int[] range = indexQ.poll();

                if (cur == null) {
                    continue;
                }

                int left = range[0];
                int right = range[1];
                int mid = left + (right-left)/2;

                res.get(height).set(mid, String.valueOf(cur.val));

                queue.offer(cur.left);
                queue.offer(cur.right);
                indexQ.offer(new int[]{left, mid-1});
                indexQ.offer(new int[]{mid+1, right});
            }
        }
        return res;
    }

    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }
}
