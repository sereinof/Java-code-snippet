package person.man.zhou;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

class MyStack {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode res = new ListNode(-1);
        ListNode pointer = res;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                int val = (l2.val + carry) % 10;
                carry = (l2.val + carry) / 10;
                pointer.next = new ListNode(val);
                pointer = pointer.next;
                l2 = l2.next;
                continue;
            }
            if (l2 == null) {
                int val = (l1.val + carry) % 10;
                carry = (l1.val + carry) / 10;
                pointer.next = new ListNode(val);
                pointer = pointer.next;
                l1 = l1.next;
                continue;
            }
            int val = (carry + l1.val + l2.val) % 10;
            carry = (carry + l1.val + l2.val) / 10;
            pointer.next = new ListNode(val);
            pointer = pointer.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        if (carry != 0) {
            pointer.next = new ListNode(carry);
        }
        return res.next;
    }

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
