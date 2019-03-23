import java.util.HashMap;
import java.util.Map;

public class ShortestWordDistance {
    public int shortestDistance(String[] words, String word1, String word2) {
        int min = Integer.MAX_VALUE;
        int p1 = -1;
        int p2 = -1;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; ++i) {
            String s = words[i];
            if (s.equals(word1)) {
                p1 = i;
            }
            if (s.equals(word2)) {
                p2 = i;
            }
            if (p1 != -1 && p2 != -1) {
                min = Math.min(min, Math.abs(p1-p2));
            }
        }
        return min;
    }
}
