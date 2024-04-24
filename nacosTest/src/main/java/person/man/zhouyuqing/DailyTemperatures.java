package person.man.zhouyuqing;

import java.util.Stack;

public class DailyTemperatures {
    public int[] dailyTemperatures(int[] temperatures) {
//借助栈结构
        //单调栈这结构也是很少遇见啊
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[temperatures.length];
        // stack.add();//入栈
        // stack.peak;
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.empty() && temperatures[stack.peek()] < temperatures[i]) {
                int index = stack.pop();
                res[index] = i - index;
            }
            stack.add(i);
        }
        return res;
    }
}
