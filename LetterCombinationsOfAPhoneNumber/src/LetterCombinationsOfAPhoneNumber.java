import java.util.ArrayList;
import java.util.List;

public class LetterCombinationsOfAPhoneNumber {
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return res;
        }
        String[] map = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        helper(map, digits, 0, new StringBuilder(), res);
        return res;
    }

    public void helper(String[] map, String digits, int start, StringBuilder sb, List<String> res) {
        if (sb.length() == digits.length()) {
            res.add(sb.toString());
            return;
        }
        for (int i = start; i < digits.length(); ++i) {
            int idx = Integer.valueOf(digits.substring(i, i+1));
            for (int j = 0; j < map[idx].length(); ++j) {
                sb.append(map[idx].charAt(j));
                helper(map, digits, i+1, sb, res);
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }
}
