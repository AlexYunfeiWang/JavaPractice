public class Minesweeper {
    int[] dx = {-1, 0, 1, -1, 1, 0, 1, -1};
    int[] dy = {-1, 1, 1, 0, -1, -1, 0, 1};
    public char[][] updateBoard(char[][] board, int[] click) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return board;
        }
        int x = click[0];
        int y = click[1];
        if (board[x][y] == 'M') {
            board[x][y] = 'X';
            return board;
        }
        DFS(board, x, y);
        return board;
    }

    private void DFS(char[][] board, int x, int y) {
        if (!isValid(board, x, y) || board[x][y] != 'E') {
            return;
        }

        /*
        board[x][y] itself cannot be bomb, because it's already labeled 'E'

        so we need to find out how many bombs are there around (x,y)

        1. If bomb number is 0, then label (x,y) to be 'B' and continue to expand
        2. If there are bombs, then label (x,y) to be the number of bombs around
        */
        int num = getBombNumber(board, x, y);

        if (num == 0) {
            board[x][y] = 'B';
            for (int i = 0; i < 8; ++i) {
                int xx = x + dx[i];
                int yy = y + dy[i];
                DFS(board, xx, yy);
            }
        }
        else {
            board[x][y] = (char)(num + '0');
        }
    }

    private boolean isValid(char[][] board, int x, int y) {
        return x >= 0 && x < board.length && y >= 0 && y < board[0].length;
    }

    private int getBombNumber(char[][] board, int x, int y) {
        int num = 0;
        for (int i = 0; i < 8; ++i) {
            int xx = x + dx[i];
            int yy = y + dy[i];
            if (isValid(board, xx, yy)) {
                if (board[xx][yy] == 'M' || board[xx][yy] == 'X') {
                    num++;
                }
            }

        }
        return num;
    }
}
