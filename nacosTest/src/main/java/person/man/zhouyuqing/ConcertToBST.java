package person.man.zhouyuqing;

import java.util.Arrays;

public class ConcertToBST {
    public static void main(String[] args) {
        TreeNode result = new ConcertToBST().sortedArrayToBST(new int[]{-10, -3, 0, 5, 9});
        System.out.println(result);
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
}
