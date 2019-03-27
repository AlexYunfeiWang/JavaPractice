
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

// exact_match("apple", "ppl") == true
// wildcard_match("apple", "p.l") == true
// wildcard_match"("apple", "pple.") == false
// question_wildcard_match("apple", "pple.?") == true
// question_wildcard_match("apple", "px?y?z?p?l?e") == true

public class RegexMatch {
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        System.out.println(exact_match("apple", "ppl"));
        System.out.println(wildcard_match("apple", "p.l"));
        System.out.println(wildcard_match("apple", "pple."));
        System.out.println(question_wildcard_match("apple", "pple.?"));
        System.out.println(question_wildcard_match("apple", "px?y?z?p?l?e"));
    }

    public static boolean regexMatch(String text, String query) {
        if (text == null || text.length() == 0 || query == null || query.length() == 0) {
            return false;
        }

        //case1: exact_match
        if (query.indexOf(".") < 0 && query.indexOf("?") < 0) {
            return exact_match(text, query);
        }

        //case2
        if (query.indexOf(".") >= 0) {
            return wildcard_match(text, query);
        }

        //case3
        if (query.indexOf("?") >= 0) {
            return question_wildcard_match(text, query);
        }
        return false;
    }

    private static boolean exact_match(String text, String query) {
        return text.indexOf(query) >= 0;
    }

    private static boolean wildcard_match(String text, String query) {
        if (query.length() > text.length()) {
            return false;
        }
        int k = 0;
        for (; k < query.length(); ++k) {
            if (query.charAt(k) != '.') {
                break;
            }
        }
        query = query.substring(k);

        List<Integer> start = new ArrayList<>();
        for (int i = 0; i < text.length(); ++i) {
            if (text.charAt(i) == query.charAt(0)) {
                start.add(i);
            }
        }
        if (start.size() == 0) {
            return false;
        }

        for (int begin : start) {
            int i = begin;
            int j = 0;
            while(i < text.length() && j < query.length()) {
                if (query.charAt(j) == '.') {
                    i++;
                    j++;
                }
                else if (query.charAt(j) != text.charAt(i)) {
                    return false;
                }
                i++;
                j++;
            }

        }
        return true;
    }

    private static boolean question_wildcard_match(String text, String query) {
        /*
        for question mark and dot combination, there are 2 cases:
        1. if both are letters, then dp[i][j] = dp[i-1][j-1]
        2. if query.charAt(j) == '.', then dp[i][j] has the same case from case 1
        3. if query.charAt(j) == '?', then there are 3 other cases considered:
            - query.charAt(j-1) != text.charAt(i) && query.charAt(j-1) != '.', dp[i][j] = dp[i][j-2]  x? counts 0,
            - query.carAt(j-1) == text.charAt(i) or query.charAt(j-1) == '.', dp[i][j] = dp[i][j-1] x? counts for 1,
            - query.charAt(j-1) == text.charAt(i) or query.charAt(j-1) == '.', dp[i][j] = dp[i-1][j], x? counts for more
            - query.carAt(j-1) == text.charAt(i) or query.charAt(j-1) == '.', dp[i][j] = dp[i][j-2]x? counts for 0
        */

        for (int idx = 0; idx < text.length(); ++idx) {
            String sub = text.substring(idx);
            //System.out.println(sub);
            boolean[][] dp = new boolean[sub.length()+1][query.length()+1];

            dp[0][0] = true;

            for (int i = 0; i < query.length(); ++i) {
                if (query.charAt(i) == '?' && dp[0][i-1]) {
                    dp[0][i+1] = true;
                }
            }

            for (int i = 0; i < sub.length(); ++i) {
                for (int j = 0; j < query.length(); ++j) {
                    if (sub.charAt(i) == query.charAt(j)) {
                        dp[i+1][j+1] = dp[i][j];
                    }
                    if (query.charAt(j) == '.') {
                        dp[i+1][j+1] = dp[i][j];
                    }
                    if (query.charAt(j) == '?') {
                        if (query.charAt(j-1) != sub.charAt(i) && query.charAt(j-1) != '.') {
                            dp[i+1][j+1] = dp[i+1][j-1];
                        }
                        else {
                            dp[i+1][j+1] = dp[i+1][j] || dp[i][j+1] || dp[i+1][j-1];
                        }
                    }
                }
            }
            if (dp[sub.length()][query.length()]) {
                return true;
            }
        }
        return false;
    }
}
