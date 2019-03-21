public class LongestIncreasingPathInAMatrix {
    int[] dx = new int[]{0,1,0,-1};
    int[] dy = new int[]{1,0,-1,0};
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int max = 1;
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] dp = new int[m][n];

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int len = DFS(matrix, dp, i, j, m, n);
                max = Math.max(max, len);
            }
        }
        return max;
    }

    public int DFS(int[][] matrix, int[][] dp, int x, int y, int m, int n) {
        if (dp[x][y] != 0) {
            return dp[x][y];
        }
        int max = 1;
        for (int i = 0; i < 4; ++i) {
            int xx = x + dx[i];
            int yy = y + dy[i];
            if (isValid(xx, yy, m, n) && matrix[xx][yy] > matrix[x][y]) {
                int len = 1 + DFS(matrix, dp, xx, yy, m, n);
                max = Math.max(len, max);
            }
        }
        dp[x][y] = max;
        return max;
    }

    public boolean isValid(int x, int y, int m, int n) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
}
