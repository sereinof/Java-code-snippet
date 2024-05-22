package person.man.zhou;

import java.util.Stack;

public class review {
    public static void main(String[] args) {
        String res = multiply("5678", "78");
        System.out.println(res);
    }

    public boolean isValid(String s) {
        if ("".equals(s)) {
            return true;
        }
        Stack<Character> stack = new Stack();
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                stack.push(')');
            } else if (ch == '{') {
                stack.push('}');
            } else if (ch == '[') {

                stack.push(']');
            } else {
                if (stack.isEmpty()) {
                    return false;
                } else if (stack.peek() != ch) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        if (num1.length() < num2.length()) {
            String tmp = num1;
            num1 = num2;
            num2 = tmp;
        }
        int len2 = num2.length();
        int[] arr = new int[num1.length() + num2.length()];
        for (int i = 0; i < num1.length(); i++) {
            for (int j = 0; j < num2.length(); j++) {
                arr[i + j + 1] += (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
            }
        }
        StringBuilder res = new StringBuilder();
        for (int j = arr.length - 1; j > 0; j--) {
            int nun = arr[j] % 10;
            int carry = arr[j] / 10;
            arr[j - 1] += carry;
            res.insert(0, nun);
        }
        if (arr[0] != 0) {
            res.insert(0, arr[0]);
        }
        return res.toString();
    }
}
///        5 6 7 8
//            7 8
//----------------
//      40 48 56 64
//   35 42 49 56