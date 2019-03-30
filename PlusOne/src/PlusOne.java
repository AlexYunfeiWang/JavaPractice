import java.util.Arrays;

public class PlusOne {
    public int[] plusOne(int[] digits) {
        if (digits == null || digits.length == 0) {
            return new int[0];
        }
        int carry = 0;
        digits[digits.length-1] += 1;

        for (int i = digits.length-1; i > 0; --i) {
            if (digits[i] > 9) {
                digits[i] = 0;
                digits[i-1] += 1;
            }
            else {
                break;
            }
        }

        if (digits[0] <= 9) {
            return digits;
        }

        int[] res = new int[digits.length+1];
        Arrays.fill(res, 0);
        res[0] = 1;

        return res;
    }
}
