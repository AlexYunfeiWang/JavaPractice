import java.util.Stack;

public class ValidParentheses {
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == '(' && c == ')') {
                stack.pop();
            }
            else if (!stack.isEmpty() && stack.peek() == '[' && c == ']') {
                stack.pop();
            }
            else if (!stack.isEmpty() && stack.peek() == '{' && c == '}') {
                stack.pop();
            }
            else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
}
