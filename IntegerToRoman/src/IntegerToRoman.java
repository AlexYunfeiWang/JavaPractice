public class IntegerToRoman {

    public String intToRoman(int n) {
        if (n <= 0) {
            return "";
        }
        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder sb = new StringBuilder();

        //start from the highest in nums, keep looking until digit > 0
        int digit = 0;

        while(n > 0) {
            int times = n / nums[digit];
            //if times == 0, that means n is less than nums[digit], need to move on
            if (times == 0) {
                digit++;
                continue;
            }
            n -= nums[digit] * times;

            for (; times > 0; --times) {
                sb.append(symbols[digit]);
            }
            digit++;
        }
        return sb.toString();
    }
}
