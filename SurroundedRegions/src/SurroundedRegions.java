public class SurroundedRegions {
    int m = 0;
    int n = 0;
    private final int[][] directions = new int[][]{{0,1},{0,-1}, {1,0}, {-1,0}};
    public void solve(char[][] board) {
        // write your code here
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        m = board.length;
        n = board[0].length;

        markNonSurroundedRegions(board);

        labelSurroundedRegions(board);
    }

    private void markNonSurroundedRegions(char[][] board) {
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if ((i == 0 || j == 0 || i == m-1 || j == n-1) && board[i][j] == 'O') {
                    DFS(board, i, j);
                }
            }
        }
    }

    private void DFS(char[][] board, int i, int j) {
        if (isValid(i,j) && board[i][j] == 'O') {
            board[i][j] = '*';
            for (int[] dir : directions) {
                DFS(board, i+dir[0], j+dir[1]);
            }
        }
    }

    private void labelSurroundedRegions(char[][] board) {
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                else if (board[i][j] == '*') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private boolean isValid(int i, int j) {
        return i >= 0 && i < m && j >= 0 && j < n;
    }
}
