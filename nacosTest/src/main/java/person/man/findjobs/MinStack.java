package person.man.findjobs;

import java.util.Stack;

public class MinStack {

    Stack<Integer> stack = new Stack<>();
    Stack<Integer> subStack = new Stack<>();

    public static void main(String[] args) {
        MinStack minStack = new MinStack();

        minStack.push(512);
        System.out.println("push(512)    -> 当前栈: [512],            最小值: " + minStack.getMin());

        minStack.push(-1024);
        System.out.println("push(-1024)  -> 当前栈: [512, -1024],      最小值: " + minStack.getMin());

        minStack.push(-1024);
        System.out.println("push(-1024)  -> 当前栈: [512, -1024, -1024], 最小值: " + minStack.getMin());

        minStack.push(512);
        System.out.println("push(512)    -> 当前栈: [512, -1024, -1024, 512], 最小值: " + minStack.getMin());

        minStack.pop();
        System.out.println("pop()        -> 当前栈: [512, -1024, -1024], 最小值: " + minStack.getMin());

        System.out.println("getMin()     -> 返回: " + minStack.getMin());  // -1024

        minStack.pop();
        System.out.println("pop()        -> 当前栈: [512, -1024],       最小值: " + minStack.getMin());

        System.out.println("getMin()     -> 返回: " + minStack.getMin());  // -1024

        minStack.pop();
        System.out.println("pop()        -> 当前栈: [512],             最小值: " + minStack.getMin());

        System.out.println("getMin()     -> 返回: " + minStack.getMin());
    }


    public MinStack() {

    }

    public void push(int value) {
        stack.push(value);
        if (subStack.isEmpty()) {
            subStack.push(value);
        } else {
            if (value <= subStack.peek()) {
           subStack.push(value);
            }
        }
    }

    public void pop() {
        if (subStack.peek().equals(stack.peek())) {
            subStack.pop();
            stack.pop();
        } else {
            stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        if (!subStack.isEmpty()) {
            return subStack.peek();
        } else {
            return stack.peek();
        }
    }
}
