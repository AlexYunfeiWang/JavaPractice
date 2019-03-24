import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Interval {
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }
}
public class EmployeeFreeTime {
    /*
    Can also be done in PriorityQueue, maybe less space complexity, but time complexity is same

        List<Interval> result = new ArrayList<>();

        PriorityQueue<Interval> pq = new PriorityQueue<>((a, b) -> a.start - b.start);
        avails.forEach(e -> pq.addAll(e));

        Interval temp = pq.poll();
        while(!pq.isEmpty()) {
            if(temp.end < pq.peek().start) { // no intersect
                result.add(new Interval(temp.end, pq.peek().start));
                temp = pq.poll(); // becomes the next temp interval
            }else { // intersect or sub merged
                temp = temp.end < pq.peek().end ? pq.peek() : temp;
                pq.poll();
            }
        }
        return result;
    */
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> res = new ArrayList<>();
        if (schedule == null || schedule.size() == 0) {
            return res;
        }
        List<Interval> list = new ArrayList<>();
        for (List<Interval> sub : schedule) {
            for (Interval time : sub) {
                list.add(time);
            }
        }
        Collections.sort(list, (a, b) -> a.start - b.start);
        List<Interval> merged = mergeSchedule(list);

        for (int i = 0; i < merged.size()-1; ++i) {
            res.add(new Interval(merged.get(i).end, merged.get(i+1).start));
        }
        return res;
    }

    private List<Interval> mergeSchedule(List<Interval> list) {
        List<Interval> merged = new ArrayList<>();

        Interval cur = null;

        for (Interval time : list) {
            if (cur == null || cur.end < time.start) {
                cur = time;
                merged.add(cur);
            }
            else {
                cur.end = Math.max(cur.end, time.end);
            }
        }
        return merged;
    }
}
