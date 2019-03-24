import java.util.Stack;

public class BasicCalculator2 {
    public int calculate(String s) {
        s = s.trim();
        if (s.length() == 0) {
            return 0;
        }

        //Eliminate all the inner spaces
        String[] preProcess = s.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String part : preProcess) {
            sb.append(part);
        }
        s = sb.toString();

        char[] arr = s.toCharArray();

        Stack<Integer> stack = new Stack<>();
        int i = 0;

        while(i < arr.length) {
            if (arr[i] >= '0' && arr[i] <= '9') {
                int number = 0;
                int start = i;
                while (i < arr.length && arr[i] >= '0' && arr[i] <= '9') {
                    i++;
                }
                stack.push(Integer.valueOf(s.substring(start, i)));
                continue;
            }
            else if (arr[i] == '+') {
                i++;
            }
            else if (arr[i] == '-') {
                i++;
                if (arr[i] >= '0' && arr[i] <= '9') {
                    int number = 0;
                    int start = i;
                    while (i < arr.length && arr[i] >= '0' && arr[i] <= '9') {
                        i++;
                    }
                    stack.push((Integer.valueOf(s.substring(start, i))) * (-1));
                    continue;
                }
            }
            else if (arr[i] == '*') {
                i++;
                if (arr[i] >= '0' && arr[i] <= '9') {
                    int first = stack.pop();
                    int start = i;
                    while (i < arr.length && arr[i] >= '0' && arr[i] <= '9') {
                        i++;
                    }
                    int second = Integer.valueOf(s.substring(start, i));
                    stack.push(first * second);
                    continue;
                }
            }
            else if (arr[i] == '/') {
                i++;
                if (arr[i] >= '0' && arr[i] <= '9') {
                    int first = stack.pop();
                    int start = i;
                    while (i < arr.length && arr[i] >= '0' && arr[i] <= '9') {
                        i++;
                    }
                    int second = Integer.valueOf(s.substring(start, i));
                    stack.push(first / second);
                    continue;
                }
            }
        }

        int res = 0;
        while(!stack.isEmpty()) {
            res += stack.pop();
        }


        return res;
    }
}
