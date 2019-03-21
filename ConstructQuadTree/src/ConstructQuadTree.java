class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;

    public Node() {}

    public Node(boolean _val,boolean _isLeaf,Node _topLeft,Node _topRight,Node _bottomLeft,Node _bottomRight) {
        val = _val;
        isLeaf = _isLeaf;
        topLeft = _topLeft;
        topRight = _topRight;
        bottomLeft = _bottomLeft;
        bottomRight = _bottomRight;
    }
};
public class ConstructQuadTree {
    public Node construct(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return null;
        }
        return helper(grid, 0, 0, grid.length);
    }

    public Node helper(int[][] grid, int x, int y, int len) {
        if (len == 1) {
            return new Node(grid[x][y] != 0, true, null, null, null, null);
        }
        Node root = new Node();
        Node topLeft = helper(grid, x, y, len/2);
        Node topRight = helper(grid, x, y+len/2, len/2);
        Node bottomLeft = helper(grid, x+len/2, y, len/2);
        Node bottomRight = helper(grid, x+len/2, y+len/2, len/2);

        if (topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf
                && topLeft.val == topRight.val && topRight.val == bottomLeft.val && bottomLeft.val == bottomRight.val) {
            root.isLeaf = true;
            root.val = topLeft.val;
        }
        else {
            root.topLeft = topLeft;
            root.topRight = topRight;
            root.bottomLeft = bottomLeft;
            root.bottomRight = bottomRight;
        }
        return root;
    }
}
