import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NumberOfIslands2 {
    Set<Integer> set = new HashSet<>();
    int[][] matrix;
    int row;
    int col;
    int[] dx = {0,1,0,-1};
    int[] dy = {1,0,-1,0};

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList<>();
        if (positions == null || positions.length == 0 || positions[0].length == 0) {
            return res;
        }

        matrix = new int[m][n];
        row = m;
        col = n;

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                matrix[i][j] = -1;
            }
        }

        for (int[] p : positions) {
            int x = p[0];
            int y = p[1];

            add(x,y);
            res.add(set.size());
        }
        return res;
    }

    private void add(int x, int y) {
        matrix[x][y] = x*col + y;
        set.add(matrix[x][y]);

        for (int i = 0; i < 4; ++i) {
            int xx = x + dx[i];
            int yy = y + dy[i];

            if (xx >= 0 && xx < row && yy >= 0 && yy < col && matrix[xx][yy] != -1) {
                union(x,y,xx,yy);
            }
        }
    }

    private void union(int x, int y, int xx, int yy) {
        int find1 = find(x, y);
        int find2 = find(xx, yy);

        if (find1 != find2) {
            matrix[find2/col][find2%col] = find1;
            set.remove(find2);
        }
    }

    private int find(int x, int y) {
        if (matrix[x][y] == x*col+y) {
            return matrix[x][y];
        }
        return find(matrix[x][y]/col, matrix[x][y]%col);
    }
}
