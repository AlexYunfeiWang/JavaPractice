import java.util.Random;

public class RandomPickWithWeight {
    Random rand;
    int[] wSum;

    public RandomPickWithWeight(int[] w) {
        rand = new Random();
        wSum = new int[w.length];
        for (int i = 1; i < w.length; ++i) {
            w[i] += w[i-1];
        }
        wSum = w;
    }

    public int pickIndex() {
        int length = wSum.length;
        int pick = rand.nextInt(wSum[wSum.length-1])+1;

        int start = 0;
        int end = length-1;
        while(start+1 < end) {
            int mid = start + (end-start)/2;
            if (wSum[mid] == pick) {
                return mid;
            }
            else if (wSum[mid] < pick) {
                start = mid;
            }
            else {
                end = mid;
            }
        }
        if (wSum[start] >= pick) {
            return start;
        }
        return end;
    }
}

