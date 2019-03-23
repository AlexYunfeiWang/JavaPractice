public class LongestSubstringWithAtLeastKRepeatingCharacters {
    /*
    Use divide and conquer, every character that doesn't have K frequency can be seen as a divider,
    For each divider:
    calculate the longest from left side of the divider and the longest from right side of the divider
    */
    public int longestSubstring(String s, int k) {
        char[] str = s.toCharArray();
        return helper(str, k, 0, str.length);
    }

    public int helper(char[] str, int k, int start, int end) {
        if (end - start < k) {
            return 0;
        }
        int[] count = new int[26];

        for (int i = start; i < end; ++i) {
            count[str[i] - 'a']++;
        }

        for (int i = 0; i < 26; ++i) {

            if (count[i] < k && count[i] > 0) {
                for (int j = start; j < end; ++j) {
                    if (str[j] == i+'a') {
                        int left = helper(str, k, start, j);
                        int right = helper(str, k, j+1, end);
                        return Math.max(left, right);
                    }
                }
            }
        }
        return end - start;
    }
}
