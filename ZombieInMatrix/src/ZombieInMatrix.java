import java.util.LinkedList;
import java.util.Queue;

/*
Given a 2D grid, each cell is either a wall 2, a zombie 1 or
people 0 (the number zero, one, two).Zombies can turn the nearest
people(up/down/left/right) into zombies every day, but can not
through wall. How long will it take to turn all people into
zombies? Return -1 if can not turn all people into zombies.

Example
Example 1:

Input:
[[0,1,2,0,0],
 [1,0,0,2,1],
 [0,1,0,0,0]]
Output:
2
Example 2:

Input:
[[0,0,0],
 [0,0,0],
 [0,0,1]]
Output:
4
 */
class Cell {
    int x;
    int y;
    Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
public class ZombieInMatrix {
    /**
     * @param grid: a 2D integer grid
     * @return: an integer
     */
    private int m = 0;
    private int n = 0;
    private final int[][] directions = {{0,1},{0,-1},{1,0},{-1,0}};

    public int zombie(int[][] grid) {
        // write your code here
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        m = grid.length;
        n = grid[0].length;

        int days = turnPeopleToZombie(grid);

        boolean survivors = checkSurvivors(grid);

        return survivors ? -1 : days;
    }

    private int turnPeopleToZombie(int[][] grid) {
        Queue<Cell> zombieQueue = new LinkedList<>();

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    zombieQueue.offer(new Cell(i,j));
                }
            }
        }

        return BFS(grid, zombieQueue);
    }

    private int BFS(int[][] grid, Queue<Cell> zombieQueue) {
        int days = 0;

        while(!zombieQueue.isEmpty()) {
            int size = zombieQueue.size();
            days++;
            for (int i = 0; i < size; ++i) {
                Cell cur = zombieQueue.poll();
                for (int[] dir : directions) {
                    Cell neighbor = new Cell(cur.x + dir[0], cur.y + dir[1]);
                    if (isValid(neighbor) && grid[neighbor.x][neighbor.y] == 0) {
                        grid[neighbor.x][neighbor.y] = 1;
                        zombieQueue.offer(neighbor);
                    }
                }
            }

        }
        return days-1;
    }

    private boolean isValid(Cell cell) {
        return cell.x >= 0 && cell.x < m && cell.y >= 0 && cell.y < n;
    }

    private boolean checkSurvivors(int[][] grid) {
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
