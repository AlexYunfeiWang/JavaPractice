import java.util.ArrayList;
import java.util.List;

public class PalindromePermutation2 {
    public List<String> generatePalindromes(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return res;
        }
        int numOdds = 0;
        String mid = "";
        int halfLength = 0;

        int[] map = new int[256];

        for (char c : s.toCharArray()) {
            map[c]++;
            numOdds = map[c] % 2 == 1 ? numOdds+1 : numOdds-1;
        }

        if (numOdds > 1) {
            return res;
        }

        for (int i = 0; i < map.length; ++i) {
            if (map[i] > 0) {
                if (map[i] % 2 == 1) {
                    map[i]--;
                    mid = "" + (char)i;
                }
                map[i] /= 2;
                halfLength += map[i];
            }
        }
        DFS(map, halfLength, res, "", mid);
        return res;
    }

    private void DFS(int[] map, int halfLength, List<String> res, String path, String mid) {
        if (path.length() == halfLength) {
            StringBuilder sb = new StringBuilder(path).reverse();
            res.add(path + mid + sb.toString());
        }
        for (int i = 0; i < map.length; ++i) {
            if (map[i] > 0) {
                map[i]--;
                DFS(map, halfLength, res, path+(char)i, mid);
                map[i]++;
            }
        }
    }
}
