package person.man.findjobs;

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
        int nums[] = new int[]{1, 1, 2};
        new mergeTwoOrderArr().removeDuplicates(nums);
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
//先用哈希表走一遍吧  //
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int count = map.getOrDefault(num, 0);
            if ((count + 1) > (nums.length/2)) {
                return num;
            } else {
                map.put(num, count + 1);
            }

        }
        return 0;
    }
}
