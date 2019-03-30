import java.util.ArrayList;
import java.util.List;

public class TextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        if (words == null || words.length == 0) {
            return res;
        }

        int start = 0;
        int curLength = 0;
        int n = words.length;

        for (int i = 0; i <= n; ++i) {
            if (i == n || curLength + words[i].length() + i - start > maxWidth) {

                int totalRemainingSpace = maxWidth - curLength;

                StringBuilder sb = new StringBuilder();

                while(start < i) {
                    sb.append(words[start]);

                    int spaceAfterWord = 0;

                    //when we get to the last word of this line, whatever spaces remain will be put together
                    if (start == i-1) {
                        spaceAfterWord = totalRemainingSpace;
                    }
                    //according to question,
                    //"For the last line of text, it should be left justified and no extra space is inserted between words."
                    else if (i == n) {
                        spaceAfterWord = 1;
                    }
                    //"Extra spaces between words should be distributed as evenly as possible. "
                    else {
                        spaceAfterWord = (int)Math.ceil((double)totalRemainingSpace/(i - start - 1));
                    }

                    totalRemainingSpace -= spaceAfterWord;

                    while(spaceAfterWord-- > 0) {
                        sb.append(" ");
                    }
                    start++;
                }
                curLength = 0;
                res.add(sb.toString());
            }
            if (i < n) {
                curLength += words[i].length();
            }
        }
        return res;
    }
}
