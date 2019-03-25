public class InterleavingString {
    /*
    dp[i][j] == true means the first i+j chars in s3 is
    matched by i chars from s1 and j chars from s2

    therefore, dp[i][j] is true if one of the following is true:
        1. dp[i-1][j] is true and also s1.charAt(i-1) == s3.charAt(i+j-1)
        2. dp[i][j-1] is true and also s2.charAt(j-1) == s3.charAt(i+j-1)

    */
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }

        boolean[][] dp = new boolean[s1.length()+1][s2.length()+1];

        dp[0][0] = true;

        for (int i = 1; i < dp.length; ++i) {
            dp[i][0] = dp[i-1][0] && s1.charAt(i-1) == s3.charAt(i-1);
        }

        for (int j = 1; j < dp[0].length; ++j) {
            dp[0][j] = dp[0][j-1] && s2.charAt(j-1) == s3.charAt(j-1);
        }

        for (int i = 1; i < dp.length; ++i) {
            for (int j = 1; j < dp[0].length; ++j) {
                dp[i][j] = (dp[i-1][j] && s1.charAt(i-1) == s3.charAt(i+j-1))
                        || (dp[i][j-1] && s2.charAt(j-1) == s3.charAt(i+j-1));
            }
        }
        return dp[s1.length()][s2.length()];
    }
}
