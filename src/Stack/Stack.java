package Stack;

import java.util.LinkedList;
import java.util.List;


public class Stack {
    LinkedList<Integer> stackList = new LinkedList<>();

    public void push(int value) {
        stackList.add(value);
    }
    public int pop() {
        if (stackList.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stackList.removeLast();
    }

    public int top() {
        if (stackList.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stackList.getLast();
    }

}
