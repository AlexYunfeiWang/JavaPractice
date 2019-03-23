import java.util.Stack;

public class ImplementQueueUsingStacks {
    Stack<Integer> stack1;
    Stack<Integer> stack2;

    /** Initialize your data structure here. */
    public ImplementQueueUsingStacks() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        stack1.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        while(stack1.size() > 1) {
            stack2.push(stack1.pop());
        }
        int item = stack1.pop();
        while(stack2.size() > 0) {
            stack1.push(stack2.pop());
        }
        return item;
    }

    /** Get the front element. */
    public int peek() {
        while(stack1.size() > 0) {
            stack2.push(stack1.pop());
        }
        int item = stack2.peek();
        while(stack2.size() > 0) {
            stack1.push(stack2.pop());
        }
        return item;
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack1.isEmpty();
    }
}
