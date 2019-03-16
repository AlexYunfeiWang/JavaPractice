import java.util.ArrayList;
import java.util.List;

public class PacificAtlanticWaterFlow {
	
	public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> res = new ArrayList<int[]>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return res;
        int[][] dirs = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (visited[i][j] == false) {
                    if (DFSPacific(i, j, dirs, matrix, visited) == true)
                        System.out.println("[" + i + "," + j + "]");
                }
                
            }
        }
        return res;
    }
    
    public boolean DFSPacific(int i, int j, int[][] dirs, int[][] matrix, boolean[][] visited) {
    	visited[i][j] = true;
        if (i == 0 || j == 0)
            return true;
        
        for (int k = 0; k < dirs.length; ++k) {
            int r = i + dirs[k][0];
            int c = j + dirs[k][1];
            if (r >= 0 && r < matrix.length && c >= 0 && c < matrix[0].length && matrix[r][c] <= matrix[i][j]) {
                if (visited[r][c] == false) {
                    if (DFSPacific(r, c, dirs, matrix, visited) == true)
                        return true;
                }
                
            }
        }
        visited[i][j] = true;
        return false;
    }

	

}
