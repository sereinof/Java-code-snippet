package person.man.zhou;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

class MyStack {
    Queue<Integer> A = new LinkedList();
    Queue<Integer> B = new LinkedList();

    public MyStack() {

    }

    public void push(int x) {
        if (!A.isEmpty()) {
            A.offer(x);
        } else {
            B.offer(x);
        }
    }

    public int pop() {
        if (A.isEmpty()) {
            while (!B.isEmpty()) {
                if (B.size() > 1) {
                    A.offer(B.poll());
                } else {
                    return B.poll();
                }
            }
        } else {
            while (!A.isEmpty()) {
                if (A.size() > 1) {
                    B.offer(A.poll());
                } else {
                    return A.poll();
                }
            }
        }
        return 0;
    }

    public int top() {
        HashMap map = new HashMap();
        if (A.isEmpty()) {
            while (!B.isEmpty()) {
                if (B.size() > 1) {
                    A.offer(B.poll());
                } else {
                    int a = B.poll();
                    A.offer(a);
                    return a;
                }
            }
        } else {
            while (!A.isEmpty()) {
                if (A.size() > 1) {
                    B.offer(A.poll());
                } else {
                    int a = A.poll();
                    B.offer(a);
                    return a;
                }
            }
        }
        return 0;
    }

    public boolean empty() {
        return A.isEmpty() && B.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
