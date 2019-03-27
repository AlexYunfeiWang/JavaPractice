import java.util.Collections;
import java.util.PriorityQueue;

public class FindMedianFromDataStream {
    PriorityQueue<Integer> firstHalf;
    PriorityQueue<Integer> secondHalf;

    /** initialize your data structure here. */
    public FindMedianFromDataStream() {
        firstHalf = new PriorityQueue<>(Collections.reverseOrder());
        secondHalf = new PriorityQueue<>();
    }

    public void addNum(int num) {
        secondHalf.offer(num);
        firstHalf.offer(secondHalf.poll());
        if (secondHalf.size() < firstHalf.size()) {
            secondHalf.offer(firstHalf.poll());
        }
    }

    public double findMedian() {
        if ((secondHalf.size() + firstHalf.size()) % 2 == 1) {
            return secondHalf.peek();
        }
        return (firstHalf.peek() + secondHalf.peek())/2.0;
    }
}
