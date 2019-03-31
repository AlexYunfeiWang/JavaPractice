public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        int maxIndex = 0;

        dp[0] = nums[0];

        for (int i = 1; i < nums.length; ++i) {
            //printArray(dp);
            int pos = binarySearch(dp, maxIndex, nums[i]);
            //System.out.println(pos);
            if (dp[pos] > nums[i]) {
                dp[pos] = nums[i];
            }
            if (pos > maxIndex) {
                maxIndex = pos;
                dp[maxIndex] = nums[i];
            }
        }
        return maxIndex+1;
    }

    private int binarySearch(int[] dp, int maxIndex, int target) {
        int start = 0;
        int end = maxIndex;

        while(start+1 < end) {
            int mid = start + (end-start)/2;
            if (dp[mid] == target) {
                return mid;
            }
            else if (dp[mid] > target) {
                end = mid;
            }
            else {
                start = mid;
            }
        }
        if (dp[end] < target) {
            return maxIndex+1;
        }
        else if (dp[end] > target && target > dp[start]) {
            return end;
        }
        return start;
    }

    private void printArray(int[] dp) {
        for (int i = 0; i < dp.length; ++i) {
            System.out.print(dp[i] + ",");
        }
        System.out.println();
    }
}
