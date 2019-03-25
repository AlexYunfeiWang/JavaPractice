import java.util.HashMap;
import java.util.Map;

public class LongestConsecutiveSequence {
    /*
    We will use HashMap. The key thing is to keep track of the sequence length and store that
    in the boundary points of the sequence. For example, as a result, for sequence {1, 2, 3, 4, 5},
    map.get(1) and map.get(5) should both return 5.

    Whenever a new element n is inserted into the map, do two things:

    1. See if n - 1 and n + 1 exist in the map, and if so, it means there is an existing sequence
    next to n. Variables left and right will be the length of those two sequences, while 0 means
    there is no sequence and n will be the boundary point later. Store (left + right + 1) as the
    associated value to key n into the map (for future use)
    
    1. Use left and right to locate the other end of the sequences to the left and right of n
    respectively, and replace the value with the new length.
    */
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for (int n : nums) {

            if (!map.containsKey(n)) {
                int left = map.containsKey(n-1) ? map.get(n-1) : 0;
                int right = map.containsKey(n+1) ? map.get(n+1) : 0;

                int sum = left+right+1;

                /*
                if we don't input n, map.containsKey(n) will happen again and overCount
                put(n, sum) or (n, whatever) doesn't matter, as long as we put n as a key in map
                */
                map.put(n, sum);

                max = Math.max(max, sum);

                map.put(n-left, sum);
                map.put(n+right, sum);
            }
            else {
                continue;
            }
        }

        return max;
    }
}
