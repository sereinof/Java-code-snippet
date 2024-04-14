package person.man.zhouyuqing;

public class test_leetcode {

    public int findKthLargest(int[] nums, int k) {
        // 这个题目采用哥哥快拍的思想
        int piovt = nums[0];
        int pionter = 0;
        int pionter_right = nums.length - 1;
        int i = 0;
        while (pionter < pionter_right) {
            if (nums[i] < piovt) {
                swap(nums, pionter, i);
                pionter++;
                i++;
            } else if (nums[i] == piovt) {
                i++;
            } else {
                swap(nums, i, pionter_right);
                pionter_right--;
            }
        }
        if (nums.length - i - 1 > k) {// 在大的区间里
            return find(nums, i , nums.length - 1, k);
        } else if (pionter + 1 == k) {
            return nums[pionter];
        } else {// 在小的区间里
            return find(nums, 0, pionter - 1, k - (nums.length - pionter + 1));
        }
    }

    public int find(int[] nums, int start, int end,int k) {
        int piovt = nums[start];
        int pionter = start;
        int pionter_right = end;
        int i = start;
        while (pionter < pionter_right) {
            if (nums[i] < piovt) {
                swap(nums, pionter, i);
                pionter++;
                i++;
            } else if (nums[i] == piovt) {
                i++;
            } else {
                swap(nums, i, pionter_right);
                pionter_right--;
            }
        }
        if ((end-start+1)- i - 1 > k) {// 在大的区间里
            return find(nums, i , end, k);
        } else if (pionter + 1 == k) {
            return nums[pionter];
        } else {// 在小的区间里
            return find(nums, 0, start, k - ((end-start) - pionter + 1));
        }
    }

    public void swap(int[] nums, int index1, int index2) {
        int tmp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = tmp;
    }
}
