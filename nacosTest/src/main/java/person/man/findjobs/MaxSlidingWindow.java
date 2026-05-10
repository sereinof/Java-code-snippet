package person.man.findjobs;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class MaxSlidingWindow {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        System.out.println("res = " + Arrays.toString(maxSlidingWindow(nums, 3)));
    }

    //左神第一课 滑动窗口最大值
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        Deque<int[]> deque = new ArrayDeque<>();//需要改进 只需要存下标就好了 通过下标去 nums 里面找值
        for (int i = 0; i < k; i++) {
            if (deque.isEmpty()) {
                int[] elemnt = new int[]{i, nums[i]};
                deque.addFirst(elemnt);
            } else {//
                while (!deque.isEmpty() && deque.getFirst()[1] <= nums[i]) {
                    deque.removeFirst();
                }
                deque.addFirst(new int[]{i, nums[i]});
            }
        }//初始化窗口
        res[0] = deque.getLast()[1];
        for (int i = 1; i <= nums.length - k; i++) {
            //进 出 进出之后将队列左边写入 res数组
            //进
            int indexEnter = i + k - 1;
            int valueEnter = nums[indexEnter];
            if (deque.isEmpty()) {
                int[] elemnt = new int[]{indexEnter, valueEnter};
                deque.addFirst(elemnt);
            } else {//
                while (!deque.isEmpty() && deque.getFirst()[1] <= nums[indexEnter]) {
                    deque.removeFirst();
                }
                deque.addFirst(new int[]{indexEnter, valueEnter});
            }
            //出
            int indexOut = i - 1;
            if (!deque.isEmpty() && deque.getLast()[0] == indexOut){
                deque.removeLast();
            }
                //写入结果数组
                res[i] = deque.getLast()[1];
        }

        return res;
    }
}
