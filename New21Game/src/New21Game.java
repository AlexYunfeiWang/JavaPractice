public class New21Game {
    /*
    probability[i] represents the probability that sum equals i

    Game rule is as follows

    point 0 initially
    while (point < K) {
        draw w  from [1, W] randomly
        point += w
    }
    probability(point <= N) ?
    Let's observe that

    point total range [0, K + W - 1]
    we define probability[i] as probability to get i points
    since all start with point 0 => probability[0] = 1

    Probability transition:

    Before we reach point `i`, we draw `w`, i.e., our last point is `i - w`

    probability to get i points = sum(probability to get i - w points * 1 / W) for w can be any of [1, W]
    where 0 <= i - w < K
    target probability = sum of prabability to get points [K, N]
    */

    /*
    The following solution follows the idea, but it is taking too much time

    public double new21Game(int N, int K, int W) {
        if (K == 0) {
            return 1;
        }
        //the max sum that Alice can possibly get to
        int maxPoint = K-1 + W;

        double[] probability = new double[maxPoint+1];

        probability[0] = 1;

        for (int i = 1; i <= maxPoint; ++i) {
            for (int w = 1; w <= W; ++w) {
                if (i-w >= 0 && i-w < K) {
                    probability[i] += probability[i-w] * 1 / W;
                }
            }
        }

        double targetProbability = 0; // Probability of N or less points.
        for (int i = K; i <= N; i++) {
            targetProbability += probability[i];
        }

        return targetProbability;
    }
    */

    //this is an optimized solution:
    public double new21Game(int N, int K, int W) {
        if (K == 0 || N > K-1+W) {
            return 1.0;
        }
        double[] probability = new double[N+1];
        probability[0] = 1;
        double probabilitySum = 1;
        double result = 0;

        for (int i = 1; i <= N; ++i) {
            probability[i] = probabilitySum * 1 / W;
            if (i < K) {
                probabilitySum += probability[i];
            }
            else {
                result += probability[i];
            }

            if (i - W >= 0) {
                probabilitySum -= probability[i-W];
            }
        }
        return result;
    }
}
