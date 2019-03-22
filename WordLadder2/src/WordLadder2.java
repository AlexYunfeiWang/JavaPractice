import java.util.*;

public class WordLadder2 {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<List<String>>();
        if (!wordList.contains(endWord)) {
            return res;
        }
        Set<String> dict = new HashSet<>(wordList);
        Map<String, List<String>> map = new HashMap<>();
        Map<String, Integer> distance = new HashMap<>();
        dict.add(beginWord);

        BFS(map, distance, beginWord, endWord, dict);

        DFS(map, distance, beginWord, endWord, new ArrayList<>(), res);

        return res;
    }

    private void BFS(Map<String, List<String>> map, Map<String, Integer> distance, String begin, String end, Set<String> dict) {
        for (String s : dict) {
            map.put(s, new ArrayList<String>());
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(begin);
        distance.put(begin, 0);

        while(!queue.isEmpty()) {
            boolean foundEnd = false;
            int size = queue.size();

            for (int i = 0; i < size; ++i) {
                String cur = queue.poll();
                int curDist = distance.get(cur);
                List<String> nextList = generateNextWords(cur, dict);

                for (String next : nextList) {
                    map.get(cur).add(next);
                    if (!distance.containsKey(next)) {
                        distance.put(next, curDist+1);
                        if (end.equals(next)) {
                            foundEnd = true;
                        }
                        else {
                            queue.offer(next);
                        }
                    }
                }
                if (foundEnd) {
                    break;
                }
            }
        }
    }

    private void DFS(Map<String, List<String>> map, Map<String, Integer> distance, String cur, String end, List<String> path, List<List<String>> res) {
        path.add(cur);
        if (path.contains(end)) {
            res.add(new ArrayList<>(path));
        }
        else {
            for (String next : map.get(cur)) {
                if (distance.containsKey(next) && distance.get(next) == distance.get(cur) + 1) {
                    DFS(map, distance, next, end, path, res);
                }
            }
        }
        path.remove(path.size()-1);
    }

    private List<String> generateNextWords(String s, Set<String> dict) {

        List<String> neighbors = new ArrayList<String>();
        for (int i = 0; i < s.length(); ++i) {
            for (char c = 'a'; c <= 'z'; ++c) {
                if (c == s.charAt(i)) {
                    continue;
                }
                String next = s.substring(0,i) + c + s.substring(i+1);
                if (dict.contains(next)) {
                    neighbors.add(next);
                }
            }
        }
        return neighbors;
    }
}
