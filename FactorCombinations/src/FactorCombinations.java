import java.util.ArrayList;
import java.util.List;

public class FactorCombinations {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<>();
        if (n == 0 || n == 1) {
            return res;
        }

        DFS(2, n, new ArrayList<>(), res);

        return res;
    }

    private void DFS(int start, int remain, List<Integer> path, List<List<Integer>> res) {
        if (remain == 1) {
            if (path.size() > 1) {
                res.add(new ArrayList<>(path));
            }
            return;
        }

        for (int i = start; i < remain; ++i) {
            //since i cannot be greater than remina/i on next level, so once i > remain/i, we break
            //This is for optimization, but it will miss the case when i == remain, which will be used to
            //add results to res, so we add another case outside the loop
            if (i > remain/i) {
                break;
            }
            if (remain % i == 0) {
                path.add(i);
                DFS(i, remain/i, path, res);
                path.remove(path.size()-1);
            }
        }

        //when i == remain
        path.add(remain);
        DFS(remain, 1, path, res);
        path.remove(path.size()-1);
    }
}
