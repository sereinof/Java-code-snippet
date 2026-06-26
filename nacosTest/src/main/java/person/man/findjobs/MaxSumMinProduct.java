package person.man.findjobs;

import java.util.*;

public class MaxSumMinProduct {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,2};
        System.out.println("result = " + maxSumMinProduct(nums));
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Long.MAX_VALUE);

        List immutable  = List.of("a","g");
    }

    public  static  int maxSumMinProduct(int[] nums) {
        long res = 0;
//此题1856题和 907题估计差不多 也都要模一下 也都要二维数组记录信息 并且 记录的方式都一样 尤其是对于连续重复值而言
        //然后的话 本题还需要构建前缀和 来简化流程 最后将前缀和 以及 单调栈生成的信息 加工一下得到答案 如此


        /*---------------------构建前缀和----------------------------*/
        long[] sumArr = new long[nums.length];
        long sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            sumArr[i] = sum;
        }
        /*-------------------------------------------------*/
        Deque<List<Integer>> stack = new ArrayDeque();//需要自动扩容
        int[][] infoMatrix = new int[nums.length][2];//-1代表左边或者右边没有值
        for (int i = 0; i < infoMatrix.length; i++) {
            infoMatrix[i][0] = -1;
            infoMatrix[i][1] = -1;
        }
        for (int i = 0; i < nums.length; i++) {
            if (stack.isEmpty()) {//入栈
                ArrayList<Integer> elements = new ArrayList<>();
                elements.add(i);
                stack.push(elements);
            } else if (nums[(int) stack.peek().get(0)] < nums[i]) {//入栈 维持单调递增
                ArrayList<Integer> elements = new ArrayList<>();
                elements.add(i);
                stack.push(elements);
            } else if (nums[(int) stack.peek().get(0)] == nums[i]) {
                stack.peek().add(i);
            } else { //处理出栈//出栈这里也存在一个循环
                while (!stack.isEmpty() && nums[i] < nums[stack.peek().get(0)]) {
                    List<Integer> topElement = stack.pop();
                    for (int j = 0; j < topElement.size(); j++) {
                        int index = topElement.get(j);
                        if (!stack.isEmpty() || j > 0) {
                            List<Integer> bottom = stack.peek();
                            infoMatrix[index][0] = j > 0 ? index - 1 : bottom.get(bottom.size() - 1);
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
                if (!stack.isEmpty() || j > 0) {
                    List<Integer> bottom = stack.peek();
                    infoMatrix[index][0] = j > 0 ? index - 1 : bottom.get(bottom.size() - 1);
                }
                //infoMatrix[index][1]=;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            int left = infoMatrix[i][0];
            int right = infoMatrix[i][1] == -1 ? nums.length : infoMatrix[i][1];
            long sumToi =0;
            if(left==-1){
                sumToi = sumArr[right - 1];
            }else{
               sumToi = sumArr[right - 1] - sumArr[left];
            }

            res = Long.max((long) res, (long) sumToi*nums[i]);

        }
        final long MOD = (long) 1e9 + 7;

        return (int) (res % MOD);
    }
}
