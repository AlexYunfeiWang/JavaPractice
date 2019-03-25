import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class ReorganizeString {
    public String reorganizeString(String S) {
        if (S == null || S.length() == 0) {
            return "";
        }

        Map<Character, Integer> map = new HashMap<>();

        for (char c : S.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0)+1);
            if (map.get(c) > (S.length()+1)/2) {
                return "";
            }
        }

        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a, b) -> (b.getValue() - a.getValue()));

        pq.addAll(map.entrySet());

        StringBuilder sb = new StringBuilder();

        while(!pq.isEmpty()) {
            //System.out.println(sb.toString());
            Map.Entry<Character, Integer> entry1 = pq.poll();
            if (sb.length() == 0 || sb.charAt(sb.length()-1) != entry1.getKey()) {
                sb.append(entry1.getKey());
                entry1.setValue(entry1.getValue()-1);
                if (entry1.getValue() > 0) {
                    pq.add(entry1);
                }
            }
            else {
                Map.Entry<Character, Integer> entry2 = pq.poll();
                sb.append(entry2.getKey());
                entry2.setValue(entry2.getValue()-1);
                if (entry2.getValue() > 0) {
                    pq.add(entry2);
                }
                pq.add(entry1);
            }
        }
        return sb.toString();
    }
}
