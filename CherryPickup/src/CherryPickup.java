import java.util.Arrays;
public class CherryPickup {
    public int CherryPickup(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int n = grid.length;
        int[][][] dp = new int[n+1][n+1][n+1];

        for (int i = 0; i <= n; ++i) {
            for (int j = 0; j <= n; ++j) {
                Arrays.fill(dp[i][j], Integer.MIN_VALUE);
            }
        }

        dp[1][1][1] = grid[0][0];

        for (int x = 1; x <= n; ++x) {
            for (int y = 1; y <= n; ++y) {
                for (int xx = 1; xx <= n; ++xx) {
                    int yy = x+y-xx;

                    if (dp[x][y][xx] > 0 || yy < 1 || yy > n
                            || grid[x][y] == -1 || grid[xx][yy] == -1) {
                        continue;
                    }
                    int max = Math.max(
                      Math.max(dp[x-1][y][xx-1], dp[x-1][y][xx]),
                      Math.max(dp[x][y-1][xx-1], dp[x][y-1][xx])
                    );

                    if (max < 0) {
                        continue;
                    }

                    dp[x][y][xx] = max + grid[x-1][y-1];
                    if (x != xx) {
                        dp[x][y][xx] += grid[xx-1][yy-1];
                    }
                }
            }
        }
        return dp[n][n][n] < 0 ? 0 : dp[n][n][n];
    }
}
