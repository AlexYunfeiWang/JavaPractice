import java.util.ArrayList;
import java.util.List;

public class ExpressionAddOperators {
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        if (num == null || num.length() == 0) {
            return res;
        }
        DFS(res, "", num, 0, target, 0, 0);
        return res;
    }
    private void DFS(List<String> res, String path, String num, int start, int target, long eval, long multiplied) {
        if (start == num.length()) {
            if (target == eval) {
                res.add(path);
            }
            return;
        }
        for (int i = start; i < num.length(); ++i) {

            if (i != start && num.charAt(start) == '0') {
                break;
            }

            long cur = Long.valueOf(num.substring(start, i+1));
            if (start == 0) {
                DFS(res, path+cur, num, i+1, target, cur, cur);
            }
            else {
                DFS(res, path + "+" + cur, num, i+1, target, eval+cur, cur);

                DFS(res, path + "-" + cur, num, i+1, target, eval-cur, -cur);

                DFS(res, path + "*" + cur, num, i+1, target, eval - multiplied + multiplied * cur, multiplied * cur);
            }
        }
    }
}
