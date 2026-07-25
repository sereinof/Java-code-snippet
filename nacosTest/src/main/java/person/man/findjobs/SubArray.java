package person.man.findjobs;

public class SubArray {

    public int minSubArrayLen(int target, int[] nums) {
        int res = 0;
        int n = nums.length;
        int[] pre = new int[n];
        int[] after = new int[n];
        pre[0] = nums[0];
        after[n - 1] = nums[n - 1];
        for (int i = 1; i < n; i++) {
            pre[i] = nums[i] + pre[i - 1];
        }
        for (int i = n - 2; i >= 0; i--) {
            after[i] = nums[i] + after[i + 1];
        }


        return res;
    }
}
