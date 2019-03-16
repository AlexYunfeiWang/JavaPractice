import java.util.*;

public class Solution {
    /**
     * @param s: the given expression
     * @return: the result of expression
     */
    public int calculate(String s) {
        // Write your code here
        if (s == null || s.length() == 0) {
            return 0;
        }
        Stack<String> stack = new Stack<>();

        long sum = 0;

        char[] arr = s.toCharArray();
        int i = 0;

        while(i < arr.length) {
            String number = "";
            if (arr[i] == ' ') {
                i++;
                continue;
            }
            if (arr[i] >= '0' && arr[i] <= '9') {
                while (i < arr.length && arr[i] >= '0' && arr[i] <= '9') {
                    number += arr[i];
                    i++;
                }
                //System.out.println("number：" + number);
                stack.push(number);
            }
            else if (arr[i] == '+' || arr[i] == '-') {
                stack.push(s.substring(i, i+1));
                i++;
            }
            else if (arr[i] == '*') {
                long first = Long.valueOf(stack.pop());
                i++;
                long second = 0;
                while (i < arr.length && arr[i] >= '0' && arr[i] <= '9') {
                    second = second * 10 + (arr[i] - '0');
                    i++;
                }
                stack.push(String.valueOf(first*second));
            }
            else if (arr[i] == '/') {
                long first = Long.valueOf(stack.pop());
                i++;
                long second = 0;
                while (i < arr.length && arr[i] >= '0' && arr[i] <= '9') {
                    second = second * 10 + (arr[i] - '0');
                    i++;
                }
                if (second != 0) {
                    stack.push(String.valueOf(first/second));
                }

            }
        }

        Iterator<String> it = stack.iterator();
        while(it.hasNext()) {
            String cur = it.next();
            System.out.println(cur);
            if (cur.equals("-")) {
                if (it.hasNext()) {
                    sum -= Long.valueOf(it.next());
                }
            }
            else if (cur.equals("+")) {
                continue;
            }
            else {
                sum += Long.valueOf(cur);
            }
        }
        return (int)sum;
    }
}