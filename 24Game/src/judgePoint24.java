import java.util.ArrayList;
import java.util.List;

public class judgePoint24 {
    final double eps = 0.001;
    boolean res = false;

    public boolean judgePoint24(int[] nums) {
        if (nums == null || nums.length != 4) {
            return res;
        }
        List<Double> list = new ArrayList<>();
        for (int num : nums) {
            list.add((double)num);
        }
        DFS(list);
        return res;
    }

    private void DFS(List<Double> list) {
        if (res) {
            return;
        }
        if (list.size() == 1) {
            if (Math.abs(24.0 - list.get(0)) < eps) {
                res = true;
            }
            return;
        }

        for (int i = 0; i < list.size(); ++i) {
            for (int j = 0; j < i; ++j) {
                double p1 = list.get(i);
                double p2 = list.get(j);

                List<Double> next = new ArrayList<>();
                next.add(p1+p2);
                next.add(p1-p2);
                next.add(p2-p1);
                next.add(p1*p2);

                if (Math.abs(p1) > eps) {
                    next.add(p2/p1);
                }
                if (Math.abs(p2) > eps) {
                    next.add(p1/p2);
                }

                list.remove(i);
                list.remove(j);
                for (double d : next) {
                    list.add(d);
                    DFS(list);
                    list.remove(list.size()-1);
                }
                list.add(j, p2);
                list.add(i, p1);
            }
        }
    }
}
