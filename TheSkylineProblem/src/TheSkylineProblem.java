import java.util.ArrayList;
import java.util.List;

public class TheSkylineProblem {
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new ArrayList<>();
        if (buildings == null || buildings.length == 0 || buildings[0].length == 0) {
            return res;
        }
        int size = 0;
        for (int[] b : buildings) {
            size = Math.max(size, b[1]);
        }
        int[] heights = new int[size+1];

        int[] prev = buildings[0];

        for (int i = prev[0]; i < prev[1]; ++i) {
            heights[i] = prev[2];
        }

        for (int i = 1; i < buildings.length; ++i) {
            int[] cur = buildings[i];
            if (cur[0] < prev[1]) {
                int start = cur[0];
                int shadeLine = prev[1];
                int end = cur[1];

                for (int j = start; j < shadeLine; ++j) {
                    heights[j] = Math.max(cur[2], prev[2]);
                }

                for (int j = shadeLine; j < end; ++j) {
                    heights[j] = cur[2];
                }
            }
            else {
                int start = cur[0];
                int end = cur[1];
                for (int j = start; j < end; ++j) {
                    heights[j] = cur[2];
                }
            }
            prev = cur;
        }

        int start = 0;
        while(start < heights.length && heights[start] == 0) {
            start++;
        }
        int end = start;
        while(end < heights.length) {
            while(end < heights.length && heights[end] == heights[start]) {
                end++;
            }
            res.add(new int[]{start, heights[start]});
            start = end;
        }
        return res;
    }
}
