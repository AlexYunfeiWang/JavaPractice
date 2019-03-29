public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if (s.equals(t)) {
            return true;
        }
        if (s.length() != t.length()) {
            return false;
        }

        int[] map = new int[256];

        for (char c : s.toCharArray()) {
            map[c]++;
        }

        for (char c : t.toCharArray()) {
            map[c]--;
        }

        for (int i : map) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }
}
