package person.man.findjobs;

import java.util.Stack;

public class LCStack {

    public boolean isValid(String s) {
        int a = 0;
        int b = 0;
        int c = 0;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '{') {
                stack.push('{');
            }
            if (ch == '[') {
                stack.push('[');
            }
            if (ch == '(') {
                stack.push('(');
            }
            if (ch == '}') {
                if (stack.isEmpty()) {
                    return false;
                }
                if (stack.peek() != '{') {
                    return false;
                } else {
                    stack.pop();
                }
            }
            if (ch == ']') {
                if (stack.isEmpty()) {
                    return false;
                }
                if (stack.peek() != '[') {
                    return false;
                } else {
                    stack.pop();
                }
            }
            if (ch == ')') {
                if (stack.isEmpty()) {
                    return false;
                }
                if (stack.peek() != '(') {
                    return false;
                } else {
                    stack.pop();
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String s = "/home/";
        String res = new LCStack().simplifyPath(s);
    }

    public String simplifyPath(String path) {
        int l = 0;
        Stack<String> stack = new Stack<>();
        while (l < path.length()) {
            while (l < path.length() && path.charAt(l) == '/') {
                l++;
            }
            int r = l;
            while (r < path.length() && path.charAt(r) != '/') {
                r++;
            }
            String s = path.substring(l, r);
            if (s.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (s.length() != 0 && !".".equals(s)) {
                stack.push(s);
            }
            l = r;
        }
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.insert(0, stack.pop());
            res.insert(0, "/");
        }
        if (res.length() == 0) {
            return "/";
        }
        return res.toString();
    }

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            try {
                Integer integer = Integer.parseInt(token);
                stack.push(integer);

            } catch (Exception e) {
                int val = stack.pop();
                int val1 = stack.pop();
                int result = 0;
                if (token.equals("+")) {
                    result = val1 + val;
                } else if (token.equals("/")) {
                    result = val1 / val;
                } else if (token.equals("*")) {
                    result = val1 * val;
                } else if (token.equals("-")) {
                    result = val1 - val;
                }
                stack.push(result);
            }
        }
        return stack.pop();
    }

    public int calculate(String s) {

    }
}
