import java.util.ArrayList;
import java.util.List;

public class SummaryRanges {
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        int i = 0;
        int start = 0;

        while (i < nums.length) {
            while(i < nums.length && nums[start] == nums[i] - (i-start)) {
                i++;
            }
            res.add(getRange(nums, start, i-1));
            start = i;
        }
        return res;
    }

    private String getRange(int[] nums, int i, int j) {
        StringBuilder sb = new StringBuilder();
        if (i == j) {
            sb.append(nums[i]);
        }
        else {
            sb.append(nums[i] + "->" + nums[j]);
        }
        return sb.toString();
    }
}
