import java.util.ArrayList;
import java.util.List;

class Interval {
    int start;
    int end;
    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
public class MyCalendar {
    List<Interval> list;
    public MyCalendar() {
        list = new ArrayList<Interval>();
    }

    public boolean book(int start, int end) {
        if (list.size() == 0) {
            list.add(new Interval(start,end));
            return true;
        }

        //binary search to find left and right intervals closest to the new interval
        int left = 0;
        int right = list.size()-1;

        while(left+1 < right) {
            int mid = left + (right-left)/2;
            if (list.get(mid).end <= start) {
                left = mid;
            }
            else {
                right = mid;
            }
        }

        /*
        only 3 cases can we insert a new interval:
        1. start >= right end
        2. start and end is between left end and right start
        3. end <= left start

        any other cases, return false
        */

        //if start is larger than the right end, append the new interval
        if (list.get(right).end <= start) {
            list.add(new Interval(start, end));
            return true;
        }
        //if start and end falls between left end and right start, insert the new interval
        if (list.get(left).end <= start && list.get(right).start >= end) {
            list.add(left+1, new Interval(start, end));
            return true;
        }
        //if end is less than left start, insert new interval in the front
        if (list.get(left).start >= end) {
            list.add(left-1 >= 0 ? left-1 : 0, new Interval(start, end));
            return true;
        }
        return false;
    }
}

