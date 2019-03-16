import java.util.HashMap;
import java.util.List;
import java.util.*;

public class findFriend {
    public static void main(String[] args) {
        int[][] friends = new int[][]{
                {5,1},
                {2,3,9},
                {},
                {},
                {},
                {7,9},
                {},
                {},
                {3},
                {}
        };

        helper(friends);
    }

    static List<List<Integer>> res;
    public static void helper(int[][] friends) {
        if (friends == null || friends.length == 0 || friends[0].length == 0) {
            System.out.println("-1");
            return;
        }
        int min = Integer.MAX_VALUE;
        res = new ArrayList<List<Integer>>();
        List<Integer> path = new ArrayList<>();
        path.add(0);
        DFS(friends, 0, 9, path);

        List<Integer> result = null;

        for (List<Integer> candidate : res) {
            int cost = calculate(candidate);
            if (cost < min) {
                min = cost;
                result = candidate;
            }
        }
        System.out.println("min is " + min);

        for (int i = 0; i < result.size(); ++i) {
            System.out.print(result.get(i) + ",");
        }
    }

    private static void DFS(int[][] friends, int cur, int dest, List<Integer> path) {
        if (path.contains(dest)) {
            for (int i = 0; i < path.size(); ++i) {
                System.out.print(path.get(i) + ",");
            }
            System.out.println();
            res.add(new ArrayList<>(path));
            return;
        }
        int[] children = friends[cur];

        for (int i = 0; i < children.length; ++i) {
            path.add(children[i]);
            DFS(friends, children[i], dest, path);
            path.remove(path.size()-1);
        }
    }

    private static int calculate(List<Integer> path) {
        int sum = 0;
        if (path.size() < 2) {
            return sum;
        }
        for (int i = 1; i < path.size(); ++i) {
            sum += (path.get(i) - path.get(i-1)) * (path.get(i) - path.get(i-1));
        }
        return sum;
    }
}


