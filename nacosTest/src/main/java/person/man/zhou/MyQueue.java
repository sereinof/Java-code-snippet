package person.man.zhou;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Stack;

class MyQueue {
    Deque<Integer> A = new ArrayDeque<Integer>();
    Deque<Integer> B = new ArrayDeque();

    public MyQueue() {

    }

    public void push(int x) {
        A.push(x);
    }

    public int pop() {
        if (!B.isEmpty()) {
            return B.pop();
        } else {
            while (!A.isEmpty()) {
                B.push(A.pop());
            }
            return B.pop();
        }
    }

    public int peek() {
        if (!B.isEmpty()) {
            return B.peek();
        } else {
            while (!A.isEmpty()) {
                B.push(A.pop());
            }
            return B.peek();
        }
    }

    public boolean empty() {
        return A.isEmpty() && B.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
