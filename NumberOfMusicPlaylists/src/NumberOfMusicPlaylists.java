public class NumberOfMusicPlaylists {
    /*
    dp[i][j] denotes the solution of i songs with j different songs.
    So the final answer should be dp[L][N]

    When without K constraint:

    For dp[i][j], there are 2 cases:
    1. (the last added one is new song): listen i - 1 songs
    with j - 1 different songs, then the last one is definitely
    new song with the choices of N - (j - 1).
    2. (the last added one is old song): listen i - 1 songs with
    j different songs, then the last one is definitely old song
    with the choices of j
    if without the constraint of K, the status equation will be
    dp[i][j] = dp[i-1][j-1] * (N - (j-1)) + dp[i-1][j] * j

    If with the constaint of K, there are also 2 cases
    1. no changes since the last added one is new song. Hence, there
    is no conflict
    2. now we don't have choices of j for the last added old song.
    It should be updated j - k because k songs can't be chosed from
    j - 1 to j - k. However, if j <= K, this case will be 0 because
    only after choosing K different other songs, old song can be chosen.

    if (j > k)
    dp[i][j] = dp[i-1][j-1] * (N- (j-1)) + dp[i-1][j] * (j-k)
    else
    dp[i][j] = dp[i-1][j-1] * (N- (j-1))
*/
    public int numMusicPlaylists(int N, int L, int K) {
        long[][] dp = new long[L+1][N+1];
        dp[0][0] = 1;
        int mod = (int)Math.pow(10, 9) + 7;
        for (int i = 1; i <= L; ++i) {
            for (int j = 1; j <= N; ++j) {

                dp[i][j] = (dp[i-1][j-1] * (N - (j-1))) % mod;
                if (j > K) {
                    dp[i][j] = (dp[i][j] + (dp[i-1][j] * (j-K)) % mod) % mod;
                }

            }
        }
        return (int)dp[L][N];
    }
}
