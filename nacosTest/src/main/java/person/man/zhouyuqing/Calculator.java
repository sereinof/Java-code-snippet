package person.man.zhouyuqing;


import java.util.ArrayList;
import java.util.Optional;
import java.util.Stack;

public class Calculator {

    public static int calculate(String s) {
        if (s == null || s.isEmpty()) return 0;
        int length = s.length();
        int currentNumber = 0, lastNumber = 0, result = 0;
        char operation = '+';
        for (int i = 0; i < length; i++) {
            char currentChar = s.charAt(i);
            if (Character.isDigit(currentChar)) {
                currentNumber = (currentNumber * 10) + (currentChar - '0');
            }
            if (!Character.isDigit(currentChar) && !Character.isWhitespace(currentChar) || i == length - 1) {
                if (operation == '+' || operation == '-') {
                    result += lastNumber;
                    lastNumber = (operation == '+') ? currentNumber : -currentNumber;
                } else if (operation == '*') {
                    lastNumber = lastNumber * currentNumber;
                } else if (operation == '/') {
                    lastNumber = lastNumber / currentNumber;
                }
                operation = currentChar;
                currentNumber = 0;
            }
        }
        result += lastNumber;
        return result;
    }
    public int maxProduct(int[] nums) {
        int[] dp = new int[nums.length];
        int[] dp_min = new int[nums.length];
        dp[0]=nums[0];
        dp_min[0] = nums[0];
        for(int i= 1;i<nums.length;i++){
            dp[i] =  Math.max( Math.max(dp[i-1]*nums[i],nums[i]),Math.max(dp[i-1],dp_min[i-1]*nums[i]));
            dp_min[i] = Math.min(Math.min(nums[i]*dp_min[i-1],nums[i]),Math.min(dp[i-1],dp[i-1]*nums[i]));

        }
        int asn =   Integer.MIN_VALUE;;
        for(int i :dp){
            if(i>asn){
                asn = i;
            }
        }
        return asn;
    }
    public static void main(String[] args) {
        String expression = "1+2*3+4/2-3";
        ArrayList a = new ArrayList();
        Stack<Integer> b = new Stack();
         Integer min =  b.stream().min((x, y)->x-y).get();

        System.out.println(calculate(expression));  // 输出结果应该为6
    }
}

