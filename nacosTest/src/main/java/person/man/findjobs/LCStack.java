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
        String s = "1-(     -2)";
        int res = new LCStack().calculate(s);
        System.out.println(res);
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
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                stringBuilder.append(s.charAt(i));
            }
        }
        Stack<String> nums = new Stack<>();
        Stack<String> operators = new Stack<>();
        String s1 = s.replace(" ", "");
        int i = 0;
        s = stringBuilder.toString();
        while (i < s.length()) {
            char ch = s.charAt(i);
            if (ch == ' ') {
                i++;
                continue;
            }
            if (ch == ')') {
                while (!operators.peek().equals("(")) {
                    calcu(nums, operators);
                }
                operators.pop();//弹出（
                i++;
                continue;
            }
            if (ch == '+' || ch == '-') {
                if (i == 0 || s.charAt(i - 1) == '(') {
                    nums.push("0");
                }
                if (operators.isEmpty() || operators.peek().equals("(")) {
                    operators.push(String.valueOf(ch));
                } else {//这里可以添加优先级处理 包正操作栈里的优先级是从小到大的
                    calcu(nums, operators);
                    operators.push(String.valueOf(ch));
                }
                i++;
                continue;
            }
            if (ch != '(' && ch != ')') {
                Integer num = Integer.parseInt(String.valueOf(ch));
                i++;
                while (i < s.length() && (s.charAt(i) >= '0' && s.charAt(i) <= '9')) {
                    num = num * 10 + Integer.parseInt(String.valueOf(s.charAt(i)));
                    i++;
                }
                nums.push(String.valueOf(num));
                continue;
            }
            if (ch == '(') {
                operators.push(String.valueOf(ch));
            }
            i++;

        }
        if (!operators.isEmpty()) {
            calcu(nums, operators);
        }
        return Integer.parseInt(nums.pop());

    }

    private void calcu(Stack<String> nums, Stack<String> op) {
        int bb = Integer.parseInt(nums.pop());
        int aa = Integer.parseInt(nums.pop());
        String opp = op.pop();
        switch (opp) {
            case "+":
                nums.push(String.valueOf(aa + bb));
                break;
            case "-":
                nums.push(String.valueOf(aa - bb));
                break;
            default:
        }
    }
}
