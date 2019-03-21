public class ValidNumber {

    public boolean isNumber(String s) {
        int i = 0;
        s = s.trim();
        if (s.length() == 0) {
            return false;
        }

        if (s.charAt(i) == '+' || s.charAt(i) == '-') {
            i++;
        }

        boolean num = false; //is a digit
        boolean dot = false; //is a '.'
        boolean exp = false; //is a 'e'

        while(i < s.length()) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                num = true;
            }
            else if (c == '.') {
                if (dot || exp) {
                    //if '.' already exists or if it is an exp number, return false
                    return false;
                }
                dot = true;
            }
            else if (c == 'e') {
                if (exp || !num || i == s.length()-1) {
                    //if exp already exists
                    //if previous char is not a number
                    //if 'e' is the last one in String
                    return false;
                }
                exp = true;
            }
            else if (c == '+' || c == '-') {
                if (s.charAt(i-1) != 'e' || i == s.length()-1) {
                    //i.e. 2e+10 is valid, so the previous char must be a number
                    //or if this is the last one in String
                    return false;
                }
            }
            else {
                return false;
            }
            i++;
        }
        return num;
    }
}
