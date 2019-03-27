import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BasicCalculator3 {
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Queue<Character> q = new LinkedList<>();
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) != ' ') {
                q.offer(s.charAt(i));
            }
        }
        q.offer('+');

        return helper(q);
    }

    private int helper(Queue<Character> q) {
        char sign = '+';
        int num = 0;
        Stack<Integer> stack = new Stack<>();
        int sum = 0;
        while(!q.isEmpty()) {
            char c = q.poll();

            if (c >= '0' && c <= '9') {
                num = num*10 + (c-'0');
            }
            else if (c == '(') {
                num = helper(q);
            }
            else {
                if (sign == '+') {
                    stack.push(num);
                }
                else if (sign == '-') {
                    stack.push(-num);
                }
                else if (sign == '*') {
                    stack.push(stack.pop() * num);
                }
                else if (sign == '/') {
                    stack.push(stack.pop()/num);
                }
                num = 0;
                sign = c;
                if (sign == ')') {
                    break;
                }
            }
        }
        while(!stack.isEmpty()) {
            sum += stack.pop();
        }
        return sum;
    }
}
