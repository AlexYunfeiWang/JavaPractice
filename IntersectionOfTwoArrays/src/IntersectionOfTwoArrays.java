import java.util.HashSet;
import java.util.Set;

public class IntersectionOfTwoArrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> intersect = new HashSet<>();

        for (int i : nums1) {
            set.add(i);
        }

        for (int j : nums2) {
            if (set.contains(j)) {
                intersect.add(j);
            }
        }

        int[] res = new int[intersect.size()];
        int i = 0;

        for (int num : intersect) {
            res[i++] = num;
        }
        return res;
    }
}
