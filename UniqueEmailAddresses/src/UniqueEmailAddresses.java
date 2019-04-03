import java.util.HashSet;
import java.util.Set;

public class UniqueEmailAddresses {
    public int numUniqueEmails(String[] emails) {
        if (emails == null || emails.length == 0) {
            return 0;
        }

        Set<String> set = new HashSet<>();

        for(String s : emails) {
            int mark = s.indexOf("@");
            String revised = reviseString(s.substring(0, mark));
            String newStr = revised + s.substring(mark);
            if (!set.contains(newStr)) {
                set.add(newStr);
            }
        }

        return set.size();
    }

    private String reviseString(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == '.') {
                continue;
            }
            else if (c == '+') {
                break;
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
