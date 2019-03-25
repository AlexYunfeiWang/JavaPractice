import java.util.Arrays;

public class Candy {
    public int candy(int[] ratings) {
        if (ratings.length == 0 || ratings.length == 1) {
            return ratings.length;
        }
        int[] candies = new int[ratings.length];

        //give everybody 1 candy at beginning
        Arrays.fill(candies, 1);

        /*
        Scan from left to right, to make sure right higher rated child gets 1 more
        candy than left lower rated child
        */
        for (int i = 1; i < ratings.length; ++i) {
            if (ratings[i] > ratings[i-1]) {
                candies[i] = candies[i-1]+1;
            }
        }

        /*
        Scan from right to left, to make sure left higher rated child gets 1 more
        candy than right lower rated child
        */
        for (int i = ratings.length-2; i >= 0; --i) {
            if (ratings[i] > ratings[i+1]) {
                candies[i] = Math.max(candies[i], candies[i+1]+1);
            }
        }

        int sum = 0;
        for (int i : candies) {
            sum += i;
        }
        return sum;
    }
}
