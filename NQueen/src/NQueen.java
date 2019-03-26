import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueen {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }
        char[][] board = new char[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(board[i], '.');
        }

        DFS(0, n, board, res);

        return res;
    }

    private void DFS(int row, int n, char[][] board, List<List<String>> res) {
        if (row == n) {
            addToRes(res, board);
            return;
        }
        for (int col = 0; col < n; ++col) {
            if (isValid(board, row, col, n)) {
                board[row][col] = 'Q';
                DFS(row+1, n, board, res);
                board[row][col] = '.';
            }
        }
    }

    private boolean isValid(char[][] board, int row, int col, int n) {
        //check if the column had a queen before
        for (int i = 0; i < row; ++i) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }

        //check if the 45° diagonal had a queen before.
        for (int i = row-1, j = col-1; i >= 0 && j >= 0; --i, --j) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        //check if the 135° diagonal had a queen before.
        for (int i = row-1, j = col+1; i >= 0 && j < n; --i,++j) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    private void addToRes(List<List<String>> res, char[][] board) {
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < board.length; ++i) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < board[0].length; ++j) {
                sb.append(board[i][j]);
            }
            ans.add(sb.toString());
        }
        res.add(new ArrayList<>(ans));
    }
}
