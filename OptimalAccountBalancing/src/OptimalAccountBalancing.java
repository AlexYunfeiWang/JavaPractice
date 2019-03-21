import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OptimalAccountBalancing {
    int res = 0;
    public int minTransfers(int[][] transactions) {
        if (transactions == null || transactions.length == 0 || transactions[0].length == 0) {
            return 0;
        }
        //result cannot exceed number of original transactions,
        //because worst case we can just reverse all the transactions
        res = transactions.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] i : transactions) {
            if (!map.containsKey(i[0])) map.put(i[0], 0);
            if (!map.containsKey(i[1])) map.put(i[1], 0);
            map.put(i[0], map.get(i[0]) - i[2]);
            map.put(i[1], map.get(i[1]) + i[2]);
        }

        List<Integer> positive = new ArrayList<>();
        List<Integer> negative = new ArrayList<>();

        for (int m : map.values()) {
            if (m > 0) {
                positive.add(m);
            }
            else if (m < 0) {
                negative.add(-m);
            }
        }

        DFS(positive, negative, 0);
        return res;
    }

    public void DFS(List<Integer> positive, List<Integer> negative, int count) {
        if (count >= res) {
            return;
        }
        if (positive.isEmpty()) {
            res = Math.min(res, count);
            return;
        }
        int n1 = positive.size();
        int n2 = negative.size();
        if (count + n1 >= res || count + n2 >= res) {
            return;
        }
        for (int i = 0; i < n1; ++i) {
            int pos = positive.remove(i);
            for (int j = 0; j < n2; ++j) {
                int neg = negative.remove(j);
                if (pos > neg) {
                    positive.add(pos-neg);
                    DFS(positive, negative, count+1);
                    positive.remove(positive.size()-1);
                }
                else if (pos < neg) {
                    negative.add(neg-pos);
                    DFS(positive, negative, count+1);
                    negative.remove(negative.size()-1);
                }
                else {
                    DFS(positive, negative, count+1);
                }
                negative.add(j, neg);
            }
            positive.add(i, pos);
        }
    }
}
