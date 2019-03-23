import java.util.LinkedList;
import java.util.Queue;

class Point{
    int x;
    int y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
public class ShortestBridge {
    /*
    1. Use DFS to find the first island, mark them as 2;
    2. find all the cells with value 1, this is the second island, push all of them into queue
    3. Use BFS to find the shortest path from second island to first island
    */
    int[] dx = {0,1,0,-1};
    int[] dy = {1,0,-1,0};
    public int shortestBridge(int[][] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int m = A.length;
        int n = A[0].length;
        Queue<Point> queue = new LinkedList<>();

        boolean found = false;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (A[i][j] == 1 && !found) {
                    DFS(A, i, j);
                    found = true;
                }
                if (A[i][j] == 1 && found) {
                    queue.offer(new Point(i,j));
                }
            }
        }

        return BFS(queue, A);
    }

    private void DFS(int[][] A, int x, int y) {

        A[x][y] = 2;
        for (int i = 0; i < 4; ++i) {
            int xx = x + dx[i];
            int yy = y + dy[i];
            if (xx >= 0 && xx < A.length && yy >= 0 && yy < A[0].length && A[xx][yy] == 1) {
                DFS(A, xx, yy);
            }
        }

    }

    public int BFS(Queue<Point> queue, int[][] A) {
        int count = 0;

        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                Point cur = queue.poll();

                for (int j = 0; j < 4; ++j) {
                    Point next = new Point(cur.x + dx[j], cur.y + dy[j]);
                    if (isValid(A, next) ) {
                        if (A[next.x][next.y] == 2) {
                            return count;
                        }
                        else if (A[next.x][next.y] == 1) {
                            continue;
                        }
                        else if (A[next.x][next.y] == 0) {
                            A[next.x][next.y] = 1;
                            queue.offer(next);
                        }
                    }
                }
            }
            count++;
        }
        return -1;
    }

    private boolean isValid(int[][] A, Point p) {
        return p.x >= 0 && p.x < A.length && p.y >= 0 && p.y < A[0].length;
    }
}
