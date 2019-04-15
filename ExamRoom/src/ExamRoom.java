import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class ExamRoom {
    int N;
    PriorityQueue<Interval> pq;

    class Interval {
        int left;
        int right;
        int dist;
        Interval(int left, int right) {
            this.left = left;
            this.right = right;

            if (left == -1) {
                this.dist = right;
            } else if (right == N) {
                this.dist = N - 1 - left;
            } else {
                this.dist = Math.abs(right-left)/2;
            }
        }
    }

    public ExamRoom(int N) {
        this.N = N;
        pq = new PriorityQueue<Interval>((a,b) -> a.dist != b.dist ? b.dist - a.dist : a.left - b.left);
        pq.offer(new Interval(-1, N));
    }

    // O(logn): poll top candidate, split into two new intervals
    public int seat() {
        int seat = 0;
        Interval maxInterval = pq.poll();
        if (maxInterval.left == -1) {
            seat = 0;
        } else if (maxInterval.right == N) {
            seat = N-1;
        } else {
            seat = (maxInterval.left + maxInterval.right)/2;
        }

        pq.offer(new Interval(maxInterval.left, seat));
        pq.offer(new Interval(seat, maxInterval.right));

        return seat;
    }

    // O(n)Find head and tail based on p. Delete and merge two ends
    public void leave(int p) {
        Interval head = null;
        Interval tail = null;

        List<Interval> intervals = new ArrayList<>(pq);
        for (Interval cur : intervals) {
            if (cur.left == p) {
                tail = cur;
            }
            if (cur.right == p) {
                head = cur;
            }
            if (head != null && tail != null) {
                break;
            }
        }
        pq.remove(head);
        pq.remove(tail);
        pq.offer(new Interval(head.left, tail.right));
    }
}
