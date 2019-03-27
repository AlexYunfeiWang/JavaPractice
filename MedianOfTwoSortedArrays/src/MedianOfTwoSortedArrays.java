import java.util.ArrayList;
import java.util.List;

public class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int i = 0;
        int j = 0;

        int stop = (nums1.length + nums2.length) / 2 + 1;
        List<Integer> halfList = new ArrayList<>();

        while(i < nums1.length && j < nums2.length) {
            if (nums1[i] <= nums2[j]) {
                halfList.add(nums1[i]);
                i++;
            }
            else {
                halfList.add(nums2[j]);
                j++;
            }
            if (halfList.size() == stop) {
                break;
            }
        }


        while (i < nums1.length) {
            halfList.add(nums1[i]);
            i++;
            if (halfList.size() == stop) {
                break;
            }
        }

        while (j < nums2.length) {
            halfList.add(nums2[j]);
            j++;
            if (halfList.size() == stop) {
                break;
            }
        }

        int index = stop-1;
        if ((nums1.length + nums2.length) % 2 == 1) {
            return halfList.get(index);
        }
        return (double)((halfList.get(index) + halfList.get(index-1)))/2.0;
    }
}
