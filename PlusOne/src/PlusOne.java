import java.util.Arrays;

public class PlusOne {
    public int[] plusOne(int[] digits) {
        int i = digits.length-1;
        digits[i] += 1;

        int carry = digits[i]/10;
        digits[i] %= 10;
        i--;

        while(i >= 0 && carry > 0) {
            digits[i] += carry;
            carry = digits[i]/10;
            if (carry > 0) {
                digits[i] %= 10;
                i--;
            }
            else {
                break;
            }
        }

        if (carry > 0) {
            int n = digits.length;
            digits = new int[n+1];
            Arrays.fill(digits, 0);
            digits[0] = 1;
        }

        return digits;
    }
}
