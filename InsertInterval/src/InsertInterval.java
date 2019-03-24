import java.util.ArrayList;
import java.util.List;

class Interval {
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }
}

public class InsertInterval {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if (intervals == null) {
            return intervals;
        }
        if (intervals.size() == 0) {
            intervals.add(newInterval);
            return intervals;
        }
        List<Interval> res = new ArrayList<>();
        int i = 0;

        //add everything before
        while(i < intervals.size() && newInterval.start > intervals.get(i).end) {
            res.add(intervals.get(i));
            i++;
        }

        //merge the intervals that are related to insertion
        while(i < intervals.size() && newInterval.end >= intervals.get(i).start) {
            newInterval = new Interval(
                    Math.min(newInterval.start, intervals.get(i).start),
                    Math.max(newInterval.end, intervals.get(i).end)
            );
            i++;
        }
        //add newInterval to res
        res.add(newInterval);

        //add everything after
        while(i < intervals.size()) {
            res.add(intervals.get(i));
            i++;
        }
        return res;
    }
}
