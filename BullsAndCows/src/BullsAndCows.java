public class BullsAndCows {
    /*
    The idea is to iterate over the numbers in secret and in guess and count
    all bulls right away. For cows maintain an array that stores count of the
    number appearances in secret and in guess. Increment cows when either number
    from secret was already seen in guest or vice versa.
    */
    public String getHint(String secret, String guess) {
        int bulls = 0;
        int cows = 0;
        int[] numbers = new int[10];
        for (int i = 0; i<secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) bulls++;
            else {
                if (numbers[secret.charAt(i)-'0']++ < 0) cows++;
                if (numbers[guess.charAt(i)-'0']-- > 0) cows++;
            }
        }
        return bulls + "A" + cows + "B";
    }
}
