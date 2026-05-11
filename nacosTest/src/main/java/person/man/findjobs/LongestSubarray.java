package person.man.findjobs;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class LongestSubarray {
    public static void main(String[] args) {
        int[] nums = new int[]{8};
        int limit = 10;
        System.out.println("result = " + longestSubarray(nums, limit));
    }

    public static int longestSubarray(int[] nums, int limit) {
        //滑动窗口加单调栈  给你一个整数数组 nums ，和一个表示限制的整数 limit，
        // 请你返回最长连续子数组的长度，该子数组中的任意两个元素之间的绝对差必须小于或者等于 limit。
        //求满足的子数组的 长度最大值
//思路
        Deque<Integer> max = new ArrayDeque<Integer>();
        Deque<Integer> min = new ArrayDeque<Integer>();
        int L = 0;
        int R = 0;
        int res = 0;
        if (limit > 0) {
            res = 1;
        }
        max.addFirst(L);
        min.addFirst(L);
        while (L < nums.length && R <= nums.length) {
            if (R < nums.length - 1) {
                R++;
                maintanStack("in", R, nums, max, min);
                if (nums[(int) max.getLast()] - nums[(int) min.getLast()] > limit) {//不合法
                    L++;//从新选一个开头
                    maintanStack("out", L - 1, nums, max, min);
                } else {//合法的话 可能需要统计子数组的数量 但是力扣这题不需要 它要最长的 子数组虽合法 但不是最长 
                    int newVal = R - L + 1;
                    res = Math.max(newVal, res);
                }
            } else {
                break;
            }
        }
        ;
        return res;
    }

    public static void maintanStack(String type, int index, int[] nums, Deque max, Deque min) {
        if ("in".equals(type)) {//进栈
            if (max.isEmpty()) {
                max.addFirst(index);
            } else {
                while (!max.isEmpty() && nums[(int) max.getFirst()] < nums[index]) {
                    max.removeFirst();
                }
                max.addFirst(index);
            }

            //
            if (min.isEmpty()) {
                min.addFirst(index);
            } else {
                while (!min.isEmpty() && nums[(int) min.getFirst()] > nums[index]) {
                    min.removeFirst();
                }
                min.addFirst(index);
            }
        } else {
            if (index == (int) max.getLast()) {
                max.removeLast();
            }
            if (index == (int) min.getLast()) {
                min.removeLast();
            }
        }
    }
}
