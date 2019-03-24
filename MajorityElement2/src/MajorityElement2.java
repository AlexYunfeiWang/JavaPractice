import java.util.ArrayList;
import java.util.List;

public class MajorityElement2 {
    /*
    for each size of nums, there are at most 2 majority numbers exist, see:
    8 = 3+3+2 -> 2
    9 = 4+4+1 -> 2
    10 = 4+4+2 -> 2
    11 = 4+4+3 -> 2
    12 = 5+5+2 -> 2
    .....
    and so forth
    so only need to find at most 2 numbers that appears more than n/3 times
    */
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        int number1 = nums[0];
        int number2 = nums[0];
        int count1 = 0;
        int count2 = 0;

        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == number1) {
                count1++;
            }
            else if (nums[i] == number2){
                count2++;
            }
            else if (count1 == 0) {
                number1 = nums[i];
                count1 = 1;
            }
            else if (count2 == 0) {
                number2 = nums[i];
                count2 = 1;
            }
            else {
                count1--;
                count2--;
            }
            /*
            System.out.println("Number1: " + number1);
            System.out.println("Count1 : " + count1);
            System.out.println("Number2: " + number2);
            System.out.println("Count2: " + count2);
            System.out.println("------------------------");
            */
        }
        count1 = 0;
        count2 = 0;

        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == number1) {
                count1++;
            }
            if (nums[i] == number2) {
                count2++;
            }
        }

        if (count1 > nums.length/3) {
            res.add(number1);
        }
        if (number1 != number2) {
            if (count2 > nums.length/3) {
                res.add(number2);
            }
        }

        return res;
    }
}
