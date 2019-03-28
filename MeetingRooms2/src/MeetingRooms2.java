import java.util.Arrays;

class Interval {
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }
}

public class MeetingRooms2 {
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        int n = intervals.length;
        int[] start = new int[n];
        int[] end = new int[n];

        for (int i = 0; i < n; ++i) {
            start[i] = intervals[i].start;
            end[i] = intervals[i].end;
        }

        Arrays.sort(start);
        Arrays.sort(end);

        int startIndex = 0;
        int endIndex = 0;
        int min = 0;
        int count = 0;

        while(startIndex < n && endIndex < n) {
            if (start[startIndex] < end[endIndex]) {
                count++;
                startIndex++;
            }
            else {
                count--;
                endIndex++;
            }
            min = Math.max(min, count);
        }
        return min;
    }
}
