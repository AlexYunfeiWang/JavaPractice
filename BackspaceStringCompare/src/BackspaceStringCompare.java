import java.util.Stack;

public class BackspaceStringCompare {
    public boolean backspaceCompare(String S, String T) {
        if (S.equals(T)) {
            return true;
        }
        return parseStr(S).equals(parseStr(T));
    }

    private String parseStr(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '#') {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            }
            else {
                stack.push(c);
            }
        }
        String res = "";
        while(!stack.isEmpty()) {
            res = stack.pop() + res;
        }
        return res;
    }
}
