package person.man.zhouyuqing;

import java.util.Arrays;

public class ConcertToBST {
    public static void main(String[] args) {
        // TreeNode result = new ConcertToBST().sortedArrayToBST(new int[]{-10, -3, 0, 5, 9});
        // System.out.println(result);
        int res = new ConcertToBST().searchInsert(new int[]{1, 3, 5, 6}, 2);
        System.out.println(res);
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        TreeNode res = new TreeNode();
        int mid = nums.length / 2;
        int mid_val = nums[mid];
        res.val = mid_val;
        res.left = sortedArrayToBST(Arrays.copyOfRange(nums, 0, mid));
        res.right = sortedArrayToBST(Arrays.copyOfRange(nums, mid + 1, nums.length));
        return res;
    }

    public int searchInsert(int[] nums, int target) {
        return findIndex(nums, 0, nums.length, target);
    }

    public int findIndex(int[] nums, int index1, int index2, int target) {
        if (index1 + 1 == index2) {
            if (target > nums[index1]) {
                return index1 + 1;
            } else {
                return index1;
            }

        }
        int mid = (index1 + index2) / 2;
        if (nums[mid] > target) {
            return findIndex(nums, index1, mid, target);
        } else if (nums[mid] < target) {
            return findIndex(nums, mid, index2, target);
        } else {
            return mid;
        }
    }

}
