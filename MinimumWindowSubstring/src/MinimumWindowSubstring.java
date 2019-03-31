import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        if (s == null || s.length() == 0 || s.length() < t.length()) {
            return "";
        }
        Map<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0)+1);
        }

        int minLength = s.length()+1;
        int minLeft = 0;
        int left = 0;
        int count = 0;

        char[] arr = s.toCharArray();

        for (int right = 0; right < arr.length; ++right) {
            if (map.containsKey(arr[right])) {
                map.put(arr[right], map.get(arr[right])-1);
                if (map.get(arr[right]) >= 0) {
                    count++;
                }

                while(count == t.length()) {
                    if (minLength > right-left+1) {
                        minLength = right-left+1;
                        minLeft = left;
                    }
                    if (map.containsKey(arr[left])) {
                        map.put(arr[left], map.get(arr[left])+1);
                        if (map.get(arr[left]) > 0) {
                            count--;
                        }
                    }
                    left++;
                }
            }
        }
        if (minLength > s.length()) {
            return "";
        }
        return s.substring(minLeft, minLeft+minLength);
    }
}
