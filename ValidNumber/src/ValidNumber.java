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
                if (exp || !num) {
                    //if exp already exists or previous char is not a number
                    return false;
                }
                exp = true;
            }
            else if (c == '+' || c == '-') {
                if (s.charAt(i-1) != 'e') {
                    //i.e. 2e+10 is valid, so the previous char must be a number
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
