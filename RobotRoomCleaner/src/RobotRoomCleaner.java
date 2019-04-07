import java.util.HashSet;
import java.util.Set;

/**
 * // This is the robot's control interface.
 * // You should not implement it, or speculate about its implementation
 * interface Robot {
 *     // Returns true if the cell in front is open and robot moves into the cell.
 *     // Returns false if the cell in front is blocked and robot stays in the current cell.
 *     public boolean move();
 *
 *     // Robot will stay in the same cell after calling turnLeft/turnRight.
 *     // Each turn will be 90 degrees.
 *     public void turnLeft();
 *     public void turnRight();
 *
 *     // Clean the current cell.
 *     public void clean();
 * }
 */
class RobotRoomCleaner {
    //the direction needs to follow a certain pattern: up, right, down, left
    private final int[][] directions = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};

    class Node {
        int row;
        int col;
        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            Node node = (Node)o;
            if (node.row == row && node.col == col) {
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            int hash = 17;
            hash = hash*31 + row;
            hash = hash*31 + col;
            return hash;
        }
    }

    private void find(Robot robot, Set<Node> visited, int currentDir, int row, int col) {
        Node curNode = new Node(row, col);
        visited.add(curNode);
        robot.clean();
        for (int i = 0; i < 4; ++i) {
            int nextDir = (currentDir+i)%4;
            int[] nextCell = directions[nextDir];
            int nextRow = row + nextCell[0];
            int nextCol = col + nextCell[1];
            Node nextNode = new Node(nextRow, nextCol);
            if (!visited.contains(nextNode) && robot.move()) {
                find(robot, visited, nextDir, nextRow, nextCol);
                /*
                this is the backtracking part
                after moving to next cell on a direction,
                the robot turns 180 degrees and move back to the original cell
                then the robot turns 90 degrees to the left for next direction

                we can reduce 1 turn per valid move by noticing that we need to
                move to the next position after the loop, so we don't need to backtrack
                completely. We can backtrack to the next position by turning 90 degrees
                back instead of 180. Then we only turn right if it wasn't a valid move.
                */
                robot.turnLeft();
                robot.turnLeft();
                robot.move();
                robot.turnLeft();
            }
            else {
                robot.turnRight();
            }
        }
    }

    public void cleanRoom(Robot robot) {
        Set<Node> visited = new HashSet<>();
        find(robot, visited, 0, 0, 0);
    }
}