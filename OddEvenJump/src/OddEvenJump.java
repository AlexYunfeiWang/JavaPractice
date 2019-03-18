import java.util.*;

public class OddEvenJump {
/*
First let's create a boolean DP array.
dp[i][0] stands for you can arrive index n - 1 starting from index i at an odd step.
dp[i][1] stands for you can arrive index n - 1 starting from index i at an even step.

Initialization:
Index n - 1 is always a good start point, regardless it's odd or even step right now.
Thus dp[n - 1][0] = dp[n - 1][1] = true.

DP formula:
dp[i][0] = dp[index_next_greater_number][1] - because next is even step
dp[i][1] = dp[index_next_smaller_number][0] - because next is odd step

Result:
Since first step is odd step, then result is count of dp[i][0] with value true.

To quickly find the next greater or smaller number and its index:
- traverse the array reversely and store data into a TreeMap
using the number as Key and its index as Value.

 */

    public int oddEvenJumps(int[] A) {
        int n = A.length;
        TreeMap<Integer, Integer> map = new TreeMap<>();

        boolean[][] dp = new boolean[n][2];
        dp[n-1][0] = true;
        dp[n-1][1] = true;
        map.put(A[n-1], n-1);

        int res = 1;

        for (int i = n-2; i >= 0; --i) {
            //Odd step
            //ceilingKey returns the smallest number that is >= given key
            Integer nextGreater = map.ceilingKey(A[i]);
            if (nextGreater != null) {
                dp[i][0] = dp[map.get(nextGreater)][1];
            }

            //Even step
            //floorKey returns the greatest number that is <= given key
            Integer nextSmaller = map.floorKey(A[i]);
            if (nextSmaller != null) {
                dp[i][1] = dp[map.get(nextSmaller)][0];
            }

            map.put(A[i], i);

            res += dp[i][0] ? 1 : 0;
        }
        return res;
    }
}
