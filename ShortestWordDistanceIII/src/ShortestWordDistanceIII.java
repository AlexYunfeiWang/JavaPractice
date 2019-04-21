import java.util.ArrayList;
import java.util.List;

public class ShortestWordDistanceIII {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        int result = Integer.MAX_VALUE;
        if (words == null || words.length == 0) {
            return result;
        }

        if (word1.equals(word2)) {
            return shortestDistanceForSameWords(words, word1, result);
        }

        int p1 = -1;
        int p2 = -1;

        for (int i = 0; i < words.length; ++i) {
            if (words[i].equals(word1)) {
                p1 = i;
            }
            if (words[i].equals(word2)) {
                p2 = i;
            }
            if (p1 != -1 && p2 != -1) {
                result = Math.min(result, Math.abs(p1-p2));
            }
        }
        return result;
    }

    private int shortestDistanceForSameWords(String[] words, String word, int result) {
        List<Integer> indexList = new ArrayList<>();
        for (int i = 0; i < words.length; ++i) {
            if (words[i].equals(word)) {
                indexList.add(i);
            }
        }
        for (int i = 0; i < indexList.size(); ++i) {
            for (int j = i; j < indexList.size(); ++j) {
                if (i != j) {
                    result = Math.min(result, Math.abs(indexList.get(i)-indexList.get(j)));
                }
            }
        }
        return result;
    }
}
