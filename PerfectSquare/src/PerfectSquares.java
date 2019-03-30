public class PerfectSquares {
    public int numSquares(int n) {
        if (n == 0) {
            return 1;
        }
        int[] dp = new int[n+1];
        for (int i = 0; i <= n; ++i) {
            dp[i] = i;
        }
        //dp[0] = 1;

        for (int i = 0; i <= n; ++i) {
            for (int j = 0; i + j*j <= n; ++j) {
                dp[i+j*j] = Math.min(dp[i+j*j], dp[i]+1);
            }
        }
        return dp[n];
    }
}
}
