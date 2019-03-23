public class RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int start = 0;
        int i = start;

        while(i < nums.length) {
            while(i < nums.length && nums[start] == nums[i]) {
                i++;
            }
            if (i < nums.length) {
                start++;
                nums[start] = nums[i];
            }

        }

        return start+1;
    }
}
