import java.util.HashSet;
import java.util.Set;

public class MostStonesRemovedWithSameRowOrColumn {
    /*
    Idea: If two stones share same row or same col, then these 2 stones are connected.
    We call a connected graph as an island.
    One island must have at least one stone left.
    The maximum stones can be removed = stones number - islands number

    If there are 5 islands, then at least 5 stones are left,
    then the rest of the stones can be removed
    */
    public int removeStones(int[][] stones) {
        if (stones == null || stones.length == 0 || stones[0].length == 0) {
            return 0;
        }
        int numberOfIsland = 0;
        Set<int[]> visited = new HashSet<>();
        for (int[] s1 : stones) {
            if (!visited.contains(s1)) {
                DFS(s1, stones, visited);
                numberOfIsland++;
            }

        }
        return stones.length - numberOfIsland;
    }

    private void DFS(int[] s1, int[][] stones, Set<int[]> visited) {
        visited.add(s1);
        for (int[] s2 : stones) {
            if (!visited.contains(s2)) {
                //if two stones share same row or col, they're connected
                if (s1[0] == s2[0] || s1[1] == s2[1]) {
                    DFS(s2, stones, visited);
                }
            }
        }
    }
}
