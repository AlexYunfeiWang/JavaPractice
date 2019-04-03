public class SplitArrayLargestSum {
    /*
    Same idea as Leetcode 1011: Capacity To Ship Packages Within D Days
    */
    public int splitArray(int[] nums, int m) {
        if (nums == null || nums.length < m) {
            return 0;
        }
        long left = Long.valueOf(nums[0]);
        long right = Long.valueOf(nums[0]);
        for (int i = 1; i < nums.length; ++i) {
            left = Math.max(left, nums[i]);
            right += nums[i];
        }

        if (m == 1) {
            return (int)right;
        }

        long res = binarySearch(left, right, m, nums);

        return (int)res;
    }

    private long binarySearch(long min, long max, int m, int[] nums) {
        long start = min;
        long end = max;

        while(start+1 < end) {
            long mid = start + (end-start)/2;
            if (partitionSize(nums, mid) > m) {
                start = mid;
            }
            else {
                end = mid;
            }
        }
        if (partitionSize(nums, start) <= m) {
            return start;
        }
        return end;
    }

    private int partitionSize(int[] nums, long cap) {
        int count = 1;
        long curSum = Long.valueOf(nums[0]);
        for (int i = 1; i < nums.length; ++i) {
            if (curSum + Long.valueOf(nums[i]) > cap) {
                count++;
                curSum = Long.valueOf(nums[i]);
            }
            else {
                curSum += Long.valueOf(nums[i]);
            }
        }
        return count;
    }
}
