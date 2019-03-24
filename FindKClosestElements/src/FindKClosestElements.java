import java.util.ArrayList;
import java.util.List;

public class FindKClosestElements {
    /*
     binary-search for where the resulting elements start in the array. It's the first index i so that arr[i] is better than arr[i+k] (with "better" meaning closer to or equally close to x). Then I just return the k elements starting there.
     */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int lo = 0, hi = arr.length - k;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (x - arr[mid] > arr[mid+k] - x)
                lo = mid + 1;
            else
                hi = mid;
        }

        List<Integer> res = new ArrayList<>();

        for (int i = lo; i < lo+k; ++i) {
            res.add(arr[i]);
        }
        return res;
    }
}
