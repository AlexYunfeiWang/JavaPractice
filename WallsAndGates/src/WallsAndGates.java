import java.util.LinkedList;
import java.util.Queue;

class Point {
    int x;
    int y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
};
public class WallsAndGates {
    int[] dx = new int[]{0,1,0,-1};
    int[] dy = new int[]{1,0,-1,0};
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0].length == 0) {
            return;
        }
        Queue<Point> queue = new LinkedList<>();
        for (int i = 0; i < rooms.length; ++i) {
            for (int j = 0; j < rooms[0].length; ++j) {
                if (rooms[i][j] == 0) {
                    queue.offer(new Point(i,j));
                }
            }
        }

        while(!queue.isEmpty()) {
            Point cur = queue.poll();
            for (int i = 0; i < 4; ++i) {
                int xx = cur.x + dx[i];
                int yy = cur.y + dy[i];
                Point next = new Point(xx, yy);
                if (isValid(next, rooms) && rooms[xx][yy] == Integer.MAX_VALUE) {
                    rooms[xx][yy] = 1 + rooms[cur.x][cur.y];
                    queue.offer(next);
                }
            }
        }
    }

    public boolean isValid(Point p, int[][] rooms) {
        return p.x >= 0 && p.x < rooms.length && p.y >= 0 && p.y < rooms[0].length;
    }
}
