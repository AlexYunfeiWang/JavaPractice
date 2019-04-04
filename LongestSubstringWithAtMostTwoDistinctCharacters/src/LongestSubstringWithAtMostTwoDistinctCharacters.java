import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithAtMostTwoDistinctCharacters {
    /*
    Idea: Same thing as Leetcode 904: Fruit Into Baskets
    Sliding window method
    */
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] arr = s.toCharArray();

        int max = 0;

        int left = 0;

        Map<Character, Integer> map = new HashMap<>();

        int uniqueCount = 0;

        for (int right = 0; right < arr.length; ++right) {
            if (!map.containsKey(arr[right])) {
                uniqueCount++;
            }
            map.put(arr[right], map.getOrDefault(arr[right], 0)+1);

            while(uniqueCount > 2) {
                map.put(arr[left], map.get(arr[left])-1);
                if (map.get(arr[left]) == 0) {
                    uniqueCount--;
                    map.remove(arr[left]);
                }
                left++;
            }
            max = Math.max(max, right-left+1);
        }
        return max;
    }
}
