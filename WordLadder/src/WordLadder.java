import java.util.*;

public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) {
            return 0;
        }
        dict.add(beginWord);

        Map<String, Integer> distance = new HashMap<>();

        BFS(distance, dict, beginWord, endWord);

        return distance.containsKey(endWord) ? distance.get(endWord) : 0;
    }

    public void BFS(Map<String, Integer> distance, Set<String> dict, String beginWord, String endWord) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        distance.put(beginWord, 1);

        while(!queue.isEmpty()) {
            boolean endFound = false;
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                String cur = queue.poll();
                int curDist = distance.get(cur);
                List<String> nextList = getNextList(cur, dict);
                for (String next : nextList) {
                    if (!distance.containsKey(next)) {
                        distance.put(next, curDist+1);
                        if (next.equals(endWord)) {
                            endFound = true;
                        }
                        else {
                            queue.offer(next);
                        }
                    }
                }
                if (endFound) {
                    break;
                }
            }
        }
    }

    private List<String> getNextList(String cur, Set<String> dict) {
        List<String> nextList = new ArrayList<>();
        for (int i = 0; i < cur.length(); ++i) {
            for (char c = 'a'; c <= 'z'; ++c) {
                if (c == cur.charAt(i)) {
                    continue;
                }
                String next = cur.substring(0,i) + c + cur.substring(i+1);
                if (dict.contains(next)) {
                    nextList.add(next);
                }
            }
        }
        return nextList;
    }
}
