public class ReverseInteger {
    public int reverse(int x) {
        if (x == 0) {
            return 0;
        }
        int sign = 1;
        long num = (long)x;
        if (num < 0) {
            sign = -1;
            num = -num;
        }
        char[] arr = String.valueOf(num).toCharArray();

        StringBuilder sb = new StringBuilder();

        int i = arr.length-1;

        while(arr[i] == 0) {
            i--;
        }

        for (; i >= 0; --i) {
            sb.append(arr[i]);
        }

        long result = Long.valueOf(sb.toString()) * sign;

        return result < Integer.MAX_VALUE && result > Integer.MIN_VALUE ? (int)result : 0;
    }
}
