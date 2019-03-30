import java.util.*;

public class ReconstructItinerary {
    public List<String> findItinerary(String[][] tickets) {
        List<String> res = new ArrayList<>();
        if (tickets == null || tickets.length == 0 || tickets[0].length == 0) {
            return res;
        }
        Map<String, PriorityQueue<String>> map = new HashMap<>();

        for (String[] t : tickets) {
            if (!map.containsKey(t[0])) {
                map.put(t[0], new PriorityQueue<String>());
            }
            map.get(t[0]).offer(t[1]);
        }
        DFS("JFK", map, res);
        return res;
    }

    private void DFS(String src, Map<String, PriorityQueue<String>> map, List<String> res) {
        PriorityQueue<String> pq = map.get(src);

        while(pq != null && !pq.isEmpty()) {
            String dst = pq.poll();
            DFS(dst, map, res);
        }
        res.add(0,src);
    }
}
