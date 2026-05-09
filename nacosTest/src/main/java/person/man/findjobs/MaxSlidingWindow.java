package person.man.findjobs;

import java.util.ArrayDeque;
import java.util.Deque;

public class MaxSlidingWindow {
    //左神第一课 滑动窗口最大值
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        Deque<int[]> deque = new ArrayDeque<>();
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
            int indexEnter = nums[i + k - 1];
            int valueEnter = nums[indexEnter];
            if (deque.isEmpty()) {
                int[] elemnt = new int[]{indexEnter, valueEnter};
                deque.addFirst(elemnt);
            } else {//
                while (!deque.isEmpty() && deque.getFirst()[1] <= nums[i]) {
                    deque.removeFirst();
                }
                deque.addFirst(new int[]{indexEnter, valueEnter});
            }
            //出
            int indexOut = i - 1;
            int ValueOut = nums[indexOut];
            if (!deque.isEmpty() && deque.getLast()[0] == indexOut)
                //写入结果数组
                res[i] = deque.getFirst()[1];
        }

        return res;
    }
}
