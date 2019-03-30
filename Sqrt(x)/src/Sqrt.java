public class Sqrt {
    public int mySqrt(int x) {
        if (x == 1 || x == 0) {
            return x;
        }
        long start = 1;
        long end = x/2;

        while(start+1 < end) {
            long mid = start + (end-start)/2;
            if (mid*mid == x) {
                return (int)mid;
            }
            else if (mid*mid < x) {
                start = mid;
            }
            else {
                end = mid;
            }
        }
        if (end * end <= x) {
            return (int)end;
        }
        return (int)start;
    }
}
