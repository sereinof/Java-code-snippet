package person.man.findjobs;

import org.checkerframework.checker.units.qual.A;

import java.util.*;

public class SumSubarrayMins {
    public static void main(String[] args) {
        int[] arr = new int[]{19,19,62,66};
        System.out.println("result = " + sumSubarrayMins(arr));
    }

    public static int sumSubarrayMins(int[] arr) {
        //使用单调栈解题 数组有重复值的
        //主要是熟悉一些这个单调栈的过程 只是听了课却没有去实践 还有股票矩形那道题也是单调栈
        int res = 0;
        Deque<List<Integer>> stack = new ArrayDeque();//需要自动扩容
        int[][] infoMatrix = new int[arr.length][2];//-1代表左边或者右边没有值
        for (int i = 0; i < infoMatrix.length; i++) {
            infoMatrix[i][0] = -1;
            infoMatrix[i][1] = -1;
        }
        for (int i = 0; i < arr.length; i++) {
            if (stack.isEmpty()) {//入栈
                ArrayList<Integer> elements = new ArrayList<>();
                elements.add(i);
                stack.push(elements);
            } else if (arr[(int) stack.peek().get(0)] < arr[i]) {//入栈 维持单调递增
                ArrayList<Integer> elements = new ArrayList<>();
                elements.add(i);
                stack.push(elements);
            } else if (arr[(int) stack.peek().get(0)] == arr[i]) {
                stack.peek().add(i);
            } else { //处理出栈//出栈这里也存在一个循环
                while (!stack.isEmpty() && arr[i] < arr[stack.peek().get(0)]) {
                    List<Integer> topElement = stack.pop();
                    for (int j = 0; j < topElement.size(); j++) {
                        int index = topElement.get(j);
                        if (!stack.isEmpty()) {
                            List<Integer> bottom = stack.peek();
                            infoMatrix[index][0] = bottom.get(bottom.size() - 1);
                        }
                        infoMatrix[index][1] = i;
                    }
                }

                ArrayList<Integer> elements = new ArrayList<>();
                elements.add(i);
                stack.push(elements);

            }

        }
       while (!stack.isEmpty()) {
            List<Integer> topElement = stack.pop();
            for (int j = 0; j < topElement.size(); j++) {
                int index = topElement.get(j);
                if (!stack.isEmpty()) {
                    List<Integer> bottom = stack.peek();
                    infoMatrix[index][0] = bottom.get(bottom.size() - 1);
                }
                //infoMatrix[index][1]=;
            }
        }
        //获取完信息了
        //求到了这个区间 但是还得求包含这个最小值的所有子数组 这里还用到了排列组合的方法 左边有 n种 右边有 m种 则是 m*n
        //好神奇
        final int MOD = 1000000007;
        for (int i = 0; i < arr.length; i++) {
            int left = infoMatrix[i][0];
            int right = infoMatrix[i][1] == -1 ? arr.length : infoMatrix[i][1];
            int partRes = (i - left) * (right - i) * arr[i];
            res += partRes % MOD;
        }
        return res;
    }
}
