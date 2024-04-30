package person.man.zhouyuqing;

import java.util.Arrays;

public class ConcertToBST {
    public static void main(String[] args) {

    }
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        TreeNode res = new TreeNode();
        int mid = nums[nums.length / 2];
        res.val = nums[mid];
        res.left = sortedArrayToBST(Arrays.copyOfRange(nums, 0, mid));
        res.right = sortedArrayToBST(Arrays.copyOfRange(nums, mid + 1, nums.length));
        return res;
    }
}
