import java.util.Arrays;

public class NextClosestTime {
    /*
    Start from the last digit of the timestamp, for each digit, try to find a
    number that is greater
    if cannot find, then assign the smallest number, move on to the next digit
    if found, then compare if the new number is greater than old number, if yes,
    then return the result
    */
    private char NINE = '9';
    private char FIVE = '5';
    private char THREE = '3';
    private char TWO = '2';

    public String nextClosestTime(String time) {
        char[] result = time.toCharArray();
        char[] digits = new char[]{result[0], result[1], result[3], result[4]};
        Arrays.sort(digits);

        //find next digit for HH:M_
        // no upperLimit for this digit, i.e. 0-9
        result[4] = findNextGreaterDigit(result[4], (char)(NINE + 1), digits);
        if (result[4] > time.charAt(4)) {
            return String.valueOf(result);
        }

        //find next digit for HH:_M
        result[3] = findNextGreaterDigit(result[3], FIVE, digits);
        if (result[3] > time.charAt(3)) {
            return String.valueOf(result);
        }

        //find next digit for H_:MM
        if (result[0] == TWO) {
            result[1] = findNextGreaterDigit(result[1], THREE, digits);
        }
        else {
            // no upperLimit for this digit, i.e. 0-9
            result[1] = findNextGreaterDigit(result[1], (char)(NINE + 1), digits);
        }
        if (result[1] > time.charAt(1)) {
            return String.valueOf(result);
        }

        //find next digit for _H:MM
        result[0] = findNextGreaterDigit(result[0], TWO, digits);

        return String.valueOf(result);
    }

    private char findNextGreaterDigit(char currentDigit, char upperLimit, char[] digits) {
        if (currentDigit == upperLimit) {
            return digits[0];
        }
        int nextGreaterDigitIndex = Arrays.binarySearch(digits, currentDigit) + 1;

        while(nextGreaterDigitIndex < 4 &&
                (digits[nextGreaterDigitIndex] > upperLimit || digits[nextGreaterDigitIndex] == currentDigit)) {
            nextGreaterDigitIndex++;
        }
        return nextGreaterDigitIndex == 4 ? digits[0] : digits[nextGreaterDigitIndex];
    }
}
