public class UniquePaths2 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] pathNum = new int[m][n];

        for (int i = 0; i < m; ++i) {
            if (obstacleGrid[i][0] == 1) {
                break;
            }
            pathNum[i][0] = 1;
        }

        for (int j = 0; j < n; ++j) {
            if (obstacleGrid[0][j] == 1) {
                break;
            }
            pathNum[0][j] = 1;
        }

        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                if (obstacleGrid[i][j] == 0) {
                    pathNum[i][j] = pathNum[i-1][j] + pathNum[i][j-1];
                }
            }
        }
        return pathNum[m-1][n-1];
    }
}
