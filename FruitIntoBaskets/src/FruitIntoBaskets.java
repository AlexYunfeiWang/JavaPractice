public class FruitIntoBaskets {

    public int totalFruit(int[] tree) {
        /*
        This problem is to find the longest subarray
        that only consists of 2 unique numbers

        Use sliding window approach
        */

        int uniqueCount = 0; //count of unique numbers
        int max = 0; //max length of required subarray

        int[] map = new int[40001]; //frequency map

        int left = 0;
        int right = 0;

        for (; right < tree.length; ++right) {
            if (map[tree[right]] == 0) {
                //discovered a new unique number
                uniqueCount++;
            }
            //add new frequency to map
            map[tree[right]]++;

            while(uniqueCount > 2) {
                //keep moving window from left
                //if frequency of a number becomes 0,
                //update uniqueCount
                if (--map[tree[left]] == 0) {
                    uniqueCount--;
                }
                left++;
            }
            max = Math.max(max, right-left+1);
        }
        return max;
    }
}
