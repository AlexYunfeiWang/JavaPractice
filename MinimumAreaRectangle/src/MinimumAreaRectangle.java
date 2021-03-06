import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MinimumAreaRectangle {
    public int minAreaRect(int[][] points) {
        if (points == null || points.length == 0 || points[0].length == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] p : points) {
            if (!map.containsKey(p[0])) {
                map.put(p[0], new HashSet<Integer>());
            }
            map.get(p[0]).add(p[1]);
        }

        for (int[] p1 : points) {
            for (int[] p2 : points) {
                //if two points are on the same line, continue
                if (p1[0] == p2[0] || p1[1] == p2[1]) {
                    continue;
                }
                //find two other diagonal points
                if (map.get(p1[0]).contains(p2[1]) && map.get(p2[0]).contains(p1[1])) {
                    min = Math.min(min, Math.abs(p2[1] - p1[1]) * Math.abs(p2[0] - p1[0]));
                }
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
