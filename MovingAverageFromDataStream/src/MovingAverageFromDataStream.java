import java.util.LinkedList;
import java.util.Queue;

public class MovingAverageFromDataStream {
    int size;
    Queue<Integer> queue;
    int sum;

    /** Initialize your data structure here. */
    public MovingAverageFromDataStream(int size) {
        this.size = size;
        queue = new LinkedList<>();
        sum = 0;
    }

    public double next(int val) {
        if (queue.size() == size) {
            sum -= queue.poll();
        }
        sum += val;
        queue.offer(val);
        return (double)sum/(double)(queue.size());
    }
}
