import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < k || k <= 0) {
            return new int[0];
        }
        int n = nums.length;
        int[] res = new int[n-k+1];
        int count = 0;

        //this deque only stores the indices of the localMax of each window
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < nums.length; ++i) {
            //delete the localMax whose index is out of k range
            while(!deque.isEmpty() && deque.peek() < i-k+1) {
                deque.poll();
            }

            //if incoming value is greater than the previous localMax values, keep deleting the previous localMax values
            while(!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            //add current value to deque
            deque.offer(i);

            //once i is greater than k-1, it means we can start adding localMax to result
            //the localMax will be deque.peek(), due to previous operations
            if (i >= k-1) {
                res[count++] = nums[deque.peek()];
            }
        }
        return res;
    }
}
