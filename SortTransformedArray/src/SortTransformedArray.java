public class SortTransformedArray {
    /*
    Since the array is sorted, then it means:

    1.a>0, two ends in original array are bigger than center if you learned middle school math before.
    2.a<0, center is bigger than two ends.

    so use two pointers i, j and do a merge-sort like process. depending on sign of a,
    you may want to start from the beginning or end of the transformed array. For a==0
    case, it does not matter what b's sign is.
    */
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int[] res = new int[nums.length];
        int left = 0;
        int right = nums.length-1;

        int index = a >= 0 ? nums.length-1 : 0;

        while(left <= right) {
            if (a >= 0) {
                if (calculate(nums[left], a, b, c) > calculate(nums[right], a, b, c)) {
                    res[index] = calculate(nums[left], a, b, c);
                    left++;
                }
                else {
                    res[index] = calculate(nums[right], a, b, c);
                    right--;
                }
                index--;
            }
            else {
                if (calculate(nums[left], a, b, c) > calculate(nums[right], a, b, c)) {
                    res[index] = calculate(nums[right], a, b, c);
                    right--;
                }
                else {
                    res[index] = calculate(nums[left], a, b, c);
                    left++;
                }
                index++;
            }
        }
        return res;
    }

    private int calculate(int x, int a, int b, int c) {
        return a * x * x + b * x + c;
    }
}
