package person.man.findjobs;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class mergeTwoOrderArr {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int mp = m - 1;
        int np = n - 1;
        for (int i = m + n - 1; i >= 0; i--) {
            if (mp >= 0 && (np < 0 || nums1[mp] >= nums2[np])) {
                nums1[i] = nums1[mp];
                mp--;
            } else {
                nums1[i] = nums2[np];
                np--;
            }

        }
    }

    public static void main(String[] args) {
        //int[] nums = new int[]{0, 1, 2, 2, 3, 0, 4, 2};
        // removeElement(nums, 2);
        int nums[] = new int[]{1,2};
        // new mergeTwoOrderArr().removeDuplicates(nums);
        new mergeTwoOrderArr().rotate(nums, 1);
    }

    public static int removeElement(int[] nums, int val) {
        if (nums.length == 0) {
            return 0;
        }
        int res = 0;
        int i = 0;
        int j = nums.length - 1;
        for (int k = 0; k < nums.length; k++) {
            if (nums[k] != val) {
                res++;
            }
        }
        while (i != j) {
            if (nums[i] == val) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j--;
            } else {
                i++;
            }
        }
        return res;
    }

    public void delete(int[] nums, int i) {
        for (int j = i; j <= nums.length - 2; j++) {
            nums[j] = nums[j + 1];
        }
    }

    public int removeDuplicates(int[] nums) {
        int k = 1;
        int p = 1;
        while (p < nums.length) {
            if (nums[p] == nums[p - 1]) {
                p++;
            } else {
                nums[k] = nums[p];
                k++;
                p++;
            }
        }
        return k;
    }

    public int majorityElement(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
//先用哈希表走一遍吧  //参考别人的解法  使用摩尔投票做一遍吧
        int count = 1;
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if (num == res) {
                count++;
            } else {
                if (count > 0) {
                    count--;
                } else {
                    res = num;
                    count = 1;
                }
            }
        }
        return res;
    }

    public void rotate(int[] nums, int k) {
        if (nums.length <= 1) {
            return;
        }
        if (k == 0) {
            return;
        }
        int finalK = k % nums.length;
        int s = nums[0];
        int index = finalK;
        int start = index;
        for (int i = 0; i < nums.length; i++) {
            int nextindex = index + finalK;
            if (nextindex >= nums.length) {
                nextindex = nextindex % nums.length;
            }
            int temp = nums[index];
            nums[index] = s;
            s = temp;

            index = nextindex;
            if (index == start && i < nums.length-1) {
                ++index;
                s = nums[index];
                index = index + finalK;
                if (index >= nums.length) {
                    index = index - nums.length;
                }
                start = index;
            }
        }
    }

    public void reverseArr(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int l = start;
        int r = end;
        while (l < r) {
            int temp = nums[r];
            nums[r] = nums[l];
            nums[l] = temp;
            l++;
            r--;
        }
    }

    public int maxProfit(int[] prices) {
 //买卖股票 问题转化为子问题 每一天买入股票的最大可能收获是多少
        int max = 0;
        int pay = 0;
        for (int i = 0; i <prices.length-1 ; i++) {
            pay  = prices[i];
            for (int j = i+1; j <prices.length ; j++) {
                 if(prices[j]-pay>max){
                     max= prices[j]-pay;
                 }
            }
        }
        return max;
    }
}
