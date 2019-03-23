import java.util.Arrays;

public class ThreeSumClosest {
    int diff = Integer.MAX_VALUE;
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums);

        for (int i = 0; i < nums.length-2; ++i) {
            helper(nums, i+1, nums.length-1, target - nums[i]);
        }

        //System.out.println(diff);

        return target - diff;
    }

    public void helper(int[] nums, int start, int end, int target) {
        while(start < end) {
            if (nums[start] + nums[end] == target) {
                diff = 0;
                break;
            }
            else if (nums[start] + nums[end] < target) {
                if (Math.abs(target - nums[start] - nums[end]) < Math.abs(diff)) {
                    diff = target - nums[start] - nums[end];
                }
                start++;
                /*
                while(start < end && nums[start] == nums[start-1]) {
                    start++;
                }
                */
            }
            else {
                if (Math.abs(target - nums[start] - nums[end]) < Math.abs(diff)) {
                    diff = target - nums[start] - nums[end];
                }
                end--;
                /*
                while(start < end && nums[end] == nums[end+1]) {
                    end--;
                }
                */
            }
        }
    }
}
