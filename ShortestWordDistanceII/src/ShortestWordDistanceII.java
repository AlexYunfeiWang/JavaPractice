import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShortestWordDistanceII {
    Map<String, List<Integer>> indexMap;

    public ShortestWordDistanceII(String[] words) {
        indexMap = new HashMap<>();

        for (int i = 0; i < words.length; ++i) {
            if (!indexMap.containsKey(words[i])) {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                indexMap.put(words[i], list);
            }
            else {
                indexMap.get(words[i]).add(i);
            }
        }
    }

    public int shortest(String word1, String word2) {
        int result = Integer.MAX_VALUE;
        List<Integer> word1Index = indexMap.get(word1);
        List<Integer> word2Index = indexMap.get(word2);

        int i = 0;
        int j = 0;

        while(i < word1Index.size() && j < word2Index.size()) {
            if (word1Index.get(i) < word2Index.get(j)) {
                result = Math.min(result, word2Index.get(j) - word1Index.get(i));
                i++;
            }
            else {
                result = Math.min(result, word1Index.get(i) - word2Index.get(j));
                j++;
            }
        }
        return result;
    }
}
