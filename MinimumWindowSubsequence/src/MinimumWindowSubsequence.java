public class MinimumWindowSubsequence {
    public String minWindow(String S, String T) {
        if (S.length() < T.length()) {
            return "";
        }
        String result = "";

        char[] s = S.toCharArray();
        char[] t = T.toCharArray();

        int sIndex = 0;
        int tIndex = 0;
        int sLength = s.length;
        int tLength = t.length;
        int minLen = Integer.MAX_VALUE;

        while(sIndex < sLength) {
            if (s[sIndex] == t[tIndex]) {
                tIndex++;

                //all t's characters are consumed, now need to recover/retrieve this substring
                if (tIndex == tLength) {
                    int substringEnd = sIndex+1; //sIndex is the last match index in S
                    tIndex--; //tIndex now is the last index of T
                    while(tIndex >= 0) {
                        if (s[sIndex] == t[tIndex]) {
                            tIndex--;
                        }
                        sIndex--;
                    }
                    sIndex++; //this is the beginning of the substring that contains T as subsequence
                    tIndex++; //now tIndex == 0
                    if (substringEnd - sIndex < minLen) {
                        minLen = substringEnd - sIndex;
                        result = S.substring(sIndex, substringEnd);
                    }
                }
            }
            sIndex++;
        }
        return minLen == Integer.MAX_VALUE ? "" : result;
    }
}
